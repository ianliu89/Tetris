package tetris.tetrimino;

import tetris.Location;

import java.util.Set;
import java.util.stream.Collectors;

public class Tetrimino {

    protected Set<Location> locations;
    protected Location centerLocation;
    protected int rotationForm;
    protected int rotationFormSize;

    public void rotate(Set<Location> softBoundary){};

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public int getRotationForm() {
        return rotationForm;
    }

    public Location getCenterLocation() {
        return centerLocation;
    }

    public void setCenterLocation(Location centerLocation) {
        this.centerLocation = centerLocation;
    }

    public void updateCenterLocation(int xMovement, int yMovement) {
        centerLocation  = updateLocation(centerLocation, xMovement, yMovement);
        updateLocations(xMovement, yMovement);
    }

    public void updateLocations(int xMovement, int yMovement) {
        locations = locations.stream().map(location -> updateLocation(location, xMovement, yMovement))
            .collect(Collectors.toSet());
    }

    public Location updateLocation(Location location, int xMovement, int yMovement) {
        return new Location(location.getxLocation() + xMovement, location.getyLocation() + yMovement);
     }
}
