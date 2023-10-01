import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String botToken = "6613978596:AAG9sBnoSQIMvjJUS11_qSt3niWAsj9mfr0";


        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new WorldOfDorem1x(botToken));


        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


    }
}
