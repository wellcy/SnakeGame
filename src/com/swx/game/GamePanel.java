package com.swx.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    //初始化蛇的长度
    int length;
    //用数组存储蛇的坐标
    int[] snakeX = new int[200];
    int[] snakeY = new int[200];
    //判断游戏是否开始
    boolean isStart = false;
    //定时器
    Timer timer;
    //用一个字符串来判断蛇头方向
    String direction;
    String nextDirection;     // 缓冲的下一个方向

    //食物的坐标
    int foodX;
    int foodY;
    //积分
    int score;
    //判断死亡
    boolean isDie;

    //初始化蛇的坐标
    public void init() {
        //设置蛇的初始长度为3，并初始化坐标
        length = 3;

        snakeX[0] = 175;
        snakeY[0] = 275;

        snakeX[1] = 150;
        snakeY[1] = 275;

        snakeX[2] = 125;
        snakeY[2] = 275;

        //设置蛇头初始方向
        direction = "right";
        nextDirection = "right";

        //设置食物初始位置
        foodX = 300;
        foodY = 200;
        //设置积分初始值
        score = 0;
        //设置死亡初始状态
        isDie = false;
    }
    //用构造方法初始化
    public GamePanel() {
        init();
        //设置焦点，todo 使得键盘监听生效
        this.setFocusable(true);
        //监听键盘事件
        addKeyListener(this);
//        this.addKeyListener(new KeyAdapter() {//这里是匿名类
//        });
            //设置定时器，每100毫秒执行一次，即每0.1秒执行一次，参数1是时间间隔，参数2是执行事件
        timer = new Timer(100-2*score,this);
         //启动定时器
        timer.start();
    }

    @Override
            // 重写keyPressed方法，监听键盘按下事件
    public void keyPressed(KeyEvent e) {
        //super.keyPressed(e);
        // 判断按下的键是否是空格键
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                // 如果按下空格键，则游戏开始，否则游戏暂停
                // 如果小蛇已经死亡，再按空格可以回到初始状态
                if (isDie) {
                    init();
                }
                //如果小蛇没有死亡，空格键可以控制暂停和开始
                else {
                    isStart = !isStart;
                }
                // 重绘面板，即重新进入paintComponent函数
                repaint();
                break;
            case KeyEvent.VK_RIGHT:
                // 如果按下右键，则蛇向右走
                    nextDirection = "right";
                break;
            case KeyEvent.VK_LEFT:
                // 如果按下左键，则蛇向左走
                    nextDirection = "left";
                break;
            case KeyEvent.VK_UP:
                // 如果按下上键，则蛇向上走
                    nextDirection = "up";
                break;
            case KeyEvent.VK_DOWN:
                // 如果按下下键，则蛇向下走
                    nextDirection = "down";
                break;
        }
    }
    //实现KeyListener接口中的其他方法，虽然没用到，但是阻止报错
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(direction == "right"){
            if (nextDirection != "left")
            {
                direction = nextDirection;
            }
        }
        if (direction == "left"){
            if (nextDirection != "right")
            {
                direction = nextDirection;
            }
        }
        if (direction == "up"){
            if (nextDirection != "down")
            {
                direction = nextDirection;
            }
        }
        if (direction == "down"){
            if (nextDirection != "up")
            {
                direction = nextDirection;
            }
        }
        //游戏是开始状态且没有死亡的时候，蛇才动
        if(isStart&&!isDie){
            // 后一节的身子走到前一节的身子上
            for (int i = length - 1; i > 0; i--){
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //蛇头动
            switch (direction){
                case "right":
                    snakeX[0] += 25;
                    break;
                case "left":
                    snakeX[0] -= 25;
                    break;
                case "up":
                    snakeY[0] -= 25;
                    break;
                case "down":
                    snakeY[0] += 25;
                    break;
            }
            // 防止蛇向右跑出边界
            if (snakeX[0] > 750){
                isDie = true;
                //snakeX[0] = 25;
            }
            // 防止蛇向左跑出边界
            if (snakeX[0] < 25){
                isDie = true;
                //snakeX[0] = 750;
            }
            // 防止蛇向下跑出边界
            if (snakeY[0] > 725){
                isDie = true;
                //snakeY[0] = 100;
            }
            // 防止蛇向上跑出边界
            if (snakeY[0] < 100){
                isDie = true;
                //snakeY[0] = 725;
            }
            //检测食物与蛇头发生碰撞，即二者的坐标相等
            if(foodX == snakeX[0] && foodY == snakeY[0]){
                //蛇长度加一
                length++;
                //todo 蛇的蛇头坐标一定是25的倍数，因此食物的坐标也要与其对应，否则二者很难相等
                //食物X坐标范围[25,750]
                foodX = (int) (Math.random() * 30 + 1) * 25;
                //食物Y坐标范围[100,725]
                foodY = (int) (new Random().nextInt(26) +4) * 25;
                // 积分加一
                score++;
            }
            for (int i = 1; i < length; i++){
                //如果蛇头与蛇身体发生碰撞，则游戏结束
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isDie = true;
                    break;
                }
            }
            //重绘面板，显示蛇移动
            repaint();
        }
    }
    @Override
    //这个paintComponent函数相当于图形版的main方法，自动调用
    protected void paintComponent(Graphics g) {
        //继承父类的方法
        super.paintComponent(g);
        //todo 再补充自己要加的内容
        //设置面板的背景颜色
        this.setBackground(new Color(42, 147, 72, 229));
        //用画笔添加Images类的头部图片headerImage到面板，this 表示当前对象，即当前面板，g 表示画笔，x和y表示位置
        Images.header2Image.paintIcon(this, g, 10, 10);
        //设置画笔颜色
        g.setColor(new Color(82, 177, 166, 255));
        //用画笔画一个矩形
        g.fillRect(10, 70, 770, 685);
        //用画笔添加蛇头到面板,用switch判断蛇头方向
        switch (direction){
            case "right":
                Images.right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "left":
                Images.left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "up":
                Images.up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case "down":
                Images.down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }
        //用画笔添加蛇身体到面板  todo 用for循环蛇身，方便后面加长
        for (int i = 1; i < length; i++) {
            Images.bodyImage.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        //如果游戏暂停，添加提示语
        if (!isStart ){
            g.setColor(new Color(46, 234, 206, 255));
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("游戏暂停，点击空格继续游戏", 150, 330);
        }
        //画食物
        Images.foodImage.paintIcon(this, g, foodX, foodY);
        //画积分
        g.setColor(new Color(3, 1, 12, 255));
        g.setFont(new Font("微软雅黑", Font.BOLD, 15));
        g.drawString("积分："+score, 670, 50);
        //创建一个积分管理器的对象
        GameScoreManager manager = new GameScoreManager();
        manager.loadHighScore();
        //画最高分
        g.setColor(new Color(3, 1, 12, 255));
        g.setFont(new Font("微软雅黑", Font.BOLD, 15));
        g.drawString("历史最高分："+ manager.getHighScore(), 670, 25);
        manager.updateHighScore(score);
        //画死亡提示
        if (isDie){
            g.setColor(new Color(234, 10, 10, 255));
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("你已死亡，点击空格重新开始游戏", 100, 330);
        }
        if (score == manager.getHighScore()){
            g.setColor(new Color(56, 107, 207, 255));
            g.setFont(new Font("微软雅黑", Font.BOLD, 10));
            g.drawString("新纪录！", 630, 25);
        }

    }
}
