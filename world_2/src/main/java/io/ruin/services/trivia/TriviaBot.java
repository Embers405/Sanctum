package io.ruin.services.trivia;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.ruin.api.utils.TimeUtils;
import io.ruin.model.World;
import io.ruin.model.entity.shared.listeners.LoginListener;
import io.ruin.utility.Broadcast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.Boolean.parseBoolean;

public class TriviaBot {

    private static String category;
    private static String question;
    private static String correctAnswer;
    private static long questiontime;

    private static void makeTriviaRequest() {
        try {
            URL url = new URL("https://opentdb.com/api.php?amount=1&type=boolean");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer bufferContent = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                bufferContent.append(inputLine);
            }
            br.close();

            Gson gson = new Gson();
            JsonObject returnObj = gson.fromJson(String.valueOf(bufferContent), JsonObject.class);
            JsonArray nestedArray = returnObj.get("results").getAsJsonArray();

            for (Object triviaObj :
                    nestedArray) {
                JsonObject currentTriviaObj = (JsonObject) triviaObj;
                category = currentTriviaObj.get("category").getAsString();
                question = currentTriviaObj.get("question").getAsString();
                correctAnswer = currentTriviaObj.get("correct_answer").getAsString();
                question.replaceAll("&#039;", "'");
                question.replaceAll("&quot;", "");
            }
            questiontime = System.currentTimeMillis();
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCategory() {
        return category;
    }

    public static String getQuestion() {
        return question;
    }

    public static String getCorrectAnswer() {
        return correctAnswer;
    }

    public static String getIncorrectAnswer() {
        String lowerCase = correctAnswer.toLowerCase();
        boolean value = parseBoolean(lowerCase);
        return Boolean.toString(!value);
    }

    public static long getTime() {
        return questiontime;
    }

    public static boolean running = false;

    private static long delay;

    private static long ranOutOfTime;

    public static boolean answered = false;

    public static void start() {
        if (ranOutOfTime < System.currentTimeMillis() && running) {
            Broadcast.GLOBAL.sendNews("[TRIVIA] Nobody answered " + TriviaBot.getQuestion());
            running = false;
            return;
        }

        if (delay > System.currentTimeMillis())
            return;

        makeTriviaRequest();

        Broadcast.GLOBAL.sendNews("[TRIVIA] " + getQuestion());
        delay = (System.currentTimeMillis() + TimeUtils.getMinutesToMillis(2));
        ranOutOfTime = (System.currentTimeMillis() + 300000000);
        running = true;
        answered = false;
    }

    static {
        World.startEvent(e -> {
            while (true) {
                TriviaBot.start();
                e.delay(9000000);
            }
        });

//        LoginListener.register(p -> {
//            if (running) {
//                p.sendFilteredMessage("[TRIVIA] Current Question " + TriviaBot.getQuestion());
//            }
//        });
//    }

    }
}

