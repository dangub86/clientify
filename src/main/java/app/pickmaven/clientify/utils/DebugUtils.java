package app.pickmaven.clientify.utils;

import java.util.Objects;

public final class DebugUtils {

    public static boolean isDebug() {
        String debug = System.getProperty("debug");
        if(!Objects.isNull(debug)) {
            return new Boolean(debug);
        }
        return false;
    }



}
