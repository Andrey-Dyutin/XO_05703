package controller;

import model.Field;
import model.Game;
import model.Player;
import view.WindowView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class WinnerController {
    private int[][] winLines1;
    public static int[][] winLine;
    private int[][] winLines2;
    private Game game;
    public static HashMap<Integer, BufferedImage> lines = new HashMap<>();
    private Player winner = null;
//    private int[][] field;

    public WinnerController(Game game) {
        this.game = game;
//        this.field = game.getField().getField();
    }


    public Player WhoseWin() {
        winLines1 = new int[game.getField().getField().length][game.getField().getField()[0].length];
        winLines2 = new int[game.getField().getField().length][game.getField().getField()[0].length];
        winLine = new int[game.getField().getField().length][game.getField().getField()[0].length];
        //Победа по вертикали.
        for (int i = 0; i < game.getField().getField().length; i++) {
            for (int j = 0; j < 6; j++) {
                int[] temp = new int[game.getField().getField()[0].length];
                for (int k = j; k < 5 + j; k++) {
                    temp[i] += game.getField().getField()[i][k];
                    winLine[i][k] = 1;
                    if (temp[i] == 5) {
                        BufferedImage linevert = null;
                        try {
                            linevert = ImageIO.read(new File("images" + "//" + "linevert.png"));
                            lines.put(1, linevert);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer1();
                    }
                    if (temp[i] == -5) {
                        BufferedImage linevert = null;
                        try {
                            linevert = ImageIO.read(new File("images" + "//" + "linevert.png"));
                            lines.put(1, linevert);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer2();
                    }

                }
                winLine = new int[game.getField().getField().length][game.getField().getField()[0].length];
            }

        }
        //победа по горизонтали
        for (int i = 0; i < game.getField().getField().length; i++) {
            for (int j = 0; j < 6; j++) {
                int[] temp = new int[game.getField().getField()[0].length];
                for (int k = j; k < 5 + j; k++) {
                    temp[i] += game.getField().getField()[k][i];
                    winLine[k][i] = 1;
                    if (temp[i] == 5) {
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linegoriz.png"));
                            lines.put(1, linegoriz);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return game.getPlayer1();
                    }
                    if (temp[i] == -5) {
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linegoriz.png"));
                            lines.put(1, linegoriz);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer2();
                    }
                }
                winLine = new int[game.getField().getField().length][game.getField().getField()[0].length];
            }

        }
        //победа по диагонали сверху вниз.
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int[][] temp = new int[7][2];
                for (int k = 0; k < 5; k++) {
                    temp[j][0] += game.getField().getField()[k + j][k + i];
                    temp[j][1] += game.getField().getField()[k + i][k + j];
                    winLines1[k + j][k + i] = 1;
                    winLines2[k + i][k + j] = 1;
                    if (temp[j][0] == 5) {
                        winLine = winLines1;
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linediag1.png"));
                            lines.put(1, linegoriz);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer1();
                    }
                    if (temp[j][1] == 5) {
                        winLine = winLines2;
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linediag1.png"));
                            lines.put(1, linegoriz);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer1();
                    }
                    if (temp[j][0] == -5) {
                        winLine = winLines1;
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linediag1.png"));
                            lines.put(1, linegoriz);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer2();
                    }
                    if (temp[j][1] == -5) {
                        winLine = winLines2;
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linediag1.png"));
                            lines.put(1, linegoriz);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer2();
                    }
                }
                winLines1 = new int[game.getField().getField().length][game.getField().getField()[0].length];
                winLines2 = new int[game.getField().getField().length][game.getField().getField()[0].length];
            }

        }

        int[][] field1 = new int[game.getField().getField().length][game.getField().getField()[0].length];
        for (int i = 0; i < field1.length; i++) {
            for (int j = 0; j < field1[0].length; j++) {
                field1[i][j] = game.getField().getField()[i][j];
            }
        }
        rotateMatrix(field1);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                int[][] temp = new int[7][2];
                for (int k = 0; k < 5; k++) {
                    temp[j][0] += field1[k + j][k + i];
                    temp[j][1] += field1[k + i][k + j];
                    winLines1[k + j][k + i] = 1;
                    winLines2[k + i][k + j] = 1;

                    if (temp[j][0] == 5) {
                        winLine = winLines1;
                        for (int l = 0; l < 3; l++) {
                            rotateMatrix(winLine);
                        }
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linediag2.png"));
                            lines.put(1, linegoriz);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer1();
                    }
                    if (temp[j][1] == 5) {
                        winLine = winLines2;
                        for (int l = 0; l < 3; l++) {
                            rotateMatrix(winLine);
                        }
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linediag2.png"));
                            lines.put(1, linegoriz);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer1();
                    }
                    if (temp[j][0] == -5) {
                        winLine = winLines1;
                        for (int l = 0; l < 3; l++) {
                            rotateMatrix(winLine);
                        }
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linediag2.png"));
                            lines.put(1, linegoriz);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer2();
                    }
                    if (temp[j][1] == -5) {
                        winLine = winLines2;
                        for (int l = 0; l < 3; l++) {
                            rotateMatrix(winLine);
                        }
                        BufferedImage linegoriz = null;
                        try {
                            linegoriz = ImageIO.read(new File("images" + "//" + "linediag2.png"));
                            lines.put(1, linegoriz);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return game.getPlayer2();
                    }
                }
                winLines1 = new int[game.getField().getField().length][game.getField().getField()[0].length];
                winLines2 = new int[game.getField().getField().length][game.getField().getField()[0].length];
            }
        }
        return null;
    }

    private static void rotateMatrix(int[][] mat) {
        // Consider all squares one by one
//        int[][] mat = game.getField().getField();
        for (int x = 0; x < mat.length / 2; x++) {
            // Consider elements in group of 4 in
            // current square
            for (int y = x; y < mat.length - x - 1; y++) {
                // store current cell in temp variable
                int temp = mat[x][y];

                // move values from right to top
                mat[x][y] = mat[y][mat.length - 1 - x];

                // move values from bottom to right
                mat[y][mat.length - 1 - x] = mat[mat.length - 1 - x][mat.length - 1 - y];

                // move values from left to bottom
                mat[mat.length - 1 - x][mat.length - 1 - y] = mat[mat.length - 1 - y][x];

                // assign temp to left
                mat[mat.length - 1 - y][x] = temp;
            }
        }
    }
}
