package tetris.util;

import tetris.Location;
import tetris.tetrimino.*;

import java.util.Random;

public class TetrisCreator {

    public static Tetrimino randomlyCreateTetrimno() {
        int randInt = new Random().nextInt(7);
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
}
