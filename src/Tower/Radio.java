package Tower;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import static java.lang.System.exit;

public class Radio {

    private static OutputStream out;

    static {
        try {
            out = new FileOutputStream("simulation.txt");
        } catch (Exception ex) {
            ex.printStackTrace();
            exit(1);
        }
    }
    public void SendMessage(String sender, String message) {
        try {
            String fullMessage = sender + " says: " + message;

            out.write(fullMessage.getBytes(StandardCharsets.UTF_8));
            out.write('\n');

            out.flush();
        }
        catch (Exception ignored) {
            // losses happens
        }
    }
}
