package com.example.cron.Constants;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static String SLASH = "/";
    public static  String DASH = "-";
    public static String COMMA = ",";
    public static String STAR = "*";

    public static List<String> SPECIAL_CHARACTERS = new ArrayList<>() {
        {
            add(SLASH);
            add(DASH);
            add(COMMA);
            add(STAR);
        }
    };
}
