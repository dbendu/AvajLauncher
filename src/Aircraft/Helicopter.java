package Aircraft;

import Weather.Weather;
import common.Coordinates;

class Helicopter extends Aircraft {

    Helicopter(String name, Coordinates coordinates) {
        super(AircraftType.Helicopter, name, coordinates);
    }

    @Override
    protected void InitMessagesList() {
        messages = new String[Weather.values().length];

        for (int i = 0; i < Weather.values().length; ++i) {
            messages[i] = type.name() + ", " + Weather.values()[i];
        }
    }

    @Override
    protected void InitWeatherOffsetsList() {
        weatherOffsets = new Coordinates[Weather.values().length];

        weatherOffsets[Weather.Sun.ordinal()] = new Coordinates(10, 0, 2);
        weatherOffsets[Weather.Fog.ordinal()] = new Coordinates(1, 0, 0);
        weatherOffsets[Weather.Rain.ordinal()] = new Coordinates(5, 0, 0);
        weatherOffsets[Weather.Snow.ordinal()] = new Coordinates(0, 0, -12);
    }
}
