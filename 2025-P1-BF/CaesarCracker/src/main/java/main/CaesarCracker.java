package main;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Brute force cracker for keyed Caesar encoding.
 *
 * @author aguirre
 *
 */
public class CaesarCracker {

    /**
     * Number of character codes (0..NUMCODES-1).
     * Number 128 corresponds to standard ASCII.
     */
    public static final int NUMCODES = 128;

    /**
     * maximum length to be tried in password cracking.
     */
    private int passwordLength;

    /**
     * known word that belongs to the original message, prior to encryption.
     */
    private String messageWord;

    /**
     * message after encryption, to be used for password cracking.
     */
    private String encryptedMessage;

    /**
     * key found after decryption. It's null if nothing was found, or decryption
     * never run.
     */
    private int[] foundKey = null;

    /**
     * Default constructor. Sets both known message word and encrypted message to
     * "".
     * Maximum password length to be tried is set to 1.
     */
    public CaesarCracker() {
        messageWord = "";
        encryptedMessage = "";
        passwordLength = 1;
    }

    /**
     * Constructor that receives both known message word and encrypted message.
     * Maximum password length to be tried is set to 1.
     *
     * @param message is the encrypted message to work on.
     * @param word    is the word known to belong to the decrypted message.
     */
    public CaesarCracker(final String message, final String word) {
        if (word == null)
            throw new IllegalArgumentException("null word");
        if (!isPureAscii(word))
            throw new IllegalArgumentException("non-ascii word");
        if (message == null)
            throw new IllegalArgumentException("null encrypted message");
        if (!isPureAscii(message))
            throw new IllegalArgumentException("non-ascii encrypted message");
        if (word.length() > message.length())
            throw new IllegalArgumentException("word longer than message");
        this.encryptedMessage = message;
        this.messageWord = word;
        this.passwordLength = 1;
    }

    /**
     * Returns the maximum length to be tried in password cracking.
     * For instance, if such length is 1, then all
     * passwords of length 1 (values from 0 to NUMCODES-1) will be tried as
     * passwords for cracking.
     *
     * @return the maximum length to be tried for password cracking.
     */
    public final int getPasswordLength() {
        return passwordLength;
    }

    /**
     * Sets the maximum length of the password to be tried for deciphering the
     * encrypted message.
     *
     * @param length is the new maximum length for the passwords to try for
     *               cracking.
     */
    public final void setPasswordLength(final int length) {
        if (length <= 0)
            throw new IllegalArgumentException("password length must be positive");
        this.passwordLength = length;
    }

    /**
     * Returns the known word of the message previous to encryption. This word is
     * used to test whether a given
     * password is able to decrypt the encrypted message.
     *
     * @return the known word of the unencrypted message.
     */
    public final String getMessageWord() {
        return messageWord;
    }

    /**
     * Sets the known word from the unencrypted message. A valid decryption will be
     * one that, applied to the
     * encrypted message leads to an unencrypted message containing the known word.
     *
     * @param word is the known word from the unencrypted message.
     */
    public final void setMessageWord(final String word) {
        if (word == null)
            throw new IllegalArgumentException("invalid known word (null)");
        if (word.length() > this.encryptedMessage.length())
            throw new IllegalArgumentException("invalid known word (longer than message)");
        if (!isPureAscii(word))
            throw new IllegalArgumentException("non-ascii word");
        this.messageWord = word;
    }

    /**
     * Returns the encrypted message, that to be cracked.
     *
     * @return the encrypted message.
     */
    public final String getEncryptedMessage() {
        return encryptedMessage;
    }

    /**
     * Sets the encrypted message, to be "decrypted" by brute force.
     *
     * @param message is the encrypted message to set for the cracker.
     */
    public final void setEncryptedMessage(final String message) {
        if (message == null)
            throw new IllegalArgumentException("invalid encrypted message (null)");
        if (message.length() < this.messageWord.length())
            throw new IllegalArgumentException("invalid message (shorter than known word)");
        if (!isPureAscii(message))
            throw new IllegalArgumentException("non-ascii encrypted message");
        this.encryptedMessage = message;
    }

    /**
     * Encodes message with a given key.
     *
     * @param message is the message to be encoded.
     * @param key     is the key used for encoding, given as an array of integer
     *                values (from 0 to NUMCODES-1).
     * @return the message encoded with the provided key.
     */
    public static String encode(final String message, final int[] key) {
        if (key == null || key.length == 0 || message == null)
            throw new IllegalArgumentException("empty/null key or null message");
        int currKeyIndex = 0;
        String output = "";
        for (int i = 0; i < message.length(); i++) {
            if (key[currKeyIndex] < 0 || key[currKeyIndex] >= NUMCODES)
                throw new IllegalArgumentException("invalid key");
            int code = (int) message.charAt(i);
            code = (code + key[currKeyIndex]) % NUMCODES; // assumed ascii encoding
            char outChar = Character.toChars(code)[0];
            output = output + outChar;
            currKeyIndex = (currKeyIndex + 1) % key.length;
        }
        return output;
    }

    /**
     * Decodes message with a given key.
     *
     * @param message is the message to be decoded.
     * @param key     is the key used for decoding, given as an array of integer
     *                values (from 0 to NUMCODES-1).
     * @return the message decoded with the provided key.
     */
    public static String decode(final String message, final int[] key) {
        if (key == null || key.length == 0 || message == null)
            throw new IllegalArgumentException("empty/null key or null message");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int charCode = message.charAt(i);
            int keyIndex = i % key.length;
            int decodedCode = (charCode - key[keyIndex] + NUMCODES) % NUMCODES;
            result.append((char) decodedCode);
        }
        return result.toString();
    }

    /**
     * Attempts to decode encrypted message with the given key.
     * Brute force decryption tries to find a key
     * of at most this.passwordLength values (each from 0 to NUMCODES-1) such that
     * the decryption of the encrypted
     * message leads to a decrypted text that contains this.messageWord.
     *
     * @return true iff brute force decryption succeeded.
     */
    public final boolean bruteForceDecrypt() {
        int[] inventedKey = new int[passwordLength]; // Inventa una llave para desencriptar
        Arrays.fill(inventedKey, 0); // Llena el arreglo con ceros
        while (true) {
            StringBuilder result = new StringBuilder(); // Inicialización de la cadena resultado
            for (int i = 0; i < encryptedMessage.length(); i++) {
                int charCode = encryptedMessage.charAt(i); // Número ascii del caracter capturado del mensaje encriptado
                int keyIndex = i % inventedKey.length; // El índice de la llave tiene módulo la longitud de la llave,
                                                       // haciendo que cuando termina la longitud vuelve a empezar
                int decodedCode = (charCode - inventedKey[keyIndex] + NUMCODES) % NUMCODES; // Transforma un caracter
                                                                                            // "decodificado" a su
                                                                                            // numero ascii
                                                                                            // correspondiente
                result.append((char) decodedCode); // Agrega el caracter obtenido a la string resultado
                // Si la cadena resultante es igual al mensaje (original sin codificar) el
                // método termina y actualiza el atributo foundKey
                if (result.toString().equals(messageWord)) {
                    foundKey = Arrays.copyOf(inventedKey, inventedKey.length); // Actualiza el atributo con la llave
                                                                               // inventada y su longitud
                    return true; // La cadena resultante y el mensaje coinciden
                }
            }
            // Flag del ciclo while, se inicializa true si se terminó el for anterior
            // (que tiene como funcion principal armar la cadena resultante)
            // La idea del ciclo es probar todas las combinaciones posibles de claves:
            // Si el inventedKey[i] llegó al final del código ascii, lo pone en 0 para
            // volver a empezar, así con toda la longitud del inventedKey
            boolean endOfAscii = true;
            for (int i = inventedKey.length - 1; i >= 0; i--) {
                if (endOfAscii) {
                    inventedKey[i]++;
                    if (inventedKey[i] == NUMCODES) {
                        inventedKey[i] = 0;
                    } else {
                        endOfAscii = false;
                    }
                }
            }
            // Cuando ya no se pueda entrar al for, también se termina el while grande
            // (inventedKey.length deberia dar 0)
            // y entonces endOfAscii va a quedar en true, provocando el break de abajo.
            if (endOfAscii) {
                break;
            }
        }
        // Como se salió enteramente del while y no se pudo devolver una cadena que sea
        // igual al mensaje, seteamos en null foundKey y retornamos false.
        foundKey = null;
        return false;
    }

    /**
     * Returns the key found by the last brute force decryption. Method returns
     * null if brute force decryption was never executed, or if key was not found.
     *
     * @return key found by brute force decryption (null if not found / decryption
     *         not executed)
     */
    public final int[] foundKey() {
        return this.foundKey;
    }

    /**
     * Tests whether a string is pure ascii.
     *
     * @param s is the string being analyzed
     * @return true iff s is pure ascii
     */
    public static boolean isPureAscii(final String s) {
        if (s == null)
            throw new IllegalArgumentException("can't check isPureAscii on null");
        return Charset.forName("US-ASCII").newEncoder().canEncode(s);
    }

}
