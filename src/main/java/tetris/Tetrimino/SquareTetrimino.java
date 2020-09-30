package tetris.Tetrimino;

import java.util.Arrays;
import java.util.HashSet;

public class SquareTetrimino extends Tetrimino{

    public SquareTetrimino() {
        rotationFormSize = 1;
    }

    public SquareTetrimino(int centerLocation) {
        rotationFormSize = 1;
        this.centerLocation = centerLocation;
        rotationForm = 1;
        locations = new HashSet<>(Arrays.asList(centerLocation, centerLocation + 1, centerLocation + 10, centerLocation + 11));
    }
}
