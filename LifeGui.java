package Projects.GameOfLife;

// Jean Raphael Barbosa Santos, CS 142, Winter 2026
// Programming Project # 5, 03/02/2026

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LifeGui {

    private LifeModel life;

    public LifeGui(LifeModel life) {
        this.life = life;

        //Initializes the frame
        JFrame frame = new JFrame("Game of Life GUI");
        frame.setSize(life.getCols() * 20 + 20, life.getRows() * 20 + 40);
        frame.setLocationRelativeTo(null); //open in the middle of the screen
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //do not quit program when close window

        //Panel to draw the grid
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int r = 0; r < life.getRows(); r++) {
                    for (int c = 0; c < life.getCols(); c++) {
                        g.setColor(life.isAlive(r, c) ? Color.GREEN : Color.BLACK);
                        g.fillRect(c * 20, r * 20, 20, 20);
                        g.setColor(Color.GRAY);
                        g.drawRect(c * 20, r * 20, 20, 20);
                    }
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);

        //Animation timer
        new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                life.update();
                panel.repaint();
            }
        }).start();
    }
}
