package tetris.util;

import tetris.InactiveBlocks;
import tetris.Location;
import tetris.tetrimino.Tetrimino;
import tetris.Tetris;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

import static tetris.GameConfig.*;
import static tetris.util.TetrisMovementHelper.moveHorizonalTetris;
import static tetris.util.TetrisMovementHelper.moveVerticalTetris;

public class JframeDrawer {

    public static void drawTetrimino(Graphics g, Tetrimino tetrimino) {
        Set<Location> locations = tetrimino.getLocations();
        for(Location location: locations) {
            g.setColor(Color.WHITE);
            g.fillRect(location.getxLocation(), location.getyLocation(), BLOCK_SIZE, BLOCK_SIZE);
            g.setColor(Color.CYAN);
            g.drawRect(location.getxLocation(), location.getyLocation(), BLOCK_SIZE, BLOCK_SIZE);
        }
    }

    public static void drawInactiveBlock(Graphics g, InactiveBlocks inactiveBlocks) {
        inactiveBlocks.getInactiveBlockSet().forEach(inactiveBlock -> {
            g.setColor(Color.WHITE);
            g.fillRect(inactiveBlock.getxLocation(), inactiveBlock.getyLocation(), BLOCK_SIZE, BLOCK_SIZE);
            g.setColor(Color.CYAN);
            g.drawRect(inactiveBlock.getxLocation(), inactiveBlock.getyLocation(), BLOCK_SIZE, BLOCK_SIZE);
        });
    }

    public static void drawGameOver(Graphics g, Timer timer, JFrame jframe, Tetris tetris) {
        timer.stop();
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 40));
        g.drawString("Game Over!", 60, HEIGHT / 2 - 50);
        jframe.removeKeyListener(tetris);
    }

    public static void drawScore(Graphics g, int score) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 24));
        g.drawString("Score", 15 * BLOCK_SIZE, 6 * BLOCK_SIZE);
        g.drawString(Integer.toString(score), 15 * BLOCK_SIZE, 8 * BLOCK_SIZE);
    }

    public static void drawBackGround(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public static void drawFrame(Graphics g) {
        for(int i=0; i<X_SIZE + 2; i++) {
            g.setColor(Color.GRAY);
            g.fillRect(40+ BLOCK_SIZE*i, 40, BLOCK_SIZE, BLOCK_SIZE);
            g.fillRect(40+ BLOCK_SIZE*i, 460, BLOCK_SIZE, BLOCK_SIZE);
            g.setColor(Color.WHITE);
            g.drawRect(40+ BLOCK_SIZE*i, 40, BLOCK_SIZE, BLOCK_SIZE);
            g.drawRect(40+ BLOCK_SIZE*i, 460, BLOCK_SIZE, BLOCK_SIZE);

        }

        for(int j=0; j<Y_SIZE + 2; j++) {
            g.setColor(Color.GRAY);
            g.fillRect(40, 40 + BLOCK_SIZE*j, BLOCK_SIZE, BLOCK_SIZE);
            g.fillRect(260, 40 + BLOCK_SIZE*j, BLOCK_SIZE, BLOCK_SIZE);
            g.setColor(Color.WHITE);
            g.drawRect(40, 40 + BLOCK_SIZE*j, BLOCK_SIZE, BLOCK_SIZE);
            g.drawRect(260, 40 + BLOCK_SIZE*j, BLOCK_SIZE, BLOCK_SIZE);
        }
    }

    public static void drawNextBlockFrame(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(15 * BLOCK_SIZE, 10 * BLOCK_SIZE, 7* BLOCK_SIZE, 6* BLOCK_SIZE);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 24));
        g.drawString("Next", 15 * BLOCK_SIZE, 11 * BLOCK_SIZE);
    }

    public static void drawNextBlock(Graphics g, Tetrimino nextTetrimino) {
        Tetrimino newTetrimino = new Tetrimino();
        newTetrimino.setLocations(nextTetrimino.getLocations());
        Set<Location> nextLocations = moveVerticalTetris(10 * BLOCK_SIZE, newTetrimino);
        newTetrimino.setLocations(nextLocations);
        nextLocations = moveHorizonalTetris(11 * BLOCK_SIZE, newTetrimino);
        newTetrimino.setLocations(nextLocations);
        drawTetrimino(g, newTetrimino);
    }
}
