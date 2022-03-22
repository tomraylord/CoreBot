package core.me.tomraylord.log5j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Code taken from an old project of mine, I present you log5j.
 * Probably the greatest logger of all time.
 *
 * (no RCE here, looking at you apache)
 */
public class Logger {

    private PrintStream output;

    public Logger() {
        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setup() throws IOException {
        File log = new File("logs/log-" + System.currentTimeMillis() + ".txt");

        log.getParentFile().mkdirs();
        log.createNewFile();

        output = new PrintStream( new FileOutputStream(log) );
    }

    public void log(LogLevel logLevel, String message) {
        String out = logLevel.getPrefix() + message;

        System.out.println(out);
        output.println(out);

        if (logLevel.isStop()) {
            System.exit(0);
        }
    }

}
