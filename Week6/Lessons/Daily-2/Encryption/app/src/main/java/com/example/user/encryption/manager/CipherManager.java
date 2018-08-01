package com.example.user.encryption.manager;

import android.util.Base64;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;

public class CipherManager {
    public static final String TRANSOFRMATION = "RSA/ECB/PKCS1Padding";
    private static final String TAG = CipherManager.class.getSimpleName() + "_TAG";
    Cipher cipher;

    public CipherManager() throws NoSuchPaddingException, NoSuchAlgorithmException {
        Log.d(TAG, "CipherManager: ");
        cipher = Cipher.getInstance(TRANSOFRMATION);
    }

    public String encrypt(String plainText, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Log.d(TAG, "encrypt: " + plainText);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    public String decrypt(String cipherText, Key key) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Log.d(TAG, "decrypt: " + cipherText);
        cipher.init(Cipher.DECRYPT_MODE, key);
        Log.d(TAG, "decrypt: 2");
        byte[] encryptedBytes = Base64.decode(cipherText, Base64.DEFAULT);
        Log.d(TAG, "decrypt: 3");
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        Log.d(TAG, "decrypt: 4");
        String Result = new String(decryptedBytes);
        Log.d(TAG, "decrypt: Result: " + Result);
        return Result;
    }
}
