package Scenario;

import Aircraft.Aircraft;
import Coordinates.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scenario {

    public final int iterations;
    public final List<Aircraft> aircrafts;

    public Scenario(int iterations, List<Aircraft> aircrafts) {
        this.iterations = iterations;
        this.aircrafts = aircrafts;
    }

//region Read from file

    public static Scenario Load(String file) throws IllegalArgumentException, FileNotFoundException {

        Scanner scanner = new Scanner(new File(file));

        String iters = scanner.nextLine();
        if (IsNumeric(iters) == false) {
            throw new IllegalArgumentException("Expected integer: " + iters);
        }

        List<Aircraft> aircrafts = new ArrayList<>();

        while (scanner.hasNext())
        {
            String line = scanner.nextLine();
            if (line.isEmpty() || line.charAt(0) == '#') {
                continue;
            }

            Aircraft aircraft = CreateAircraftByDescription(line);
            aircrafts.add(aircraft);
        }
        scanner.close();

        return new Scenario(Integer.parseInt(iters), Collections.unmodifiableList(aircrafts));
    }

//region Creation

    private static Aircraft CreateAircraftByDescription(String description) throws IllegalArgumentException {
        String[] tokens = description.split(" ");

        if (tokens.length != 5)
            throw new IllegalArgumentException("Expected five tokens: " + description);

        String type = tokens[0];
        String name = tokens[1];
        String longitude = tokens[2];
        String latitude = tokens[3];
        String height = tokens[4];

        AssertTypeSupported(type);
        AssertCoordinateValid(longitude, latitude, height);

        Coordinates coordinates = new Coordinates(Integer.parseInt(longitude),
                                                  Integer.parseInt(latitude),
                                                  Integer.parseInt(height));

        return Aircraft.NewAircraft(Aircraft.AircraftType.valueOf(type), name, coordinates);
    }

    private static void AssertTypeSupported(String type)  {
        for (Aircraft.AircraftType supportedType : Aircraft.AircraftType.values()) {
            if (supportedType.name().equals(type)) {
                return;
            }
        }

        throw new IllegalArgumentException("Unsupported aircraft type: " + type);
    }

    private static void AssertCoordinateValid(String longitude, String latitude, String height) {
        if (IsNumeric(longitude) == false || IsNumeric(latitude) == false || IsNumeric(height) == false) {
            throw new IllegalArgumentException("Coordinates must been a positive integers:" +
                    "{" + longitude + ", " + latitude + ", " + height + "}");
        }
    }

    private static boolean IsNumeric(String str) {
        Pattern pattern = Pattern.compile("^\\d+$");

        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }

//endregion

//endregion
}
