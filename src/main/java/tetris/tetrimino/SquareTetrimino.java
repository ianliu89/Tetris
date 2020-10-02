package tetris.tetrimino;

import tetris.Location;

import java.util.Arrays;
import java.util.HashSet;

import static tetris.GameConfig.BLOCK_SIZE;

public class SquareTetrimino extends Tetrimino{

    public SquareTetrimino() {
        rotationFormSize = 1;
    }

    public SquareTetrimino(Location centerLocation) {
        rotationFormSize = 1;
        this.centerLocation = centerLocation;
        rotationForm = 1;
        locations = new HashSet<>(Arrays.asList(
            centerLocation,
            updateLocation(centerLocation, BLOCK_SIZE, 0),
            updateLocation(centerLocation, 0, BLOCK_SIZE),
            updateLocation(centerLocation, BLOCK_SIZE, BLOCK_SIZE)
        ));
    }
}
