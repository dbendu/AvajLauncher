package common;

public class Coordinates {

    public final int longitude;
    public final int latitude;
    public final int height;

    private static final int MaxHeight = 100;
    private static final int MinHeight = 0;

    public Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;

        if (height > MaxHeight) {
            this.height = MaxHeight;
        } else if (height < MinHeight) {
            this.height = MinHeight;
        } else {
            this.height = height;
        }
    }

    @Override
    public String toString()
    {
        return "{" + longitude + ", " + latitude + ", " + height + "}";
    }
}
