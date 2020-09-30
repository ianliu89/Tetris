package tetris.util;

import tetris.Location;

public class LocationConverter {

    public static int toNumber(Location location) {
        int tens = (location.getyLocation() - 60 )/20;
        int unit;
        if(location.getxLocation() == 240) {
            unit = 10;
        }
        else {
            unit = 1 + (location.getxLocation() - 60)/20;
        }
        return tens*10 + unit;
    }

    public static Location toLocation(int number) {
        int division = number/10;
        int mod = number % 10;
        int yLocation = (60 + 20 * division);
        int xLocation;
        if(mod == 0) {
           xLocation = 240;
        }
        else {
            xLocation = (60 + 20*(mod -1));
        }
        return new Location(xLocation, yLocation);
    }
}
