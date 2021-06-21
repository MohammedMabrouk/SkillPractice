package com.mohamedmabrouk.skillpractice.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapAsync extends AsyncTask<String, String, JSONObject> {
    Elements questionsElements = null;
    JSONArray questionsJsonArray = new JSONArray();
    JSONArray answersJsonArray = new JSONArray();
    JSONObject questionJson = null;

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            questionsElements = Jsoup.connect("https://www.examveda.com/competitive-english/practice-mcq-question-on-direct-and-indirect-speech/?section=5")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("article.question");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        String questionText = "", correctAnswerText = "", answerText = "";
        Elements answersElements;
        try {
            if (questionsElements != null) {
                for (Element e : questionsElements) {
                    questionText = e.select("h2 > div.question-main").text();
                    correctAnswerText = e.select("div.row.answer_container > div > div > div:nth-child(2) > strong").text();
//                    #wrap > section > div > div.col-md-8 > article:nth-child(2) > div.row.answer_container > div > div > div:nth-child(2) > strong
                    answersElements = e.select("div.question-inner > div.form-style > div.form-inputs > p");
                    if (!questionText.isEmpty()) {
                        questionJson = new JSONObject();
                        answersJsonArray = new JSONArray();
                        for (Element answerElement : answersElements) {
                            answerText = answerElement.select("label:nth-of-type(2)").text();
                            if (!answerText.isEmpty())
                                answersJsonArray.put(answerText);
                        }
                        if (!correctAnswerText.isEmpty() && answersJsonArray.length() > 0) {
                            questionJson.put("question", questionText);
                            questionJson.put("answers", answersJsonArray);
                            questionJson.put("correctAnswer", correctAnswerText);
                            questionsJsonArray.put(questionJson);
                        }
                    }
                }
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        questionsElements.toString();
        Log.v("qqq", questionsJsonArray.toString());
    }
}
