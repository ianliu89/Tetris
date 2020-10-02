package tetris.tetrimino;

import tetris.Location;
import tetris.util.BoundaryChecker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static tetris.GameConfig.BLOCK_SIZE;

public class TTetrimino extends Tetrimino{

    public TTetrimino() {
        rotationFormSize = 4;
    }

    public TTetrimino(Location centerLocation) {
        this.centerLocation = centerLocation;
        rotationFormSize = 4;
        rotationForm = 1;
        locations = new HashSet<>(Arrays.asList(
            updateLocation(centerLocation, 0, -BLOCK_SIZE),
            updateLocation(centerLocation, -BLOCK_SIZE, 0),
            centerLocation,
            updateLocation(centerLocation, BLOCK_SIZE, 0)));
    }

    @Override
    public void rotate(Set<Location> softBoundary) {
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
                    updateLocation(centerLocation, 0, -BLOCK_SIZE),
                    updateLocation(centerLocation, -BLOCK_SIZE, 0),
                    centerLocation,
                    updateLocation(centerLocation, BLOCK_SIZE, 0)));
                break;
            case 2 :
                newLocations = new HashSet<>(Arrays.asList(
                    updateLocation(centerLocation, 0, -BLOCK_SIZE),
                    centerLocation,
                    updateLocation(centerLocation, BLOCK_SIZE, 0),
                    updateLocation(centerLocation, 0, BLOCK_SIZE)));
                break;
            case 3 :
                newLocations = new HashSet<>(Arrays.asList(
                    updateLocation(centerLocation, -BLOCK_SIZE, 0),
                    centerLocation,
                    updateLocation(centerLocation, BLOCK_SIZE, 0),
                    updateLocation(centerLocation, 0, BLOCK_SIZE)));
                break;
            default :
                newLocations = new HashSet<>(Arrays.asList(
                    updateLocation(centerLocation, 0, -BLOCK_SIZE),
                    updateLocation(centerLocation, -BLOCK_SIZE, 0),
                    centerLocation,
                    updateLocation(centerLocation, 0, BLOCK_SIZE)));
                break;
        }

        if (BoundaryChecker.checkHardBoundary(newLocations) && BoundaryChecker.checkSoftBoundary(newLocations, softBoundary)) {
            locations = newLocations;
            rotationForm = tempRotationForm;
        }
    }
}
