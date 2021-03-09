package Tower;

import Aircraft.Aircraft;
import Coordinates.Coordinates;
import Weather.Weather;
import Weather.WeatherProvider;

public class WeatherTower extends Tower {

//region unused

    @Deprecated
    public String GetWeatherDep(Coordinates coordinates) {
        return null;
    }

    @Deprecated
    void ChangeWeather() {

    }

//endregion

    private final WeatherProvider provider;

    public WeatherTower() {
        provider = WeatherProvider.GetProvider();
    }

    public Weather GetWeather(Coordinates coordinates) {
        return provider.GetCurrentWeather(coordinates);
    }

    public static final int MaxHeight = 100;
    public static final int MinHeight = 0;

    public void UpdateWeatherInfo() {

        for (int i = 0; i < aircrafts.size(); ++i) {
            Aircraft aircraft = aircrafts.get(i);

            aircraft.OnAction();

            if (aircraft.GetCoordinates().height == MinHeight) {
                Unregister(aircraft);
                i -= 1;
            }
        }
    }

    @Override
    public void Register(Aircraft aircraft) {
        super.Register(aircraft);
        aircraft.RegisterTower(this);
    }
}
