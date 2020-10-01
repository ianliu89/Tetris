package tetris.util;

import tetris.Location;

import java.util.Set;

import static tetris.GameConfig.*;

public class BoundaryChecker {

    private static boolean checkLeftHardBoundary(Set<Location> locations) {
        for(Location location : locations) {
            if(location.getxLocation() < X_MIN_LOCATION) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkRightHardBoundary(Set<Location> locations) {
        for(Location location : locations) {
            if(location.getxLocation() > X_MAX_LOCATION) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkUpHardBoundary(Set<Location> locations) {
        for(Location location : locations) {
            if(location.getyLocation() < Y_MIN_LOCATION) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDownHardBoundary(Set<Location> locations) {
        for(Location location : locations) {
            if(location.getyLocation() > Y_MAX_LOCATION) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkHardBoundary(Set<Location> locationNumbers) {
        return checkLeftHardBoundary(locationNumbers) &&
                checkRightHardBoundary(locationNumbers) &&
                checkUpHardBoundary(locationNumbers) &&
                checkDownHardBoundary(locationNumbers);
    }

    public static boolean checkSoftBoundary(Set<Location> locations, Set<Location> softBoundary) {
       for(Location location: locations) {
           if(softBoundary.contains(location)) {
               return false;
           }
       }
       return true;
    }
}
