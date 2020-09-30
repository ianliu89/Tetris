package tetris.Tetrimino;

import java.util.Set;
import java.util.stream.Collectors;

public class Tetrimino {

    protected Set<Integer> locations;
    protected int centerLocation;
    protected int rotationForm;
    protected int rotationFormSize;

    public void rotate(Set<Integer> softBoundaryNumbers){};

    public Set<Integer> getLocations() {
        return locations;
    }

    public int getRotationForm() {
        return rotationForm;
    }

    public int getCenterLocation() {
        return centerLocation;
    }

    public void setCenterLocation(int centerLocation) {
        this.centerLocation = centerLocation;
    }

    public void updateCenterLocation(int movement) {
        centerLocation += movement;
        updateLocations(movement);
    }

    public void updateLocations(int movement) {
        locations = locations.stream().map(location -> location + movement).collect(Collectors.toSet());
    }
}
