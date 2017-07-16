package in.vagabond.robotics.lego;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by asaxena on 10/14/16.
 */
public class FileUtil {

    public static List<String> getLines(File file) throws IOException {

        List<String> strings = FileUtils.readLines(file, "UTF8");

        return strings;

    }

    public static List<String> getLinesFromFile(String filename) throws IOException {
        File file = new File(filename);
        return getLines(file);
    }



}
