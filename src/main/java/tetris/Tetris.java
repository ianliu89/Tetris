package tetris;

import tetris.tetrimino.*;
import tetris.util.BoundaryChecker;
import tetris.util.JframeDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

import static tetris.GameConfig.*;
import static tetris.util.TetrisCreator.randomlyCreateTetrimno;
import static tetris.util.TetrisMovementHelper.*;

public class Tetris implements KeyListener, ActionListener {

    public static Tetris tetris;

    private Renderer renderer;
    private Timer timer;
    private Tetrimino tetrimino;
    private Tetrimino nextTetrimino;
    private InactiveBlocks inactiveBlocks;
    private JFrame jframe = new JFrame();
    private int score = 0;

    private Tetris() {
        renderer = new Renderer();
        inactiveBlocks = new InactiveBlocks();
        timer = new Timer(600, this);
        tetrimino = randomlyCreateTetrimno();
        nextTetrimino = randomlyCreateTetrimno();
        jframe.setTitle("Tetris");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.pack();
        jframe.add(renderer);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);
        timer.start();
    }

    public static void main(String[] args)
    {
        tetris = new Tetris();
    }

    protected void repaint(Graphics g) {
        JframeDrawer.drawBackGround(g);
        JframeDrawer.drawFrame(g);
        JframeDrawer.drawTetrimino(g, tetrimino);
        JframeDrawer.drawInactiveBlock(g, inactiveBlocks);
        JframeDrawer.drawScore(g, score);
        JframeDrawer.drawNextBlockFrame(g);
        JframeDrawer.drawNextBlock(g, nextTetrimino);
        if(!BoundaryChecker.checkSoftBoundary(tetrimino.getLocations(), inactiveBlocks.getInactiveBlockSet())) {
            JframeDrawer.drawGameOver(g, timer, jframe, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        tetrisDrop();
        renderer.repaint();
    }

    private void tetrisDrop() {
        Set<Location> newLocations = moveVerticalTetris(BLOCK_SIZE, tetrimino);
        if(BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, inactiveBlocks.getInactiveBlockSet())) {
            tetrimino.updateCenterLocation(0, BLOCK_SIZE);
        }
        else {
            inactiveBlocks.addAllInactiveBlockSet(tetrimino.getLocations());
            score += inactiveBlocks.calculateDeletedBlockRow();
            inactiveBlocks.updateInactiveBlockSet();
            tetrimino = nextTetrimino;
            nextTetrimino = randomlyCreateTetrimno();
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        int key = e.getExtendedKeyCode();
        Set<Location> newLocations;

        switch(key) {
            case KeyEvent.VK_SPACE:
                tetrimino.rotate(inactiveBlocks.getInactiveBlockSet());
                break;
            case KeyEvent.VK_1:
                newLocations = moveHorizonalTetris(-BLOCK_SIZE, tetrimino);
                if(BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, inactiveBlocks.getInactiveBlockSet())) {
                    tetrimino.updateCenterLocation(-BLOCK_SIZE ,0);
                }
                break;
            case KeyEvent.VK_2:
                newLocations = moveVerticalTetris(BLOCK_SIZE, tetrimino);
                if(BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, inactiveBlocks.getInactiveBlockSet())) {
                    tetrimino.updateCenterLocation(0, BLOCK_SIZE);
                }
                break;
            case KeyEvent.VK_3:
                newLocations = moveHorizonalTetris(BLOCK_SIZE, tetrimino);
                if(BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, inactiveBlocks.getInactiveBlockSet())) {
                    tetrimino.updateCenterLocation(BLOCK_SIZE, 0);
                }
                break;
            default:
                break;
        }
        renderer.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }
}
