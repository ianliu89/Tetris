package tetris.util;

import tetris.Location;

import java.util.Set;

public class BoundaryChecker {

    public static boolean checkLeftHardBoundary(Set<Integer> locationNumbers) {
        for(Integer locationNumber : locationNumbers) {
            Location location = LocationConverter.toLocation(locationNumber);
            if(location.getxLocation() < 60) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkRightHardBoundary(Set<Integer> locationNumbers) {
        for(Integer locationNumber : locationNumbers) {
            Location location = LocationConverter.toLocation(locationNumber);
            if(location.getxLocation() > 240) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkUpHardBoundary(Set<Integer> locationNumbers) {
        for(Integer locationNumber : locationNumbers) {
            Location location = LocationConverter.toLocation(locationNumber);
            if(location.getyLocation() < 60) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDownHardBoundary(Set<Integer> locationNumbers) {
        for(Integer locationNumber : locationNumbers) {
            Location location = LocationConverter.toLocation(locationNumber);
            if(location.getyLocation() > 440) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkHardBoundary(Set<Integer> locationNumbers) {
        return checkLeftHardBoundary(locationNumbers) &&
                checkRightHardBoundary(locationNumbers) &&
                checkUpHardBoundary(locationNumbers) &&
                checkDownHardBoundary(locationNumbers);
    }

    public static boolean checkSoftBoundary(Set<Integer> locationNumbers, Set<Integer> softBoundaryNumbers) {
       for(Integer locationNumber: locationNumbers) {
           if(softBoundaryNumbers.contains(locationNumber)) {
               return false;
           }
       }
       return true;
    }
}
