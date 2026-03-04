package top.andyron.ch03.ch37;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.info("start process....");
        try {
            "".getBytes("invalidCharsetName");
        } catch (UnsupportedEncodingException e) {
            logger.severe("class name: " + Main.class.getName() + ", " + e.getMessage());
        }
        logger.info("end process....");
    }
}
