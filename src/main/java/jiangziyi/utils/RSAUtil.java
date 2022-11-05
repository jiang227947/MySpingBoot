package jiangziyi.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static org.apache.tomcat.util.codec.binary.Base64.*;

public class RSAUtil {
    private static final KeyPair keyPair = initKey();

    /**
     * RSA非对称加密，随机生成密钥对
     */
    private static KeyPair initKey() {
        try {
            // 产生用于安全加密的随机数
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024, new SecureRandom());
            return generator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 产生public key
     */
    public static String getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // getEncoded():返回key的原始编码形式
        return new String(encodeBase64(publicKey.getEncoded()));
    }

    /**
     * RAS非对称加密: 公钥加密
     *
     * @param str 需要加密的字符串
     * @return 密文
     */
    public static String encrypt(String str) {
        //base64编码的公钥
        byte[] decoded = decodeBase64(getPublicKey());
        RSAPublicKey pubKey;
        String outStr = null;
        try {
            pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            outStr = encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException |
                 NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //RSA加密
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str 加密的字符串
     * @return 铭文
     */
    public static String decrypt(String str) {
        //64位解码加密后的字符串
        byte[] inputByte = decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        PrivateKey privateKey = keyPair.getPrivate();
        //base64编码的私钥
        byte[] decoded = privateKey.getEncoded();
        RSAPrivateKey priKey;
        //RSA解密
        Cipher cipher;
        String outStr = null;
        try {
            priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            outStr = new String(cipher.doFinal(inputByte));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException |
                 IllegalBlockSizeException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return outStr;
    }
}
