package game.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player implements ActionListener {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private Image image;
    private int height;
    private int width;
    private List<Shoot> shoots;
    private boolean isVisible;
    private boolean isTurbo = false;
    private Timer timer;

    public Player() {
        this.x = 100;
        this.y = 100;
        isVisible = true;

        shoots = new ArrayList<>();

        timer = new Timer(5000, this);
        timer.start();
    }

    public void load() {
        ImageIcon reference = new ImageIcon("src/res/spaceship2.png");
        image = reference.getImage();
        height = image.getHeight(null);
        width = image.getWidth(null);
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void simpleShoot() {
        this.shoots.add(new Shoot(x + width, y + (height / 2)));
    }

    public void turbo() {
        isTurbo = true;
        ImageIcon reference = new ImageIcon("src/res/naveturbo.png");
        image = reference.getImage();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, height, width);
    }

    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == KeyEvent.VK_A) {
            if (isTurbo == false){
                simpleShoot();
            }

        }
        if (code == KeyEvent.VK_SPACE) {
            turbo();
        }
        if (code == KeyEvent.VK_UP) {
            dy = -3;
        }
        if (code == KeyEvent.VK_DOWN) {
            dy = 3;
        }
        if (code == KeyEvent.VK_LEFT) {
            dx = -3;
        }
        if (code == KeyEvent.VK_RIGHT) {
            dx = 3;
        }
    }

    public void keyRelease(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (code == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (code == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (code == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public List<Shoot> getShoots() {
        return shoots;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isTurbo() {
        return isTurbo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isTurbo == true) {
            turbo();
            isTurbo = false;
        }
        if (isTurbo == false){
            load();
        }
    }
}
