package common;

import Aircraft.Aircraft;
import Weather.WeatherTower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<Aircraft> aircrafts = new ArrayList<>();

        WeatherTower tower = new WeatherTower();

        for (int i = 0; i < 1; ++i)
        {
            Aircraft aircraft1 = Aircraft.NewAircraft(Aircraft.AircraftType.Helicopter, "Helicopter", new Coordinates(i, i, i));
            Aircraft aircraft2 = Aircraft.NewAircraft(Aircraft.AircraftType.Balloon, "Balloon", new Coordinates(i, i, i));
            Aircraft aircraft3 = Aircraft.NewAircraft(Aircraft.AircraftType.JetPlane, "JetPlane", new Coordinates(i, i, i));

            aircrafts.add(aircraft1);
            aircrafts.add(aircraft2);
            aircrafts.add(aircraft3);

            tower.Register(aircraft1);
            tower.Register(aircraft2);
            tower.Register(aircraft3);
        }

        for (int i = 0; i < 3; ++i) {
            tower.UpdateWeatherInfo();
        }



    }
}
