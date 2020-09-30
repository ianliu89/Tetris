package tetris;

import tetris.Tetrimino.*;
import tetris.util.BoundaryChecker;
import tetris.util.LocationConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Array;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static tetris.GameConfig.HEIGHT;
import static tetris.GameConfig.WIDTH;

public class Tetris implements KeyListener, ActionListener {

    public static Tetris tetris;

    public Renderer renderer;

    private Timer timer;

    private Tetrimino tetrimino;

    private Set<Integer> inactiveBlocks;

    private Random random = new Random();

    public JFrame jframe = new JFrame();

    public Tetris() {
        renderer = new Renderer();
        inactiveBlocks = new HashSet<>();
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
    }

    private void tetrisDrop() {
        Set<Integer> locationNumbers = tetrimino.getLocations();
        locationNumbers = locationNumbers.stream().map(locationNumber -> locationNumber + 10).collect(Collectors.toSet());
        if(BoundaryChecker.checkHardBoundary(locationNumbers) && BoundaryChecker.checkSoftBoundary(locationNumbers, inactiveBlocks)) {
            tetrimino.updateCenterLocation(10);
        }
        else {
            inactiveBlocks.addAll(tetrimino.getLocations());
            tetrimino = randomlyCreateTetrimno();
        }
    }

    private boolean checkInactiveBoundary(int number) {
        for(int inactiveBlock : inactiveBlocks) {
            if(inactiveBlock == number) {
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
                newTerimino = new JTetrimino(15);
                break;
            case 1 :
                newTerimino = new LeftskewTetrimino(15);
                break;
            case 2 :
                newTerimino = new LTetrimino(15);
                break;
            case 3 :
                newTerimino = new RightskewTetrimino(15);
                break;
            case 4 :
                newTerimino = new SquareTetrimino(4);
                break;
            case 5 :
                newTerimino = new StraightTetrimino(4);
                break;
            default :
                newTerimino = new TTetrimino(15);
                break;
        }

        return newTerimino;
    }

    private void drawTetrimino(Graphics g) {

        Set<Integer> locationNumbers = tetrimino.getLocations();
        for(Integer locationNumber: locationNumbers) {
            Location location = LocationConverter.toLocation(locationNumber);
            g.setColor(Color.WHITE);
            g.fillRect(location.getxLocation(), location.getyLocation(), 20, 20);
            g.setColor(Color.CYAN);
            g.drawRect(location.getxLocation(), location.getyLocation(), 20, 20);
        }
    }

    private void drawInactiveBlock(Graphics g) {
        inactiveBlocks.forEach(inactiveBlock -> {
            Location location = LocationConverter.toLocation(inactiveBlock);

            g.setColor(Color.WHITE);
            g.fillRect(location.getxLocation(), location.getyLocation(), 20, 20);
            g.setColor(Color.CYAN);
            g.drawRect(location.getxLocation(), location.getyLocation(), 20, 20);
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
            g.fillRect(40+ 20*i, 40, 20, 20);
            g.fillRect(40+ 20*i, 460, 20, 20);
            g.setColor(Color.WHITE);
            g.drawRect(40+ 20*i, 40, 20, 20);
            g.drawRect(40+ 20*i, 460, 20, 20);

        }

        for(int j=0; j<22; j++) {
            g.setColor(Color.GRAY);
            g.fillRect(40, 40 + 20*j, 20, 20);
            g.fillRect(260, 40 + 20*j, 20, 20);
            g.setColor(Color.WHITE);
            g.drawRect(40, 40 + 20*j, 20, 20);
            g.drawRect(260, 40 + 20*j, 20, 20);
        }
    }

    private void drawBackGround(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        displayInfo(e, "KEY TYPED: ");
        int key = e.getExtendedKeyCode();
        System.out.println("key: ");

        if (key == KeyEvent.VK_SPACE) {
            tetrimino.rotate(inactiveBlocks);
        }

        if (key == KeyEvent.VK_1) {
            Set<Integer> locationNumbers = tetrimino.getLocations();
            locationNumbers = locationNumbers.stream().map(locationNumber -> locationNumber -1).collect(Collectors.toSet());
            if(BoundaryChecker.checkHardBoundary(locationNumbers) && BoundaryChecker.checkSoftBoundary(locationNumbers, inactiveBlocks)) {
                tetrimino.updateCenterLocation(-1);
            }
        }

        if (key == KeyEvent.VK_2) {
            Set<Integer> locationNumbers = tetrimino.getLocations();
            locationNumbers = locationNumbers.stream().map(locationNumber -> locationNumber + 10).collect(Collectors.toSet());
            if(BoundaryChecker.checkHardBoundary(locationNumbers) && BoundaryChecker.checkSoftBoundary(locationNumbers, inactiveBlocks)) {
                tetrimino.updateCenterLocation(10);
            }
        }

        if (key == KeyEvent.VK_3) {
            Set<Integer> locationNumbers = tetrimino.getLocations();
            locationNumbers = locationNumbers.stream().map(locationNumber -> locationNumber + 1).collect(Collectors.toSet());
            if(BoundaryChecker.checkHardBoundary(locationNumbers) && BoundaryChecker.checkSoftBoundary(locationNumbers, inactiveBlocks)) {
                tetrimino.updateCenterLocation(1);
            }
        }

        renderer.repaint();
    }

    private void displayInfo(KeyEvent e, String keyStatus) {

        //You should only rely on the key char if the event
        //is a key typed event.
        int id = e.getID();
        String keyString;
        if (id == KeyEvent.KEY_TYPED) {
            String c = e.toString();
            keyString = "key character = '" + c + "'";
        } else {
            int keyCode = e.getKeyCode();
            keyString = "key code = " + keyCode
                    + " ("
                    + KeyEvent.getKeyText(keyCode)
                    + ")";
        }
        System.out.println(keyString);
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
