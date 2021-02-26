package Weather;

import common.Coordinates;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class WeatherProvider {

//region Singleton

    private static WeatherProvider weatherProvider;

    private WeatherProvider() {}

    public static WeatherProvider GetProvider()
    {
        if (weatherProvider == null)
            weatherProvider = new WeatherProvider();

        return weatherProvider;
    }

//endregion

//region Weather management

    private final static Random random = new Random(System.currentTimeMillis());

    private final static Weather[] allWeathers = Weather.values();

    public Weather GetCurrentWeather(Coordinates coordinates) {
        int index = Math.abs(random.nextInt());

        return allWeathers[index % allWeathers.length];
    }

//endregion

}
