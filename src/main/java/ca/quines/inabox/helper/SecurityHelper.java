package ca.quines.inabox.helper;

import java.util.Base64;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public class SecurityHelper {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        // Note: System.console().readPassword() is better, but Scanner is 
        // more reliable for the built-in Eclipse console view.
        char[] password = scanner.nextLine().toCharArray();

        try {
            // Combine username + ":" + password into one char array
            char[] combined = combine(username.toCharArray(), password);

            // Convert to bytes for Base64 encoding
            byte[] encodedBytes = toBytes(combined);
            String base64Result = Base64.getEncoder().encodeToString(encodedBytes);

            System.out.println("\nYour Header Value:");
            System.out.println("Basic " + base64Result);

            // THE CRITICAL STEP: Wipe the arrays
            Arrays.fill(password, '0');
            Arrays.fill(combined, '0');
            Arrays.fill(encodedBytes, (byte) 0);
        }
        finally {
            scanner.close();
        }
    }

    private static char[] combine(char[] user, char[] pass) {
        char[] result = new char[user.length + 1 + pass.length];
        System.arraycopy(user, 0, result, 0, user.length);
        result[user.length] = ':';
        System.arraycopy(pass, 0, result, user.length + 1, pass.length);
        return result;
    }

    private static byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), 
                       byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(byteBuffer.array(), (byte) 0); // Clear the buffer
        return bytes;
    }

}
