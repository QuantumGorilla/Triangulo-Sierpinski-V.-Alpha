/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package particiones;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author manotasja
 */
public class Particiones extends JFrame {

    public static double getPromedio(int n, int M[][], double sum) {
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                sum = sum + M[k][l];
            }
        }
        sum = sum / (n * n);
        return sum;
    }

    public static double getVarianza(int n, int M[][], double prom, double sum) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum = sum + Math.pow(M[i][j] - prom, 2);
            }
        }
        sum = sum / (n * n);
        return sum;
    }

    public Particiones() {
        //Definir tamaño del lienzo
        this.setSize(400, 400);
        //Definir método predeterminado de cerrado
        //EXIT_ON_CLOSE: Termina la ejecución cuando cierra
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        int[][] M = generarMatriz();
        double prom = getPromedio(M.length, M, 0);
        double var = getVarianza(M.length, M, prom, 0);
        g.setColor(Color.GREEN);
        int x = 100, y = 100;
        int x2 = (int) prom;
        int y2 = (int) var;
        int continua = 7;
        if(var > 30){
        Dibujo(x, y, x2, y2, continua, g);
        }
    }

    private void Dibujo(int x, int y, int x2, int y2, int continua, Graphics g) {
        if (continua == 0) {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, y2 / 2, y2 / 2);
            g.setColor(Color.GREEN);
            g.drawRect(x, y, y2 / 2, y2 / 2);
            g.setColor(Color.BLACK);
            g.fillRect(x + y2 / 2, y, y2 / 2, y2 / 2);
            g.setColor(Color.GREEN);
            g.drawRect(x + y2 / 2, y, y2 / 2, y2 / 2);
            g.setColor(Color.BLACK);
            g.fillRect(x + y2 / 2, y + y2 / 2, y2 / 2, y2 / 2);
            g.setColor(Color.GREEN);
            g.drawRect(x + y2 / 2, y + y2 / 2, y2 / 2, y2 / 2);
        } else {
            Dibujo(x, y, y2 / 2, x2 - 1, continua - 1, g);
            Dibujo(x + y2 / 2, y, y2 / 2, x2 - 1, continua - 1, g);
            Dibujo(x + y2 / 2, y + y2 / 2, y2 / 2, x2 - 1, continua - 1, g);
        }
    }

    public static int[][] generarMatriz() {

        BufferedImage image = null;
        int n = 400;
        int m = 400;
        int[][] matriz = new int[n][m];
        try {
            image = ImageIO.read(new File("C:\\bot\\imagen.jpg"));

            Color color;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    color = new Color(image.getRGB(x, y));
                    matriz[x][y] = color.getRed();
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return matriz;
    }

    public static void main(String[] args) throws IOException {
            new Particiones().setVisible(true);
    }
}
