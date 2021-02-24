package Aircraft;

import Weather.Weather;
import common.Coordinates;

class Balloon extends Aircraft {

    public Balloon(String name, Coordinates coordinates) {
        super(AircraftType.Balloon, name, coordinates);
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

        weatherOffsets[Weather.Sun.ordinal()] = new Coordinates(2, 0, 4);
        weatherOffsets[Weather.Fog.ordinal()] = new Coordinates(0, 0, -3);
        weatherOffsets[Weather.Rain.ordinal()] = new Coordinates(0, 0, -5);
        weatherOffsets[Weather.Snow.ordinal()] = new Coordinates(0, 0, -15);
    }
}
