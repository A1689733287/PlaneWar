package com.gtl.planewar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by G on 2017/6/22.
 */

public class ScoreShare {
    private Context context;
    private String[] Scores;
    private String[] Scores2;
    private String score_value;
    private SharedPreferences sp;

    public ScoreShare(Context context) {
        this.context = context;
        init();
    }

    public void init() {
        score_value = "";
        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String Score = sp.getString("SCORE", "0;0;0;0;0;0;0;0;0;0");
        Scores = Score.split(";");
        Scores2 = Score.split(";");
    }

    public void saveScore(int score) {
        boolean or = false;
        for (int i = 0; i < Scores.length; i++) {
            if (score > Integer.valueOf(Scores[i]) && !or) {
                Scores[i] = score + "";
                or = true;
                int j = i;
                while (j < Scores.length - 1) {
                    Scores[j + 1] = Scores2[j];
                    j++;
                }
            }
            if (i >= Scores.length - 1)
                score_value += Scores[i];
            else
                score_value += Scores[i] + ";";
        }
        Editor e = sp.edit();
        e.putString("SCORE", score_value);
        e.commit();
    }
}
