package core.org.shmurda.core.util;

import core.me.tomraylord.log5j.LogLevel;
import core.me.tomraylord.log5j.Logger;
import core.org.shmurda.core.Core;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

@UtilityClass
public class TokenUtil {

    /**
     * Code modified from https://github.com/PolyMarsDev/Sokobot
     *
     * @throws IOException Error when creating file.
     */
    public void loadTokenFromFile() throws IOException {
        File file = Paths.get("token.txt").toFile();

        Logger logger = Core.getInstance().getLogger();

        if (!file.exists()) {
            logger.log(LogLevel.WARN, "File token.txt does not exist,");
            logger.log(LogLevel.INFO, "Paste your token in here: ");

            Scanner scanner = new Scanner(System.in);

            Core.getInstance().setBotToken(scanner.nextLine());
            logger.log(LogLevel.INFO, "Creating token.txt...");

            if (!file.createNewFile()) {
                logger.log(LogLevel.ERROR, "Couldn't create token.txt, you will have to manually do this.");
            } else {
                Files.write(file.toPath(), Core.getInstance().getBotToken().getBytes());
            }

            scanner.close();
        } else {
            Core.getInstance().setBotToken(new String(Files.readAllBytes(file.toPath())));
        }
    }

}
