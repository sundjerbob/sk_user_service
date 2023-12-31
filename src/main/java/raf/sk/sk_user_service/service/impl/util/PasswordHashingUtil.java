package raf.sk.sk_user_service.service.impl.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashingUtil {
    public static String hashPassword(String password) {
        try {
            // Step 1: Get an instance of the SHA-256 hashing algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Step 2: Compute the hash of the password
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Step 3: Convert the byte array to a hexadecimal string
            StringBuilder hexString = getStringBuilder(hash);

            // Step 5: Return the hexadecimal representation of the hash
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {

            // Step 6: Handle the exception or log it
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private static StringBuilder getStringBuilder(byte[] hash) {

        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            // Convert each byte of the hash to a two-character hexadecimal representation
            String hex = Integer.toHexString(0xff & b);

            // Step 4: Ensure that each byte is represented by two characters
            if (hex.length() == 1) {
                // adding 0 at the beginning has no "weight" to byte value, but ensures format is at least 2 digits/bits.
                hexString.append('0');
            }

            hexString.append(hex);
        }
        return hexString;
    }

    public static boolean matchRawAndHashed(String rawPassword, String hashedPassword) {

        return hashPassword(rawPassword).equals(hashedPassword);

    }


}
