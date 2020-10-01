package tetris.Tetrimino;

import tetris.Location;
import tetris.util.BoundaryChecker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static tetris.GameConfig.BLOCK_SIZE;

public class LeftskewTetrimino extends Tetrimino {

    public LeftskewTetrimino() {
        rotationFormSize = 4;
    }

    public LeftskewTetrimino(Location centerLocation) {
        rotationFormSize = 4;
        this.centerLocation = centerLocation;
        rotationForm = 1;
        locations = new HashSet<>(Arrays.asList(
                updateLocation(centerLocation, -20, -20),
                updateLocation(centerLocation, 0, -20),
                centerLocation,
                updateLocation(centerLocation, 20, 0)
        ));
    }

    @Override
    public void rotate(Set<Location> softBoundaryNumbers) {
        int tempRotationForm = rotationForm;
        Set<Location> newLocations;
        if(tempRotationForm == rotationFormSize) {
            tempRotationForm = 1;
        }
        else {
            tempRotationForm++;
        }

        switch (tempRotationForm) {
            case 1 :
                newLocations = new HashSet<>(Arrays.asList(
                        updateLocation(centerLocation, -BLOCK_SIZE, -BLOCK_SIZE),
                        updateLocation(centerLocation, 0, -BLOCK_SIZE),
                        centerLocation,
                        updateLocation(centerLocation, BLOCK_SIZE, 0)
                ));
                break;
            case 2 :
                newLocations = new HashSet<>(Arrays.asList(
                        updateLocation(centerLocation, BLOCK_SIZE, -BLOCK_SIZE),
                        centerLocation,
                        updateLocation(centerLocation, BLOCK_SIZE, 0),
                        updateLocation(centerLocation, 0, BLOCK_SIZE)
                ));
                break;
            case 3 :
                newLocations = new HashSet<>(Arrays.asList(
                        updateLocation(centerLocation, -BLOCK_SIZE, 0),
                        centerLocation,
                        updateLocation(centerLocation, 0, BLOCK_SIZE),
                        updateLocation(centerLocation, BLOCK_SIZE, BLOCK_SIZE)
                ));
                break;
            default:
                newLocations = new HashSet<>(Arrays.asList(
                        updateLocation(centerLocation, 0, -BLOCK_SIZE),
                        updateLocation(centerLocation, -BLOCK_SIZE, 0),
                        centerLocation,
                        updateLocation(centerLocation, -BLOCK_SIZE, BLOCK_SIZE)
                ));
                break;
        }

        if (BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, softBoundaryNumbers)) {
            locations = newLocations;
            rotationForm = tempRotationForm;
        }
    }
}
