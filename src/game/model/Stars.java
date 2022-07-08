package game.model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Stars {
    private static final int WIDTH = 938;
    private static int speed = 2;
    private Image image;
    private int x;
    private int y;
    private int height;
    private int width;
    private boolean isVisible;

    public Stars(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public static int getSpeed() {
        return speed;
    }

    public void load() {
        ImageIcon reference = new ImageIcon("src/res/img.png");
        image = reference.getImage();

        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void update() {
        if (x < 0) {
            this.x = WIDTH;
            Random random = new Random();
            int m = random.nextInt(500);
            this.x = m + 1024;
            Random random1 = new Random();
            int n = random1.nextInt(768);
            this.y = n;
        }else
            this.x -= speed + 0.5;

    }

    public Rectangle getBounds() {
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

    public Image getImage() {
        return image;
    }
}
