package common;

import Aircraft.Aircraft;
import Weather.WeatherTower;

public class Main {

    public static void main(String[] args) {

        Scenario scenario = null;

        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("Expected file");
            }

            scenario = Scenario.Load(args[0]);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

        WeatherTower tower = new WeatherTower();

        for (Aircraft aircraft : scenario.aircrafts) {
            tower.Register(aircraft);
        }

        for (int i = 0; i < scenario.iterations; ++i) {
            tower.UpdateWeatherInfo();
        }

        while (tower.aircrafts.size() != 0) {
            tower.Unregister(tower.aircrafts.get(0));
        }
    }
}
