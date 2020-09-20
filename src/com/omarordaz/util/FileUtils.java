package com.omarordaz.util;

import com.omarordaz.Main;

import java.io.File;
import java.net.URISyntaxException;

public class FileUtils {
    public static String getJarPath(Class c) throws URISyntaxException {
        return new File(c.getProtectionDomain().getCodeSource().getLocation().toURI()).getName();
    }
}
