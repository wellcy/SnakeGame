package com.swx.game;

import java.net.URL;

public class test {
    public static void main(String[] args) {
        URL url = Images.class.getResource("/");
        System.out.println(url);
    }
}
