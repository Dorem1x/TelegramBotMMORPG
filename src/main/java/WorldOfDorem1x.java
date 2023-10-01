import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WorldOfDorem1x extends TelegramLongPollingBot {

    Info info = new Info();
    Shop shop = new Shop();
    HashMap<String, Player> playerList = new HashMap<>();
    HashMap<String, Player> recordMap = new HashMap<>();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");


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
            if (playerList.get(playerName).isLose() == false) {
                response.setText(Player.getPlayerInfo(playerList.get(playerName)));
            } else {
                response.setText("К сожалению, Вы уже проиграли " +
                        "\nНо всегда можно попробовать создать нового персонажа!" +
                        "\nТакже можете оставить свой отзыв/комментарий тут: " +
                        "\n@Dorem1x");
            }
        } else if (command.equals("/letsGoForAnAdventure")) {

            if ((playerList.get(playerName).isLose() == false) && (playerList.get(playerName).isWin() == false)) {
                int playingDice = (int) (Math.random() * 30 + 1);
                if (playingDice == 1) {
                    //Ничего
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 1);
                    response.setText("Фух, можно выдохнуть! Сегодня все прошло без проиcшествий." +
                            "\nУровней получено: " + 1);
                    if (playerList.get(playerName).getHealth() <= 0) {
                        playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() - 1);
                    }
                } else if ((playingDice >= 15) && (playingDice <= 19)) {
                    //Тролль-маг
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 1);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 6);
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() - 15);
                    response.setText("На пути к славе ты наткнулся на Тролля-Волшебника, победить, конечно же, удалось," +
                            "но без потерь не обошлось" +
                            "\nУрона получено: " + 15 +
                            "\nУровней получено: " + 1 +
                            "\nМонет получено: " + 6);
                    if (playerList.get(playerName).getHealth() <= 0) {
                        playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() - 1);
                    }
                } else if ((playingDice >= 25) && (playingDice <= 27)) {
                    //Огр
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 2);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 12);
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() - 25);
                    response.setText("Ой-йой, а вот сейчас, кажется, реально проблемы. ОГР! НАСТОЯЩИЙ ОГР!" +
                            "\nУрона получено: " + 25 +
                            "\nУровней получено: " + 2 +
                            "\nМонет получено: " + 12);
                    if (playerList.get(playerName).getHealth() <= 0) {
                        playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() - 2);
                    }
                } else if (playingDice == 30) {
                    //Дракон
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 4);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 25);
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() - 50);
                    response.setText("Что это щас было? Вы тоже это слышали? Дракон? ЭТО ЧТО СЕЙЧАС БЫЛ ДРАКОН?" +
                            "\nУрона получено: " + 50 +
                            "\nУровней получено: " + 4 +
                            "\nМонет получено: " + 25);
                    if (playerList.get(playerName).getHealth() <= 0) {
                        playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() - 4);
                    }
                } else if ((playingDice >= 28) && (playingDice <= 29)) {
                    //Великан
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 3);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 15);
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() - 35);
                    response.setText("Все же было хорошо, почему он? Огромный великан? Тяжелым будет бой!" +
                            "\nУрона получено: " + 35 +
                            "\nУровней получено: " + 3 +
                            "\nМонет получено: " + 15);
                    if (playerList.get(playerName).getHealth() <= 0) {
                        playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() - 3);
                    }
                } else if ((playingDice >= 20) && (playingDice <= 24)) {
                    //Мечники-скелеты
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 1);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 9);
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() - 20);
                    response.setText("Фух, я уж думал что-то серьезное намечается, а это всего лишь" +
                            " мечники-скелеты. Сейчас быстренько разберемся!" +
                            "\nУрона получено: " + 20 +
                            "\nУровней получено: " + 1 +
                            "\nМонет получено: " + 9);
                    if (playerList.get(playerName).getHealth() <= 0) {
                        playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() - 1);
                    }
                } else if (playingDice == 7) {
                    //Сокровище
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 25);
                    response.setText("СЮДААААА ЛУТ! Повезло-повезло, большая удача наткнуться на сокровище в этом" +
                            " гнилом местечке!" +
                            "\nУрона получено: " + 0 +
                            "\nУровней получено: " + 0 +
                            "\nМонет получено: " + 25);
                } else if ((playingDice >= 5) && (playingDice <= 6)) {
                    //Старик-странник
                    playerList.get(playerName).setMaxHealth(playerList.get(playerName).getMaxHealth() + 10);
                    response.setText("Не знаю почему, но идея подойти к какому-то старику выглядит сосвем не очень." +
                            " Но разве когда-то был выбор." +
                            "\nМаксимальное здоровье увеличено на: " + 10);
                } else if ((playingDice >= 2) && (playingDice <= 3)) {
                    //Лагерь-переселенцев
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() + 25);
                    if (playerList.get(playerName).getHealth() > playerList.get(playerName).getMaxHealth()) {
                        playerList.get(playerName).setHealth(playerList.get(playerName).getMaxHealth());
                    }
                    response.setText("Ох, на горизонте виднеется лагерь-переселенцев. Наконец-то можно передохнуть!" +
                            "\nЗдоровья восстановлено на: " + 25);
                } else {
                    //Орк с топором
                    playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() + 1);
                    playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() + 3);
                    playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() - 10);
                    response.setText("О нет, все было так хорошо, пока не повстречался Орк с топором." +
                            "Конечно, удалось ему навалять, но и ответка прилетела." +
                            "\nУрона получено: " + 10 +
                            "\nУровней получено: " + 1 +
                            "\nМонет получено: " + 3);
                    if (playerList.get(playerName).getHealth() <= 0) {
                        playerList.get(playerName).setLevel(playerList.get(playerName).getLevel() - 1);
                    }
                }
            } else if (playerList.get(playerName).isLose() == true) {
                response.setText("Увы, Вы уже проиграли " +
                        "\nПопробуйте создать нового персонажа и всё получится!" +
                        "\nТакже можете оставить свой отзыв/комментарий тут: " +
                        "\n@Dorem1x");
            } else if (playerList.get(playerName).isWin() == true) {
                response.setText("Поздравляю, ВЫ ПРОШЛИ ЭТУ ИГРУ! " +
                        "\nСледите за обновленями и перезапуском серверов." +
                        "\nТакже можете оставить свой отзыв/комментарий тут: " +
                        "\n@Dorem1x");
            }
            if (playerList.get(playerName).getHealth() <= 0) {
                playerList.get(playerName).setLose(true);
            }
            if (playerList.get(playerName).getLevel() == 30) {
                playerList.get(playerName).setWin(true);
            }
        } else if (command.equals("/info")) {
            response.setText(info.getInfo());
        } else if (command.equals("/createPlayer")) {
                Player newPlayer = new Player(playerName);
                newPlayer.setHealth(100);
                newPlayer.setMaxHealth(100);
                newPlayer.setLevel(1);
                newPlayer.setCoins(0);
                playerList.put(playerName, newPlayer);
                playerList.get(playerName).setLose(false);
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
        } else if (command.equals("/buyItem1")) {
            if ((playerList.get(playerName).getCoins() >= shop.getItems().get("Малое зелье лечения")) &&
                    (playerList.get(playerName).isLose() == false)
                    && playerList.get(playerName).getLevel() >= 1) {
                playerList.get(playerName).setCoins(playerList.get(playerName).getCoins()
                        - shop.getItems().get("Малое зелье лечения"));
                playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() + 25);
                if (playerList.get(playerName).getHealth() > playerList.get(playerName).getMaxHealth()) {
                    playerList.get(playerName).setHealth(playerList.get(playerName).getMaxHealth());
                }
                response.setText("Малое зелье лечения успешно куплено.\n" +
                        "Текущее здоровье: " + playerList.get(playerName).getHealth() +
                        "/" + playerList.get(playerName).getMaxHealth() +
                        "\n Количество монет: " + playerList.get(playerName).getCoins());
            } else {
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        } else if (command.equals("/buyItem2")) {
            if ((playerList.get(playerName).getCoins() >= shop.getItems().get("Большое зелье лечения")) &&
                    (playerList.get(playerName).isLose() == false)
                    && (playerList.get(playerName).getLevel() >= 10)) {
                playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() -
                        shop.getItems().get("Большое зелье лечения"));
                playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() + 50);
                if (playerList.get(playerName).getHealth() > playerList.get(playerName).getMaxHealth()) {
                    playerList.get(playerName).setHealth(playerList.get(playerName).getMaxHealth());
                }
                response.setText("Большое зелье лечения успешно куплено.\n" +
                        "Текущее здоровье: " + playerList.get(playerName).getHealth() +
                        "/" + playerList.get(playerName).getMaxHealth() +
                        "\n Количество монет: " + playerList.get(playerName).getCoins());
            } else {
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        } else if (command.equals("/buyItem3")) {
            if ((playerList.get(playerName).getCoins() >= shop.getItems().get("Огромное зелье лечения")) &&
                    (playerList.get(playerName).isLose() == false)
                    && (playerList.get(playerName).getLevel() >= 15)) {
                playerList.get(playerName).setCoins(playerList.get(playerName).getCoins() -
                        shop.getItems().get("Огромное зелье лечения"));
                playerList.get(playerName).setHealth(playerList.get(playerName).getHealth() + 100);
                if (playerList.get(playerName).getHealth() > playerList.get(playerName).getMaxHealth()) {
                    playerList.get(playerName).setHealth(playerList.get(playerName).getMaxHealth());
                }
                response.setText("Огромное зелье лечения успешно куплено.\n" +
                        "Текущее здоровье: " + playerList.get(playerName).getHealth() +
                        "/" + playerList.get(playerName).getMaxHealth() +
                        "\n Количество монет: " + playerList.get(playerName).getCoins());
            } else {
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        } else if (command.equals("/buyItem4")) {
            if ((playerList.get(playerName).getCoins() >= shop.getItems().get("Кольчужный нагрудник")) &&
                    (playerList.get(playerName).isLose() == false)
                    && (playerList.get(playerName).getLevel() >= 10) &&
                    (playerList.get(playerName).isChest() == false)) {
                playerList.get(playerName).setMaxHealth(playerList.get(playerName).getMaxHealth() + 10);
                playerList.get(playerName).setChest(true);
                response.setText("Кольчужный нагрудник успешно куплен! Максимальное здоровье увеличено на 10 единиц!");
            } else if (playerList.get(playerName).isChest() == true) {
                response.setText("У вас уже есть этот предмет!");
            } else {
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        } else if (command.equals("/buyItem5")) {
            if ((playerList.get(playerName).getCoins() >= shop.getItems().get("Кольчужный шлем")) &&
                    (playerList.get(playerName).isLose() == false)
                    && (playerList.get(playerName).getLevel() >= 15) &&
                    (playerList.get(playerName).isHead() == false)) {
                playerList.get(playerName).setMaxHealth(playerList.get(playerName).getMaxHealth() + 5);
                response.setText("Кольчужный шлем успешно куплен! Максимальное здоровье увеличено на 5 единиц!");
                playerList.get(playerName).setHead(true);
            } else if (playerList.get(playerName).isHead() == true) {
                response.setText("У вас уже есть этот предмет!");
            } else {
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        } else if (command.equals("/buyItem6")) {
            if ((playerList.get(playerName).getCoins() >= shop.getItems().get("Кольчужные поножи")) &&
                    (playerList.get(playerName).isLose() == false)
                    && (playerList.get(playerName).getLevel() >= 10) &&
                    (playerList.get(playerName).isLegs() == false)) {
                playerList.get(playerName).setMaxHealth(playerList.get(playerName).getMaxHealth() + 10);
                response.setText("Кольчужные поножи успешно куплены! Максимальное здоровье увеличено на 10 единиц!");
                playerList.get(playerName).setLegs(true);
            } else if (playerList.get(playerName).isLegs() == true) {
                response.setText("У вас уже есть этот предмет!");
            } else {
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        } else if (command.equals("/buyItem7")) {
            if ((playerList.get(playerName).getCoins() >= shop.getItems().get("Кольчужные наплечники")) &&
                    (playerList.get(playerName).isLose() == false)
                    && (playerList.get(playerName).getLevel() >= 15) &&
                    (playerList.get(playerName).isShoulder() == false)) {
                playerList.get(playerName).setMaxHealth(playerList.get(playerName).getMaxHealth() + 5);
                response.setText("Кольчужные наплечники успешно куплены! Максимальное здоровье увеличено на 5 единиц!");
                playerList.get(playerName).setShoulder(true);
            } else if (playerList.get(playerName).isShoulder() == true) {
                response.setText("У вас уже есть этот предмет!");
            } else {
                response.setText("Не хватает средств или недостаточно высокий уровень(");
            }
        } else if (command.equals("/deletePlayer")) {
            LocalDateTime localDateTime = LocalDateTime.now();
            recordMap.put(dtf.format(localDateTime), playerList.get(playerName));
            playerList.remove(playerName);
            response.setText("К сожалению, игрока " + playerName + " больше нет с нами(((");
        } else if (command.equals("/manual")) {
            response.setText(getManual());
        } else if (command.equals("/getRecordsTable")) {
            response.setText(recordMap.toString());
        } else {
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


    public String getManual() {
        return "Приветствую! Это пошаговая ММО-RPG игра!" +
                "\n Чтобы начать играть, тебе нужно создать персонажа" +
                "\n Чтобы продвигаться дальше по игре тебе нужно отправляться в приключения" +
                "\n Когда у тебя закончится здоровье, игра будет закончена. Ты проиграешь(" +
                "\n Чтобы начать заново, тебе нужно будет удалить персонажа и создать еще раз" +
                "\n Вроде бы всё сказал, приятной игры!" +
                "\n По всем остальным вопросам можно обратиться сюда: " +
                "\n @Dorem1x";
    }
}
