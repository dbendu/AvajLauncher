package Weather;

import Aircraft.Aircraft;
import Weather.WeatherProvider;
import common.Coordinates;
import common.Tower;

import java.util.Iterator;

public class WeatherTower extends Tower {

    private final WeatherProvider provider;

    public WeatherTower() {
        provider = WeatherProvider.GetProvider();
    }

    public Weather GetWeather(Coordinates coordinates) {
        return provider.GetCurrentWeather(coordinates);
    }

    public void UpdateWeatherInfo() {
        int size = aircrafts.size();
        for (int i = 0; i < size; ++i) {
            Aircraft aircraft = aircrafts.get(i);

            provider.ChangeWeather(aircraft.GetCoordinates());
            aircraft.OnAction();
            provider.DeleteCoordinates(aircraft.GetCoordinates());

            if (aircrafts.size() < size) { // means thar aircraft was removed
                i -= 1;
                size -= 1;
            }
        }
    }

    @Override
    public void Register(Aircraft aircraft)
    {
        if (!aircrafts.contains(aircraft)) {
            aircrafts.add(aircraft);
            aircraft.RegisterTower(this);
            Broadcast("Tower", aircraft.GetName() + " registered to weather tower.");
        }
    }
}
