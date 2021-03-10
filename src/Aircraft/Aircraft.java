package Aircraft;

import Coordinates.Coordinates;
import EventListener.EventListener;
import Flyable.Flyable;
import Weather.Weather;
import Tower.WeatherTower;

public abstract class Aircraft implements EventListener, Flyable {

//region unused

    @Override
    @Deprecated
    public void UpdateConditions() {

    }

//endregion

//region Id management

    private static long uid = 0L;

    private long NextId() {
        return uid++;
    }

//endregion

    protected AircraftType type;

    protected Coordinates coordinates;
    public Coordinates GetCoordinates() {
        return coordinates;
    }

    protected String name;
    public String GetName() {
        return name;
    }

    protected WeatherTower weatherTower;

    protected String[] messages = null;
    abstract protected void InitMessagesList();

    protected Coordinates[] weatherOffsets = null;
    abstract protected void InitWeatherOffsetsList();

    protected Aircraft(AircraftType type, String name, Coordinates coordinates)
    {
        this.type = type;
        this.name = type.name() + '#' + name + '(' + NextId() + ')';
        this.coordinates = coordinates;

        InitMessagesList();
        InitWeatherOffsetsList();
    }

    public void RegisterTower(WeatherTower tower) {
        weatherTower = tower;
    }

    public void UnregisterTower() {
        weatherTower = null;
    }

    @Override
    public void OnAction()
    {
        Weather weather = weatherTower.GetWeather(coordinates);

        weatherTower.Broadcast(name, messages[weather.ordinal()]);

        UpdatePosition(weatherOffsets[weather.ordinal()]);

        if (coordinates.height == WeatherTower.MinHeight) {
            Landing();
            UnregisterTower();
        }
    }

    private void Landing()
    {
        weatherTower.Broadcast(name, "My coordinates: " + coordinates + ". Landing.");

        UnregisterTower();
    }

    private void UpdatePosition(Coordinates offset) {
        int longitude = this.coordinates.longitude + offset.longitude;
        int latitude = this.coordinates.latitude + offset.latitude;
        int height = this.coordinates.height + offset.height;

        // correcting
        if (height > WeatherTower.MaxHeight) {
            height = WeatherTower.MaxHeight;
        } else if (height < WeatherTower.MinHeight) {
            height = WeatherTower.MinHeight;
        }

        this.coordinates = new Coordinates(longitude, latitude, height);
    }

//region Aircraft fabric

    public enum AircraftType
    {
        Helicopter,
        JetPlane,
        Balloon
    }

    public static Aircraft NewAircraft(AircraftType type, String name, Coordinates coordinates)
    {
        switch (type)
        {
            case Helicopter:
                return new Helicopter(name, coordinates);

            case JetPlane:
                return new JetPlane(name, coordinates);

            case Balloon:
                return new Balloon(name, coordinates);

            default:
                throw new UnsupportedOperationException("Type " + type + " doesnt supports");
        }
    }

//endregion

}
