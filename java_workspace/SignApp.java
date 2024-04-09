import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author xxx
 * @date 20220726
 */
public class SignApp {

    public static void main(String[] args) {
        try {
            //Modify appId、tenantId、tenantSecret to your tenant information
            Integer appId = Integer.parseInt(args[0]);
            System.out.println("APP_ID: " + appId);
            Integer tenantId = Integer.parseInt(args[1]);
            System.out.println("TENANT_ID: " + tenantId);
            String tenantSecret = args[2];
            System.out.println("TENANT_SECRET: " + tenantSecret);

            System.out.println("SIGNATURE: " + sign(appId,tenantId,tenantSecret));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sign(Integer appId, Integer tenantId, String tenantSecret) throws Exception {
        long nowMillis = System.currentTimeMillis();
        String signJson = "{\"appId\":" + appId + ",\"signTime\":" + nowMillis + ",\"tenantId\":" + tenantId + ",\"tenantSecret\":\"" + tenantSecret + "\"}";
        // Generate signature
        String signString = Base64.getEncoder().encodeToString(signJson.getBytes(StandardCharsets.UTF_8));
        byte[] input = signString.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(tenantSecret.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skc);
        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        cipher.doFinal(cipherText, ctLength);
        StringBuilder sb = new StringBuilder();
        for (byte b : cipherText) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}