package Weather;

import common.Coordinates;

import java.util.HashMap;
import java.util.Map;

class WeatherProvider {

    private final static Map<Coordinates, Weather> weatherMap = new HashMap<>();

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

//region WeatherGenerating

    private static Weather currentWeather = Weather.Rain;

    private final static Weather[] allWeathers = Weather.values();

    private void AddWeatherPoint(Coordinates coordinates)
    {
        weatherMap.put(coordinates, currentWeather);

        currentWeather = GetNextWeather(currentWeather);
    }

    private Weather GetNextWeather(Weather currentWeather)
    {
        return allWeathers[(currentWeather.ordinal() + 1) % allWeathers.length];
    }

//endregion

//region WeatherManipulations

    public Weather GetCurrentWeather(Coordinates coordinates) {
        if (!weatherMap.containsKey(coordinates)) {
            AddWeatherPoint(coordinates);
        }

        return weatherMap.get(coordinates);
    }

    public Weather ChangeWeather(Coordinates coordinates)
    {
        if (!weatherMap.containsKey(coordinates)) {
            AddWeatherPoint(coordinates);

            return weatherMap.get(coordinates);
        }
        else {
            Weather newWeather = GetNextWeather(weatherMap.get(coordinates));
            weatherMap.put(coordinates, newWeather);

            return newWeather;
        }
    }

//endregion

    public void DeleteCoordinates(Coordinates coordinates) {
        weatherMap.remove(coordinates);
    }

}
