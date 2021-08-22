package utils;

import settings.ReadProperties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SavePrices {

    /**
     * Append new line to prices.txt file
     * Format e.g. 2021/08/22 20:52:46 | Prices: from £25410 to £32410
     * @param minValue
     * @param maxValue
     * @throws IOException
     */
    public static void addPricesToTextFile(int minValue, int maxValue) throws IOException {
        BufferedWriter out = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try {
            FileWriter fstream = new FileWriter(ReadProperties.configPath + "/prices.txt", true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write(dtf.format(now) + " | Prices: from £" + minValue + " to £" + maxValue);
        }

        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        finally {
            if(out != null) {
                out.close();
            }
        }
    }
}
