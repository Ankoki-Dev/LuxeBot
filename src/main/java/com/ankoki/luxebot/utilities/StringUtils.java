package com.ankoki.luxebot.utilities;

import java.util.Arrays;

public class StringUtils {

    public static String arrayAsString(String[] args) {
        String argsAsString = "";
        int i = 1;
        for (String s : args) {
            if (i == args.length) {
                argsAsString += s;
            } else {
                argsAsString += s + " ";
            }
            i++;
        }
        return argsAsString;
    }

    public static String[] removeOne(String[] args) {
        return Arrays.copyOfRange(args, 1, args.length);
    }

    public static String getCommandName(String args, String prefix) {
        String[] splitArgs = args.split(" ");
        String cmdName = splitArgs[0].substring(prefix.length());
        return cmdName;
    }


    public static String withoutCommand(String args) {
        try {
            return args.substring((args.split(" ")[0].length() + 1));
        } catch (StringIndexOutOfBoundsException ex) {
            return args.substring((args.split(" ")[0].length()));
        }
    }

}
