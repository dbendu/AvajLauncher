package Weather;

import Aircraft.Aircraft;
import common.Coordinates;
import common.EventListener;
import common.Tower;

public class WeatherTower extends Tower {

    private final WeatherProvider provider;

    public WeatherTower() {
        provider = WeatherProvider.GetProvider();
    }

    public Weather GetWeather(Coordinates coordinates) {
        return provider.GetCurrentWeather(coordinates);
    }

    public void UpdateWeatherInfo() {

        for (int i = 0; i < aircrafts.size(); ++i) {
            Aircraft aircraft = aircrafts.get(i);

            aircraft.OnAction(EventListener.Event.WeatherChanged);

            if (aircraft.GetCoordinates().height == Coordinates.MinHeight) {
                aircraft.OnAction(EventListener.Event.Unregistering);

                Unregister(aircraft);

                i -= 1;
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
