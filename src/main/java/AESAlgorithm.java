
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESAlgorithm {
    private static int pwdIterations = 65536;
    private static int keySize = 256;
    private static String keyAlgorithm = "AES";
    private static String encryptAlgorithm = "AES/CBC/PKCS5Padding";
    private static String secretKeyFactoryAlgorithm = "PBKDF2WithHmacSHA256";

    private static String secretKey = "PTLE";
    private static String salt = "AUBAY_PosteItaliane";

    /**
     * Cripta la stringa passata
     *
     * @param strToEncrypt
     * @return
     */
    public static String encrypt(String strToEncrypt) {
        try {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), pwdIterations, keySize);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), keyAlgorithm);

            Cipher cipher = Cipher.getInstance(encryptAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }

        return null;
    }

    /**
     * Decripta la stringa passata
     *
     * @param strToDecrypt
     * @return
     */
    public static String decrypt(String strToDecrypt) {
        try {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), pwdIterations, keySize);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), keyAlgorithm);

            Cipher cipher = Cipher.getInstance(encryptAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }

        return null;
    }
}
