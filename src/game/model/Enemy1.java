package game.model;

import javax.swing.*;
import java.awt.*;

public class Enemy1 {
    private Image image;
    private int x;
    private int y;
    private int height;
    private int width;
    private boolean isVisible;

    private static final int WIDTH = 938;
    private static int speed = 2;

    public Enemy1(int x, int y){
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public void load(){
        ImageIcon reference = new ImageIcon("src/res/enemy1.png");
        image = reference.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void update(){
        this.x -= speed;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, height, width);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public static int getSpeed() {
        return speed;
    }

    public Image getImage() {
        return image;
    }
}
