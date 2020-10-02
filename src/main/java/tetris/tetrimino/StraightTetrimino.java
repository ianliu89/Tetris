package tetris.tetrimino;


import tetris.Location;
import tetris.util.BoundaryChecker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static tetris.GameConfig.BLOCK_SIZE;

public class StraightTetrimino extends Tetrimino {

    public StraightTetrimino() {
        rotationFormSize = 2;
    }

    public StraightTetrimino(Location centerLocation) {
        rotationFormSize = 2;
        this.centerLocation = centerLocation;
        rotationFormSize = 4;
        rotationForm = 1;
        locations = new HashSet<>(Arrays.asList(
            updateLocation(centerLocation, -BLOCK_SIZE, 0),
            centerLocation,
            updateLocation(centerLocation, BLOCK_SIZE, 0),
            updateLocation(centerLocation, BLOCK_SIZE*2, 0)
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
                    updateLocation(centerLocation, -BLOCK_SIZE, 0),
                    centerLocation,
                    updateLocation(centerLocation, BLOCK_SIZE, 0),
                    updateLocation(centerLocation, BLOCK_SIZE*2, 0)
                ));
                break;
            default:
                newLocation = new HashSet<>(Arrays.asList(
                    updateLocation(centerLocation, 0, -BLOCK_SIZE),
                    centerLocation,
                    updateLocation(centerLocation, 0, BLOCK_SIZE),
                    updateLocation(centerLocation, 0, BLOCK_SIZE*2)
                ));
                break;
        }

        if (BoundaryChecker.checkHardBoundary(newLocation) && BoundaryChecker.checkSoftBoundary(newLocation, softBoundaryNumbers)) {
            locations = newLocation;
            rotationForm = tempRotationForm;
        }
    }

}
