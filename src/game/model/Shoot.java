package game.model;

import javax.swing.*;
import java.awt.*;

public class Shoot {
    private Image image;
    private int x;
    private int y;
    private int height;
    private int width;
    private boolean isVisible;

    private static final int WIDTH = 938;
    private int speed = 2;

    public Shoot(int x, int y){
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public void load(){
        ImageIcon reference = new ImageIcon("src/res/TiroSimples.png");
        image = reference.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void update(){
        this.x += speed;
        if (this.x > WIDTH){
            isVisible = false;
        }
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Image getImage() {
        return image;
    }

}
