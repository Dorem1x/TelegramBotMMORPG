import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class WorldOfDorem1x extends TelegramLongPollingBot {

    Info info = new Info();
    Shop shop = new Shop();
    HashMap<String, Player> playerList = new HashMap<>();
    boolean isLose = false;
    boolean isWin = false;



    public void setLose(boolean lose) {
        isLose = lose;
    }

    public WorldOfDorem1x(String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        String command = update.getMessage().getText();
        String playerName = update.getMessage().getChat().getUserName();
        SendMessage response = new SendMessage();
        response.setChatId(update.getMessage().getChatId().toString());
        if (command.equals("/getPlayerInfo")) {
            if (isLose == false){
                response.setText(Player.getPlayerInfo(playerList.get(playerName)));
            } else {
                response.setText("К сожалению, Вы уже проиграли " +
                        "\nНо всегда можно попробовать создать нового персонажа!");
            }
        } else if (command.equals("/letsGoForAnAdventure")) {
            if ((isLose == false) && (isWin == false)){
                int playingDice = (int) (Math.random() * 6 + 1);
                if (playingDice == 1) {
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 1);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 0);
                    response.setText("Повезло, на этот раз приключения прошли безобидно");
                    if (playerList.get(playerName).getHealth() <= 0) {
                        setLose(true);
                    }
                    if(playerList.get(playerName).getLevel() == 10) {
                        isWin = true;
                    }
                } else if (playingDice == 3) {
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 1);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 7);
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() - 35);
                    response.setText("На пути к приклюениям тебя поджидал тролль-волшебник, тебе, конечно же " +
                            "удалось победить его магию, но " +
                            "без потерь не обошлось(");
                    if (playerList.get(playerName).getHealth() <= 0) {
                        setLose(true);
                    }
                    if(playerList.get(playerName).getLevel() == 10) {
                        isWin = true;
                    }
                } else if (playingDice == 6) {
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 1);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 10);
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() - 50);
                    response.setText("Не повезло, не повезло, на пути к приключениям на тебя напал " +
                            "огромный огр, в неравном бою тебе удалось победить, но и " +
                            "урона получено немало(((");
                    if (playerList.get(playerName).getHealth() <= 0) {
                        setLose(true);
                    }
                    if(playerList.get(playerName).getLevel() == 10) {
                        isWin = true;
                    }
                } else {
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 1);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 5);
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() - 25);
                    response.setText("На пути к приклюениям на тебя накинулся орк с топором, тебе удалось" +
                            " отбиться, но не без травм(");
                    if (playerList.get(playerName).getHealth() <= 0) {
                        setLose(true);
                    }
                    if(playerList.get(playerName).getLevel() == 10) {
                        isWin = true;
                    }
                }
            } else if (isLose == true){
                response.setText("Увы, Вы уже проиграли " +
                        "\nПопробуйте создать нового персонажа и всё получится!");
            } else if (isWin == true) {
                response.setText("Поздравляем, ВЫ ПРОШЛИ ИГРУ!!!!!");
            }
        } else if (command.equals("/info")) {
                response.setText(info.getInfo());
        } else if (command.equals("/createPlayer")) {
            Player newPlayer = new Player(playerName);
            newPlayer.setHealth(100);
            newPlayer.setLevel(1);
            newPlayer.setCoins(0);
            playerList.put(playerName, newPlayer);
            isWin = false;
            isLose = false;
            response.setText("Создан новый игрок с именем: " + playerList.get(playerName).getName() +
            "\nВперед к приключениям!");
        } else if (command.equals("/getPlayerList")) {
            HashMap<String, Integer> newPlayerList = new HashMap<>();
            for (String key : playerList.keySet()) {
                newPlayerList.put(key, playerList.get(key).getLevel());
            }
            if (newPlayerList.toString().equals("[]")) {
                response.setText("Пока на сервере нет ни одного персонажа(");
            }
            response.setText(newPlayerList.toString());
        } else if (command.equals("/getShopItems")) {
            response.setText(shop.getItemsInfo());
        } else if(command.equals("/buyItem1")){
            if((playerList.get(playerName).getCoins() >= 10) && (isLose == false)
            && playerList.get(playerName).getLevel() >= 3) {
                playerList.get(playerName).setCoins(playerList.get(playerName).getCoins()
                        - shop.getItems().get("Малое зелье лечения"));
                playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() + 25);
                if(playerList.get(playerName).getHealth() > 100) {
                    playerList.get(playerName).setHealth(100);
                }
                response.setText("Малое зелье лечения успешно куплено.\n" +
                        "Текущее здоровье: " + playerList.get(playerName).getHealth() +
                        "\n Количество монет: " + playerList.get(playerName).getCoins());
            } else{
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        } else if(command.equals("/buyItem2")){
            if((playerList.get(playerName).getCoins() >= 15) && (isLose == false)
            && (playerList.get(playerName).getLevel() >= 5)) {
                playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() -
                        shop.getItems().get("Большое зелье лечения"));
                playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() + 50);
                if(playerList.get(playerName).getHealth() > 100) {
                    playerList.get(playerName).setHealth(100);
                }
                response.setText("Большое зелье лечения успешно куплено.\n" +
                        "Текущее здоровье: " + playerList.get(playerName).getHealth() +
                        "\n Количество монет: " + playerList.get(playerName).getCoins());
            } else{
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        } else if(command.equals("/buyItem3")){
            if((playerList.get(playerName).getCoins() >= 25) && (isLose == false)
                    && (playerList.get(playerName).getLevel() >= 8)) {
                playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() -
                        shop.getItems().get("Огромное зелье лечения"));
                playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() + 100);
                if(playerList.get(playerName).getHealth() > 100) {
                    playerList.get(playerName).setHealth(100);
                }
                response.setText("Огромное зелье лечения успешно куплено.\n" +
                        "Текущее здоровье: " + playerList.get(playerName).getHealth() +
                        "\n Количество монет: " + playerList.get(playerName).getCoins());
            } else{
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        }else if(command.equals("/deletePlayer")){
            playerList.remove(playerName);
            response.setText("К сожалению, игрока " + playerName + " больше нет с нами(((");
        }else {
            response.setText("Такой команды не найдено:(");
        }
        try {
            execute(response);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "A1FirstTelegramBot";
    }


}
