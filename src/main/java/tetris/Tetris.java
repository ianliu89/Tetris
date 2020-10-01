package tetris;

import tetris.Tetrimino.*;
import tetris.util.BoundaryChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static tetris.GameConfig.*;

public class Tetris implements KeyListener, ActionListener {

    public static Tetris tetris;

    public Renderer renderer;

    private Timer timer;

    private Tetrimino tetrimino;

    private InactiveBlocks inactiveBlocks;

    private Random random = new Random();

    public JFrame jframe = new JFrame();

    public Tetris() {
        renderer = new Renderer();
        inactiveBlocks = new InactiveBlocks();
        timer = new Timer(600, this);
        tetrimino = randomlyCreateTetrimno();
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

    public void repaint(Graphics g) {
        drawBackGround(g);
        drawFrame(g);
        drawTetrimino(g);
        drawInactiveBlock(g);
        if(!BoundaryChecker.checkSoftBoundary(tetrimino.getLocations(), inactiveBlocks.getInactiveBlockSet())) {
            drawGameOver(g);
        }
    }

    private void tetrisDrop() {
        Set<Location> newLocations = new HashSet<>();
        for(Location location: tetrimino.getLocations()) {
            newLocations.add(new Location(location.getxLocation(), location.getyLocation()));
        }
        newLocations = newLocations.stream().map(newLocation -> {
            newLocation.setyLocation(newLocation.getyLocation() + BLOCK_SIZE);
            return newLocation;
        }).collect(Collectors.toSet());
        if(BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, inactiveBlocks.getInactiveBlockSet())) {
            tetrimino.updateCenterLocation(0, BLOCK_SIZE);
        }
        else {
            inactiveBlocks.addAllInactiveBlockSet(tetrimino.getLocations());
            tetrimino = randomlyCreateTetrimno();
        }
    }

    private void drawGameOver(Graphics g) {
        timer.stop();
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 40));
        g.drawString("Game Over!", 60, HEIGHT / 2 - 50);
        jframe.removeKeyListener(this);
    }

    private boolean checkInactiveBoundary(Location location) {
        for(Location inactiveBlock : inactiveBlocks.getInactiveBlockSet()) {
            if((inactiveBlock.getxLocation() == location.getxLocation()) &&
                    inactiveBlock.getyLocation() == location.getyLocation()) {
                return true;
            }
        }
        return false;
    }

    private Tetrimino randomlyCreateTetrimno() {
        int randInt = random.nextInt(7);
        Tetrimino newTerimino;
        switch(randInt) {
            case 0 :
                newTerimino = new JTetrimino(new Location(140, 80));
                break;
            case 1 :
                newTerimino = new LeftskewTetrimino(new Location(140, 80));
                break;
            case 2 :
                newTerimino = new LTetrimino(new Location(140, 80));
                break;
            case 3 :
                newTerimino = new RightskewTetrimino(new Location(140, 80));
                break;
            case 4 :
                newTerimino = new SquareTetrimino(new Location(120, 60));
                break;
            case 5 :
                newTerimino = new StraightTetrimino(new Location(120, 60));
                break;
            default :
                newTerimino = new TTetrimino(new Location(140, 80));
                break;
        }

        return newTerimino;
    }

    private void drawTetrimino(Graphics g) {

        Set<Location> locations = tetrimino.getLocations();
        for(Location location: locations) {
            g.setColor(Color.WHITE);
            g.fillRect(location.getxLocation(), location.getyLocation(), BLOCK_SIZE, BLOCK_SIZE);
            g.setColor(Color.CYAN);
            g.drawRect(location.getxLocation(), location.getyLocation(), BLOCK_SIZE, BLOCK_SIZE);
        }
    }

    private void drawInactiveBlock(Graphics g) {
        inactiveBlocks.getInactiveBlockSet().forEach(inactiveBlock -> {

            g.setColor(Color.WHITE);
            g.fillRect(inactiveBlock.getxLocation(), inactiveBlock.getyLocation(), BLOCK_SIZE, BLOCK_SIZE);
            g.setColor(Color.CYAN);
            g.drawRect(inactiveBlock.getxLocation(), inactiveBlock.getyLocation(), BLOCK_SIZE, BLOCK_SIZE);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        tetrisDrop();
        renderer.repaint();
    }

    private void drawFrame(Graphics g) {
        for(int i=0; i<12; i++) {
            g.setColor(Color.GRAY);
            g.fillRect(40+ BLOCK_SIZE*i, 40, BLOCK_SIZE, BLOCK_SIZE);
            g.fillRect(40+ BLOCK_SIZE*i, 460, BLOCK_SIZE, BLOCK_SIZE);
            g.setColor(Color.WHITE);
            g.drawRect(40+ BLOCK_SIZE*i, 40, BLOCK_SIZE, BLOCK_SIZE);
            g.drawRect(40+ BLOCK_SIZE*i, 460, BLOCK_SIZE, BLOCK_SIZE);

        }

        for(int j=0; j<22; j++) {
            g.setColor(Color.GRAY);
            g.fillRect(40, 40 + BLOCK_SIZE*j, BLOCK_SIZE, BLOCK_SIZE);
            g.fillRect(260, 40 + BLOCK_SIZE*j, BLOCK_SIZE, BLOCK_SIZE);
            g.setColor(Color.WHITE);
            g.drawRect(40, 40 + BLOCK_SIZE*j, BLOCK_SIZE, BLOCK_SIZE);
            g.drawRect(260, 40 + BLOCK_SIZE*j, BLOCK_SIZE, BLOCK_SIZE);
        }
    }

    private void drawBackGround(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        int key = e.getExtendedKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            tetrimino.rotate(inactiveBlocks.getInactiveBlockSet());
        }

        if (key == KeyEvent.VK_1) {
            Set<Location> newLocations = new HashSet<>();
            for(Location location: tetrimino.getLocations()) {
                newLocations.add(new Location(location.getxLocation(), location.getyLocation()));
            }
            newLocations = newLocations.stream().map(newLocation -> {
                newLocation.setxLocation(newLocation.getxLocation() - BLOCK_SIZE);
                return newLocation;
            }).collect(Collectors.toSet());
            if(BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, inactiveBlocks.getInactiveBlockSet())) {
                tetrimino.updateCenterLocation(-BLOCK_SIZE ,0);
            }
        }

        if (key == KeyEvent.VK_2) {
            Set<Location> newLocations = new HashSet<>();
            for(Location location: tetrimino.getLocations()) {
                newLocations.add(new Location(location.getxLocation(), location.getyLocation()));
            }
            newLocations = newLocations.stream().map(newLocation -> {
                newLocation.setyLocation(newLocation.getyLocation() + BLOCK_SIZE);
                return newLocation;
            }).collect(Collectors.toSet());
            if(BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, inactiveBlocks.getInactiveBlockSet())) {
                tetrimino.updateCenterLocation(0, BLOCK_SIZE);
            }
        }

        if (key == KeyEvent.VK_3) {
            Set<Location> newLocations = new HashSet<>();
            for(Location location: tetrimino.getLocations()) {
                newLocations.add(new Location(location.getxLocation(), location.getyLocation()));
            }
            newLocations = newLocations.stream().map(newLocation -> {
                newLocation.setxLocation(newLocation.getxLocation() + BLOCK_SIZE);
                return newLocation;
            }).collect(Collectors.toSet());
            if(BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, inactiveBlocks.getInactiveBlockSet())) {
                tetrimino.updateCenterLocation(BLOCK_SIZE, 0);
            }
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
