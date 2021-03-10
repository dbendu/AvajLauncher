package Tower;

import Aircraft.Aircraft;
import Flyable.Flyable;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {

//region unused

    @Deprecated
    private List<Flyable> observers = null;

    @Deprecated
    public void Register(Flyable flyable) {

    }

    @Deprecated
    public void Unregister(Flyable flyable) {

    }

    protected void ConditionsChanged() {

    }

//endregion

    protected Radio radio;

    protected final List<Aircraft> aircrafts;

    public Tower() {
        aircrafts = new ArrayList<>();
        radio = new Radio();
    }

//region Registration

    public void Register(Aircraft aircraft)
    {
        if (!aircrafts.contains(aircraft)) {
            aircrafts.add(aircraft);
            Broadcast("Tower", aircraft.GetName() + " registered to weather tower.");
        }
    }

    public void Unregister(Aircraft aircraft)
    {
        if (aircrafts.remove(aircraft)) {
            Broadcast("Tower", aircraft.GetName() + " unregistered from weather tower.");
        }
    }

//endregion

//region Communication

    public void Broadcast(String sender, String message) {
        radio.SendMessage(sender, message);
    }

//endregion

    public List<Aircraft> GetAircrafts()
    {
        return aircrafts;
    }

}
