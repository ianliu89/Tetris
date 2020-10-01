package tetris.Tetrimino;

import tetris.Location;
import tetris.util.BoundaryChecker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static tetris.GameConfig.BLOCK_SIZE;

public class LTetrimino extends Tetrimino{

    public LTetrimino() {
        rotationFormSize = 4;
    }

    public LTetrimino(Location centerLocation) {
        rotationFormSize = 4;
        this.centerLocation = centerLocation;
        rotationForm = 1;
        locations = new HashSet<>(Arrays.asList(
                updateLocation(centerLocation, BLOCK_SIZE, -BLOCK_SIZE),
                updateLocation(centerLocation, -BLOCK_SIZE, 0),
                centerLocation,
                updateLocation(centerLocation, BLOCK_SIZE, 0)
                ));
    }

    @Override
    public void rotate(Set<Location> softBoundaryNumbers) {
        int tempRotationForm = rotationForm;
        Set<Location> newLocation;
        if(tempRotationForm == rotationFormSize) {
            tempRotationForm = 1;
        }
        else {
            tempRotationForm++;
        }

        switch (tempRotationForm) {
            case 1 :
                newLocation = new HashSet<>(Arrays.asList(
                        updateLocation(centerLocation, BLOCK_SIZE, -BLOCK_SIZE),
                        updateLocation(centerLocation, -BLOCK_SIZE,0),
                        centerLocation,
                        updateLocation(centerLocation, BLOCK_SIZE, 0)
                ));
                break;
            case 2 :
                newLocation = new HashSet<>(Arrays.asList(
                        updateLocation(centerLocation, 0, -BLOCK_SIZE),
                        centerLocation,
                        updateLocation(centerLocation, 0, BLOCK_SIZE),
                        updateLocation(centerLocation, BLOCK_SIZE, BLOCK_SIZE)
                ));
                break;
            case 3 :
                newLocation = new HashSet<>(Arrays.asList(
                        updateLocation(centerLocation, -BLOCK_SIZE, 0),
                        centerLocation,
                        updateLocation(centerLocation, BLOCK_SIZE, 0),
                        updateLocation(centerLocation, -BLOCK_SIZE, BLOCK_SIZE)
                ));
                break;
            default:
                newLocation = new HashSet<>(Arrays.asList(
                        updateLocation(centerLocation, -BLOCK_SIZE, -BLOCK_SIZE),
                        updateLocation(centerLocation, 0, -BLOCK_SIZE),
                        centerLocation,
                        updateLocation(centerLocation, 0, BLOCK_SIZE)
                ));
                break;
        }

        if (BoundaryChecker.checkHardBoundary(newLocation) && BoundaryChecker.checkSoftBoundary(newLocation, softBoundaryNumbers)) {
            locations = newLocation;
            rotationForm = tempRotationForm;
        }
    }
}
