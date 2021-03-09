package Weather;

import Coordinates.Coordinates;

import java.util.Random;

public class WeatherProvider {

//region unused

    @Deprecated
    private static String[] weather = null;

    @Deprecated
    public String GetCurrentWeatherDep(Coordinates coordinates) {
        return "";
    }

//endregion

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
