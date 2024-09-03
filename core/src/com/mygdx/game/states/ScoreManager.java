package com.mygdx.game.states;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreManager {
    private static final String SCORE_FILE = "score.txt";

    // Method to save score to a file
    public static void saveScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            writer.write(Integer.toString(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load score from a file
    public static int loadScore() {
        int score = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                score = Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }
}

