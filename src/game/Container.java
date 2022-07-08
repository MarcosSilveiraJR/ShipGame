package game;

import game.model.Phase;

import javax.swing.*;

public class Container extends JFrame {
    public Container(){
        add(new Phase());
        setTitle("My Game");
        setSize(1024, 728);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Container();
    }
}
