package utils;

import java.util.UUID;

public final class GenerateUUID {
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
