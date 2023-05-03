package server.hashs;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class RSAService {
    static PrivateKey privateKey;
    static PublicKey publicKey;
    static final String PRIVATE_KEY_FILE = "security/private.key";
    static final String PUBLIC_KEY_FILE = "security/public.key";

    public static void generateKeyPair() throws NoSuchAlgorithmException, IOException {
        File privateKeyFile = new File(PRIVATE_KEY_FILE);
        File publicKeyFile = new File(PUBLIC_KEY_FILE);
        if (privateKeyFile.exists() && publicKeyFile.exists()) {
            System.out.println("Private and public key already exist");
            return;
        }
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();

        Path privateKeyPath = Paths.get("security/private.key");
        byte[] privateKeyBytes = privateKey.getEncoded();
        Files.createDirectories(privateKeyPath.getParent());
        Files.write(privateKeyPath, privateKeyBytes);

        Path publicKeyPath = Paths.get("security/public.key");
        byte[] publicKeyBytes = publicKey.getEncoded();
        Files.createDirectories(publicKeyPath.getParent());
        Files.write(publicKeyPath, publicKeyBytes);
    }

    public static void readPrivateKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Path path = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(path);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        privateKey = keyFactory.generatePrivate(keySpec);
    }

    public static void readPublicKey(String fileName) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Path path = Paths.get(fileName);
        byte[] bytes = Files.readAllBytes(path);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(keySpec);
    }

    public static String encrypt(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedText) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] encryptedData = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedData = cipher.doFinal(encryptedData);
            return new String(decryptedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }

    static {
        try {
            generateKeyPair();
            readPrivateKey(PRIVATE_KEY_FILE);
            readPublicKey(PUBLIC_KEY_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Test {
    public static void main(String[] args) throws Exception {
        String encryptedText = RSAService.encrypt("Hello World");
        System.out.println(encryptedText);
        String decryptedText = RSAService.decrypt("Zek3r14oGyKAZ/X95uNKUadEIKjzWp/Wv7987r9ftkSkUXvJ7/PAVtJLYe1VLxW6gZosGohZJCZ5AEJoNDUx5S8kk+PGmhiCJc0+8RgjQW+2luPRSmfrKEAFFXDSMBj2hFb1tsOIG0u430HUjge2rIb0fq2274ZrKjMCF0ZvdEGr9UvH6aFyIDgdEoiN0SD61yeYXrf757r9/HIhkoEvYueJj2WOYokJqckoUW0mxY8nYwKkNDg6jqSNrPF8Kqk9Hud2gC7yBuXQKOUC8ph7fZi4LABEh46EPI4DEAxogejQ27GBJ0qpFP1cAV+lRgi1eu8lIdMyfPdLRYdGcGmEjw==");
        System.out.println(decryptedText);
    }
}