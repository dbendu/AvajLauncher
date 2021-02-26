package common;

public interface EventListener {

    enum Event
    {
        WeatherChanged,
        Unregistering
    }

    void OnAction(Event event);
}
