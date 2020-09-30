package tetris.Tetrimino;

import tetris.util.BoundaryChecker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TTetrimino extends Tetrimino{

    public TTetrimino() {
        rotationFormSize = 4;
    }

    public TTetrimino(int centerLocation) {
        this.centerLocation = centerLocation;
        rotationFormSize = 4;
        rotationForm = 1;
        locations = new HashSet<>(Arrays.asList(centerLocation - 10, centerLocation - 1, centerLocation, centerLocation + 1));
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
                locationNumbers = new HashSet<>(Arrays.asList(centerLocation - 10, centerLocation - 1, centerLocation, centerLocation + 1));
                break;
            case 2 :
                locationNumbers = new HashSet<>(Arrays.asList(centerLocation - 10, centerLocation, centerLocation + 1, centerLocation + 10));
                break;
            case 3 :
                locationNumbers = new HashSet<>(Arrays.asList(centerLocation - 1, centerLocation, centerLocation + 1, centerLocation + 10));
                break;
            default :
                locationNumbers = new HashSet<>(Arrays.asList(centerLocation - 10, centerLocation - 1, centerLocation, centerLocation + 10));
                break;
        }

        if (BoundaryChecker.checkHardBoundary(locationNumbers) && BoundaryChecker.checkSoftBoundary(locationNumbers, softBoundaryNumbers)) {
            locations = locationNumbers;
            rotationForm = tempRotationForm;
        }
    }

}
