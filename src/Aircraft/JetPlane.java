package Aircraft;

import Weather.Weather;
import common.Coordinates;

class JetPlane extends Aircraft {

    public JetPlane(String name, Coordinates coordinates) {
        super(AircraftType.JetPlane, name, coordinates);
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

        weatherOffsets[Weather.Sun.ordinal()] = new Coordinates(0, 10, 2);
        weatherOffsets[Weather.Fog.ordinal()] = new Coordinates(0, 1, 0);
        weatherOffsets[Weather.Rain.ordinal()] = new Coordinates(0, 5, 0);
        weatherOffsets[Weather.Snow.ordinal()] = new Coordinates(0, 0, -7);
    }
}
