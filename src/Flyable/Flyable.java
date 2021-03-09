package Flyable;

import Tower.WeatherTower;

public interface Flyable {

    void UpdateConditions();

    void RegisterTower(WeatherTower weatherTower);
}
