package view;

import model.Field;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Animator implements Runnable {
    private Graphics graphics;
    private Graphics screenGraphics;
    private BufferedImage img;
    private int fieldHeight;
    private int fieldWidth;
    private HashMap<Integer, BufferedImage> images;
    private  int[][] arr;


    public Animator(Graphics screenGraphics, Field field) {
        this.screenGraphics = screenGraphics;
        this.fieldHeight = field.getHeight() * WindowView.CELL_SIZE;
        this.fieldWidth = field.getWidth() * WindowView.CELL_SIZE;
        this.img = new BufferedImage(fieldWidth, fieldHeight, BufferedImage.TYPE_3BYTE_BGR);
        this.graphics = img.getGraphics();
        this.arr = field.getField();
    }

    public void initImages() {
        images = new HashMap<>();
        try {
            BufferedImage image1 = ImageIO.read(new File("images"+"//"+"-1.png"));
            images.put(-1, image1);
            BufferedImage image2 = ImageIO.read(new File("images"+"//"+"0.jpg"));
            images.put(0, image2);
            BufferedImage image3 = ImageIO.read(new File("images"+"//"+"1.png"));
            images.put(1, image3);
        } catch (IOException e) {
            System.out.println("ошибка при инициализации картинки");
        }
    }


    private void drawAll() {
        initImages();
        drawCells();
        drawToScreen();

    }

    public void drawCells() {
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[i][j]==0){
                    graphics.drawImage(images.get(0),
                            i*WindowView.CELL_SIZE,
                            (j+1)*WindowView.CELL_SIZE,
                            WindowView.CELL_SIZE,
                            WindowView.CELL_SIZE,
                            null);
                }
                if(arr[i][j]==1){
                    graphics.drawImage(images.get(1),
                            i*WindowView.CELL_SIZE+5,
                            (j+1)*WindowView.CELL_SIZE+5,
                            WindowView.CELL_SIZE-10,
                            WindowView.CELL_SIZE-10,
                            null);
                }
                if(arr[i][j]==-1){
                    graphics.drawImage(images.get(-1),
                            i*WindowView.CELL_SIZE+5,
                            (j+1)*WindowView.CELL_SIZE+5,
                            WindowView.CELL_SIZE-10,
                            WindowView.CELL_SIZE-10,
                            null);
                }
            }
        }
        /*
        for (int i = 0; i < fieldWidth; i += WindowView.CELL_SIZE) {
            for (int j = 0; j < fieldHeight; j += WindowView.CELL_SIZE) {
                graphics.drawImage(images.get(-1), i, j, WindowView.CELL_SIZE, WindowView.CELL_SIZE, null);
            }
        }*/
        //graphics.drawImage(images.get(-1), 5, 5, WindowView.CELL_SIZE-10, WindowView.CELL_SIZE-10, null);

    }


    private void drawToScreen() {
        screenGraphics.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(33);   // 30 раз в секунда обновляем картинку на экране
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            drawAll();
        }
    }


}