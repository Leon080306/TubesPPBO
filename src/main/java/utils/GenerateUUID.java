package utils;

import java.util.UUID;

public final class GenerateUUID {
    public static String generateUUID() {
        return UUID.randomUUID().toString(); // selalu 36 karakter
    }
}
