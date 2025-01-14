package pub.synx.util;

import pub.synx.pojo.po.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author SynX TA
 * @version 2024
 **/
public class PasswordHelper {
    private final static SecureRandom secureRandom = new SecureRandom();

    /**
     * Encrypt password.
     *
     * @param user the user
     */
    public static void encryptPassword(User user) {
        if (StringUtils.isEmpty(user.getSalt())) {
            user.setSalt(generateSalt());
        }
        String newPassword = hashPassword(user.getPassword(), user.getSalt());
        user.setPassword(newPassword);
    }

    public static String encryptPassword(String password, String salt) {
        return hashPassword(password, salt);
    }

    private static String generateSalt() {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return bytesToHex(salt);
    }

    private static String hashPassword(String password, String salt) {
        try {
            String algorithmName = "MD5";
            MessageDigest md = MessageDigest.getInstance(algorithmName);
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());
            int hashIterations = 3;
            for (int i = 1; i < hashIterations; i++) {
                md.reset();
                hashedPassword = md.digest(hashedPassword);
            }
            return bytesToHex(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static Character getChar(char index) {
        Random rand = new Random();
        char upper = (char) (65 + rand.nextInt(26));
        char lower = (char) (97 + rand.nextInt(26));
        char number = (char) (48 + rand.nextInt(10));
        char underline = '_';
        return switch (index) {
            case '1' -> upper;
            case '2' -> lower;
            case '3' -> number;
            case '4' -> underline;
            default -> null;
        };
    }
}