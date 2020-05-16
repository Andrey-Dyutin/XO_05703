package view;

import controller.MoveController;
import controller.WinnerController;
import exception.WrongCoordinatinatesException;
import model.Field;
import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowView extends JFrame {
    public static final int CELL_SIZE = 70;
    private int[][] arr;
    private MoveController moveController = new MoveController();
    private Game game;
    private WinnerController winnerController;

    public static boolean stopGame = false;


    public WindowView(Game game) {
        this.setBounds(0, 0, game.getField().getWidth() * CELL_SIZE, game.getField().getHeight() * CELL_SIZE + 20);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowView.EXIT_ON_CLOSE);
        this.arr = game.getField().getField();
        this.winnerController = new WinnerController(game);


        JPanel panel = new JPanel();
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());
        panel.setBackground(Color.red);
        this.add(panel);
        this.setVisible(true);


        Animator animator = new Animator(panel.getGraphics(), game.getField());
        //new Thread(animator).start();


        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX() / WindowView.CELL_SIZE;//номер ячейки
                int y = e.getY() / WindowView.CELL_SIZE;
                if (x < arr[0].length || y < arr.length) {

                    if (!stopGame) {
                        moveController.makeMove(x, y, game.getField());
                    }
                    if (winnerController.WhoseWin() != null) {
                        if (winnerController.WhoseWin() != null && winnerController.WhoseWin().toString().contains(game.getPlayer1().toString())) {
//                                status = true;
                            stopGame = true;
                        }
                        if (winnerController.WhoseWin() != null && winnerController.WhoseWin().toString().contains(game.getPlayer2().toString())) {
//                                status = true;
                            stopGame = true;
                        }
                    }
                }
            }


            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        new Thread(animator).start();
    }
}

