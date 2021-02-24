package common;

public class Coordinates {

    public final int longitude;
    public final int latitude;
    public final int height;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    @Override
    public String toString()
    {
        return "{" + longitude + ", " + latitude + ", " + height + "}";
    }
}
