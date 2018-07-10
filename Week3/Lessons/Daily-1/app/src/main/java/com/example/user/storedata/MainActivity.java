package com.example.user.storedata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.storedata.model.data.Person;
import com.example.user.storedata.model.data.data.Book;
import com.example.user.storedata.model.data.data.LocalDataSource;
import com.example.user.storedata.model.data.data.RoomHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //region variables
    // Constant values
    private static final String MY_SHARED_PREF = "mySharedPref";
    private static final String ControlKey = "etMain";
    private static final String TAG = "Room_";

    private LocalDataSource localDataSource;

    //Controls
    private TextView tvMain;
    private EditText etMain;
    private EditText etPersonName;
    private EditText etPersonAge;
    private EditText etPersonGender;
    private EditText etBookISBN;
    private EditText etBookName;
    private EditText etBookAuthor;
    private RoomHelper roomHelper;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        localDataSource = new LocalDataSource(this);
        roomHelper = new RoomHelper(this);
    }

    private void bindViews() {
        //region get controls for Shared Preferences
        tvMain = findViewById(R.id.tvMain);
        etMain = findViewById(R.id.etMain);
        //endregion

        //region get controls for Database
        etPersonName = findViewById(R.id.etPersonName);
        etPersonAge = findViewById(R.id.etPersonAge);
        etPersonGender = findViewById(R.id.etPersonGender);
        //endregion

        //region Room
        etBookISBN = findViewById(R.id.etBookISBN);
        etBookName = findViewById(R.id.etBookName);
        etBookAuthor = findViewById(R.id.etBookAuthor);
        //endregion
    }

    public void handleSharedPreferences(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE);

        switch (view.getId()) {
            //region Save Data
            case R.id.btnSaveData:
                String etString = etMain.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(ControlKey, etString);
                boolean isSaved = editor.commit();

                if (isSaved) {
                    Toast.makeText(this, "Saved data", Toast.LENGTH_SHORT).show();
                }

                break;
            //endregion

            //region Get Data
            case R.id.btnGetData:
                String data = sharedPreferences.getString(ControlKey, "default value");
                tvMain.setText(data);
                break;
            //endregion
        }
    }

    public void handlingDataBase(View view) {
        //region Current Person
        Person person = new Person(
                etPersonName.getText().toString(),
                etPersonAge.getText().toString(),
                etPersonGender.getText().toString()
        );
        //endregion

        switch (view.getId()) {
            //region Save Person
            case R.id.btnSavePerson:
                long rowId = localDataSource.savePerson(person);
                if (rowId > 0) {
                    Toast.makeText(this, "Person saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show();
                }

                break;
            //endregion

            //region Get all persons
            case R.id.btnGetAllPersons:
                List<Person> personList = localDataSource.getAllPerson();

                Toast.makeText(this, personList.toString(), Toast.LENGTH_SHORT).show();

                break;
            //endregion
        }
    }

    public void onSaveBook(View view) {
        Book book =  new Book(etBookISBN.getText().toString(),
                etBookName.getText().toString(),
                etBookAuthor.getText().toString());

        Log.d(TAG, "onSaveBook: " + book.toString());

        roomHelper.saveBook(book);
    }

    public void onGetBooks(View view) {
        roomHelper.getBooks();
    }
}
