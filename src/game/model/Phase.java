package game.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Phase extends JPanel implements ActionListener {

    private Image backGround;
    private Player player;
    private Timer timer;
    private java.util.List<Enemy1> enemy1;
    private java.util.List<Stars> stars;
    private boolean inGame;

    public Phase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon reference = new ImageIcon("src/res/Blackground.png");
        backGround = reference.getImage();

        player = new Player();
        player.load();

        addKeyListener(new KeyBoardAdapter());

        timer = new Timer(5, this);
        timer.start();

        initializerEnemy();
        initializerStars();
        inGame = true;
    }

    public void initializerEnemy() {
        int coordinates[] = new int[40];
        enemy1 = new ArrayList<Enemy1>();

        for (int i = 0; i < coordinates.length; i++) {
            int x = (int) (Math.random() * 8000 + 1024);
            int y = (int) (Math.random() * 650 + 30);
            enemy1.add(new Enemy1(x, y));

        }
    }

    public void initializerStars() {
        int coordinates[] = new int[500];
        stars = new ArrayList<Stars>();
        for (int i = 0; i < coordinates.length; i++) {
            int x = (int) (Math.random() * 1050 + 1024);
            int y = (int) (Math.random() * 1000 + 0);
            stars.add(new Stars(x, y));
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        if (inGame == true) {
            graphics2D.drawImage(backGround, 0, 0, null);

            for (int i = 0; i < stars.size(); i++) {
                Stars stars1 = stars.get(i);
                stars1.load();
                graphics2D.drawImage(stars1.getImage(), stars1.getX(), stars1.getY(), this);
            }

            graphics2D.drawImage(player.getImage(), player.getX(), player.getY(), this);

            java.util.List<Shoot> shoots = player.getShoots();
            for (int i = 0; i < shoots.size(); i++) {
                Shoot shoot = shoots.get(i);
                shoot.load();
                graphics2D.drawImage(shoot.getImage(), shoot.getX(), shoot.getY(), this);
            }

            for (int i = 0; i < enemy1.size(); i++) {
                Enemy1 enemies = enemy1.get(i);
                enemies.load();
                graphics2D.drawImage(enemies.getImage(), enemies.getX(), enemies.getY(), this);
            }
        }else {
            ImageIcon gameOver = new ImageIcon("src/res/game-over-2.jpg");
            graphics2D.drawImage(gameOver.getImage(), 0, 0, 1024, 700, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.update();
        if (player.isTurbo()){
            timer.setDelay(3);
        }else timer.setDelay(5);

        for (int i = 0; i < stars.size(); i++) {
            Stars stars1 = stars.get(i);
            if (stars1.isVisible()) {
                stars1.update();
            }else
                stars.remove(i);
        }

        java.util.List<Shoot> shoots = player.getShoots();
        for (int i = 0; i < shoots.size(); i++) {
            Shoot shoot = shoots.get(i);
            if (shoot.isVisible()) {
                shoot.update();
                if (player.isTurbo() == true){
                    shoots.get(i).setSpeed(-1);
                }
                if (player.isTurbo() == false){
                    shoots.get(i).setSpeed(2);
                }

            } else
                shoots.remove(i);
        }

        for (int i = 0; i < enemy1.size(); i++) {
            Enemy1 in = enemy1.get(i);
            if (in.isVisible()) {
                in.update();
            } else
                enemy1.remove(i);

        }
        checkColision();
        repaint();
    }

    public void checkColision(){
        Rectangle shipForm = player.getBounds();
        Rectangle enemyForm;
        Rectangle shootForm;

        for (int i = 0; i < enemy1.size(); i++){
            Enemy1 tempEnemy1 = enemy1.get(i);
            enemyForm = tempEnemy1.getBounds();
            if (shipForm.intersects(enemyForm)){
                if (player.isTurbo() == true){
                    tempEnemy1.setVisible(false);
                }else {
                    player.setVisible(false);
                    tempEnemy1.setVisible(false);
                    inGame = false;
                }
            }
        }

        java.util.List<Shoot> shoots = player.getShoots();
        for (int i = 0; i < shoots.size(); i++) {
            Shoot tempShoot = shoots.get(i);
            shipForm = tempShoot.getBounds();
            for (int j = 0; j < enemy1.size(); j++) {
                Enemy1 tempEnemy1 = enemy1.get(j);
                enemyForm = tempEnemy1.getBounds();
                if (shipForm.intersects(enemyForm)){
                    tempEnemy1.setVisible(false);
                    tempShoot.setVisible(false);
                }
            }
        }

    }

    private class KeyBoardAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            player.keyPressed(keyEvent);
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {
            player.keyRelease(keyEvent);
        }
    }
}
