package tetris.Tetrimino;

import tetris.util.BoundaryChecker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeftskewTetrimino extends Tetrimino {

    public LeftskewTetrimino() {
        rotationFormSize = 4;
    }

    public LeftskewTetrimino(int centerLocation) {
        rotationFormSize = 4;
        this.centerLocation = centerLocation;
        rotationForm = 1;
        locations = new HashSet<>(Arrays.asList(centerLocation - 11, centerLocation - 10, centerLocation, centerLocation + 1));
    }

    @Override
    public void rotate(Set<Integer> softBoundaryNumbers) {
        int tempRotationForm = rotationForm;
        Set<Integer> locationNumbers;
        if(tempRotationForm == rotationFormSize) {
            tempRotationForm = 1;
        }
        else {
            tempRotationForm++;
        }

        switch (tempRotationForm) {
            case 1 :
                locationNumbers = new HashSet<>(Arrays.asList(centerLocation - 11, centerLocation - 10, centerLocation, centerLocation + 1));
                break;
            case 2 :
                locationNumbers = new HashSet<>(Arrays.asList(centerLocation - 9, centerLocation, centerLocation + 1, centerLocation + 10));
                break;
            case 3 :
                locationNumbers = new HashSet<>(Arrays.asList(centerLocation - 1, centerLocation, centerLocation + 10, centerLocation + 11));
                break;
            default:
                locationNumbers = new HashSet<>(Arrays.asList(centerLocation - 10, centerLocation - 1, centerLocation, centerLocation + 9));
                break;
        }

        if (BoundaryChecker.checkHardBoundary(locationNumbers) && BoundaryChecker.checkSoftBoundary(locationNumbers, softBoundaryNumbers)) {
            locations = locationNumbers;
            rotationForm = tempRotationForm;
        }
    }
}
