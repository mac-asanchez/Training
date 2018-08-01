package com.example.user.encryption.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.encryption.MainApplication;
import com.example.user.encryption.R;
import com.example.user.encryption.model.Person;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    @Inject
    MainPresenter presenter;
    private EditText etPersonName;
    private TextView tvEncryptedPerson;
    private TextView tvDecryptedPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");
        etPersonName = findViewById(R.id.etPersonName);
        tvEncryptedPerson = findViewById(R.id.tvEncryptedPerson);
        tvDecryptedPerson = findViewById(R.id.tvPersonDecrypted);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MainApplication.get(this).getMainComponent().inject(this);
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        MainApplication.get(this).clearMainComponent();
        presenter.detachView();
    }

    public void onEncryptPerson(View view) {
        Log.d(TAG, "onEncryptPerson: ");
        presenter.encryptData(new Person(etPersonName.getText().toString()));
    }

    public void onDecryptPerson(View view) {
        Log.d(TAG, "onDecryptPerson: ");
        presenter.decryptData(tvEncryptedPerson.getText().toString());
    }

    @Override
    public void onEncryption(String encryptedData) {
        Log.d(TAG, "onEncryption: ");
        tvEncryptedPerson.setText(encryptedData);
    }

    @Override
    public void onDecryption(Person person) {
        Log.d(TAG, "onDecryption: ");
        tvDecryptedPerson.setText(person.getName());
    }

    @Override
    public void showError(String error) {
        Log.d(TAG, "showError: " + error);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
