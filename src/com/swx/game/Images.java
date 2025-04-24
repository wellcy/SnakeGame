package com.swx.game;

import javax.swing.*;
import java.net.URL;

public class Images {
    //todo 将图片的路径封装成对象
    public static URL bodyURL = Images.class.getResource("/images/body.png");
    //todo 创建一个图片对象
    public static ImageIcon bodyImage = new ImageIcon(bodyURL);

    public static URL foodURL = Images.class.getResource("/images/food.png");
    public static ImageIcon foodImage = new ImageIcon(foodURL);

    public static URL headerURL = Images.class.getResource("/images/header.png");
    public static ImageIcon headerImage = new ImageIcon(headerURL);

    public static URL upURL = Images.class.getResource("/images/up.png");
    public static ImageIcon up = new ImageIcon(upURL);

    public static URL downURL = Images.class.getResource("/images/down.png");
    public static ImageIcon down = new ImageIcon(downURL);

    public static URL leftURL = Images.class.getResource("/images/left.png");
    public static ImageIcon left = new ImageIcon(leftURL);

    public static URL rightURL = Images.class.getResource("/images/right.png");
    public static ImageIcon right = new ImageIcon(rightURL);

    public static URL header2URL = Images.class.getResource("/images/header2.png");
    public static ImageIcon header2Image = new ImageIcon(header2URL);
}
