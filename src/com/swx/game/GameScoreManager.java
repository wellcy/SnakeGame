package com.swx.game;

import java.io.*;
import java.util.Properties;

public class GameScoreManager {
    private static final String SCORE_FILE =  System.getProperty("user.dir") + "/SnakeGame/highScore.dat";;
    private int highScore;

    // 加载历史最高分
    public void loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            highScore = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            highScore = 0; // 默认值
        }
    }

    // 更新最高分
    public void updateHighScore(int newScore) {
        if (newScore > highScore) {
            highScore = newScore;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
                writer.write(String.valueOf(highScore));
            } catch (IOException e) {
                System.err.println("保存失败: " + e.getMessage());
            }
        }
    }

    // 获取当前最高分
    public int getHighScore() {
        return highScore;
    }
}
