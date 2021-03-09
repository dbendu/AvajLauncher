import Aircraft.Aircraft;
import Scenario.Scenario;
import Tower.WeatherTower;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        Scenario scenario = LoadScenario(args);
        WeatherTower tower = new WeatherTower();

        for (Aircraft aircraft : scenario.aircrafts) {
            tower.Register(aircraft);
        }

        for (int i = 0; i < scenario.iterations; ++i) {
            tower.UpdateWeatherInfo();
        }
    }

    public static Scenario LoadScenario(String[] args)
    {
        if (args.length != 2) {
            throw new IllegalArgumentException("Ожидался один аргумент");
        }

        try {
            return Scenario.Load(args[1]);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
            System.exit(1);
        }

        return null; // never reaches
    }
}
