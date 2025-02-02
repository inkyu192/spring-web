package spring.web.java.global.common;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESCryptoUtils {

	private final SecretKeySpec secretKeySpec;

	public AESCryptoUtils(String secretKey) {
		byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		this.secretKeySpec = new SecretKeySpec(keyBytes, "AES");
	}

	public String encrypt(String plainText) {
		try {
			Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}

	public String decrypt(String encryptedText) {
		try {
			Cipher cipher = getCipher(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
			return new String(decryptedBytes, StandardCharsets.UTF_8);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}

	private Cipher getCipher(int mode, SecretKey secretKeySpec) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(mode, secretKeySpec);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			throw new RuntimeException(e);
		}

		return cipher;
	}
}
