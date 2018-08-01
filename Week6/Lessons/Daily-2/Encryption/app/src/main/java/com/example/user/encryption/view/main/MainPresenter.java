package com.example.user.encryption.view.main;

import android.util.Log;

import com.example.user.encryption.manager.CipherManager;
import com.example.user.encryption.manager.KeystoreManager;
import com.example.user.encryption.model.Person;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {
    public static final String ALIAS = "something";
    private static final String TAG = MainPresenter.class.getSimpleName() + "_TAG";
    KeystoreManager keystoreManager;
    CipherManager cipherManager;
    MainContract.View view;
    KeyPair keyPair;

    @Inject
    public MainPresenter(KeystoreManager keystoreManager, CipherManager cipherManager) {
        Log.d(TAG, "MainPresenter: ");
        this.keystoreManager = keystoreManager;
        this.cipherManager = cipherManager;
        try {
            this.keyPair = keystoreManager.getKeyPair(ALIAS);
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException | KeyStoreException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void encryptData(Person person) {
        Log.d(TAG, "encryptData: " + person.toString());
        String encryptedData = null;
        try {
            encryptedData = cipherManager.encrypt(person.toString(), keyPair.getPublic());
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        this.view.onEncryption(encryptedData);
    }

    @Override
    public void decryptData(String encyptedData) {
        Log.d(TAG, "decryptData: " + encyptedData);
        String decryptedData = null;
        try {
            decryptedData = cipherManager.decrypt(encyptedData, keyPair.getPrivate());
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "cipherManager.decrypt: " + decryptedData);
        this.view.onDecryption(parsePerson(decryptedData));
    }

    @Override
    public void attachView(MainContract.View view) {
        Log.d(TAG, "attachView: ");
        this.view = view;
    }

    @Override
    public void detachView() {
        Log.d(TAG, "detachView: ");
        this.view = null;
    }

    public Person parsePerson(String rawJson) {
        Log.d(TAG, "parsePerson: " + rawJson);
        try {
            JSONObject jsonObject = new JSONObject(rawJson);
            return new Person(jsonObject.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Person("N/A");
    }
}
