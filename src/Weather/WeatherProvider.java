package Weather;

import common.Coordinates;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

//region Weather management

    private final static Random random = new Random(System.currentTimeMillis());

    private final static Weather[] allWeathers = Weather.values();

    private void AddWeatherPoint(Coordinates coordinates)
    {
        weatherMap.put(coordinates, GenerateWeather());
    }

    private Weather GenerateWeather() {
        int index = Math.abs(random.nextInt());

        return allWeathers[index % allWeathers.length];
    }

    public void DeleteWeatherPoint(Coordinates coordinates) {
        weatherMap.remove(coordinates);
    }

    private Weather GenerateDifferentWeather(Weather weather) {
        Weather newWeather = weather;

        while (newWeather == weather) {
            newWeather = GenerateWeather();
        }

        return newWeather;
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
            Weather newWeather = GenerateDifferentWeather(weatherMap.get(coordinates));
            weatherMap.put(coordinates, newWeather);

            return newWeather;
        }
    }

//endregion



}
