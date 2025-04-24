package com.swx.game;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    public static void main(String[] args) {
        //创建一个窗体对象
        JFrame jf = new JFrame();
        //设置窗体对象的标题
        jf.setTitle("贪吃蛇小游戏");
        //设置窗体对象的位置和大小
        int width= Toolkit.getDefaultToolkit().getScreenSize().width;
        int height=Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((width-800)/2,(height-800)/2,800,800);
        //设置是否可以改变窗体大小
        jf.setResizable(true);
        //设置关闭窗体时，程序是否退出
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //创建一个面板对象
        GamePanel gamePanel = new GamePanel();
        //将面板对象添加到窗体中
        jf.add(gamePanel);
        //设置窗体是否可见 todo 一般放在最后，所有属性都设置好了，再设置可见
        jf.setVisible(true);

    }
}
