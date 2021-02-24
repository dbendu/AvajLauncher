package common;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Radio {

    final OutputStream out = System.out;

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
