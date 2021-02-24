package Aircraft;

import Weather.Weather;
import Weather.WeatherTower;
import common.*;

public abstract class Aircraft implements EventListener {

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

//region Moving

    // Weather changing action
    @Override
    public void OnAction()
    {
        Weather weather = weatherTower.GetWeather(coordinates);

        weatherTower.Broadcast(name, messages[weather.ordinal()]);

        UpdatePosition(weatherOffsets[weather.ordinal()]);
    }

    private void Landing()
    {
        weatherTower.Broadcast(name, "coordinates: " + coordinates + ". Landing.");
        weatherTower.Unregister(this);
    }

    void UpdatePosition(Coordinates offset) {
        int longitude = this.coordinates.longitude + offset.longitude;
        int latitude = this.coordinates.latitude + offset.latitude;
        int height = this.coordinates.height + offset.height;

        if (height > 100)
            height = 100;
        if (height <= 0)
            height = 0;

        this.coordinates = new Coordinates(longitude, latitude, height);

        if (height == 0)
            Landing();
    }

//endregion

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
