package com.example.user.personsapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.personsapp.Code.Constants;
import com.example.user.personsapp.Code.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etLastName;
    EditText etAge;
    static List<Person> Persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etAge = findViewById(R.id.etAge);
        Persons = new ArrayList<Person>();
    }

    public void onClickedAddPerson(View view) {
        String Message = "Person added correctly";

        String Name = etName.getText().toString();
        String LastName = etLastName.getText().toString();

        if (Name.equals("") || LastName.equals("") || !tryParseInt(etAge.getText().toString())){
            Message = "Please check you inputs.";
            Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
            return;
        }

        int Age = Integer.parseInt(etAge.getText().toString());

        Person p = new Person(Name, LastName, Age);
        Persons.add(p);
        etLastName.setText("");
        etAge.setText("");
        etName.setText("");

        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void onClickedViewPersons(View view) {
        Intent intent = new Intent(getApplicationContext(), ListPersonsActivity.class);
        intent.putParcelableArrayListExtra(Constants.KEY.PERSONS, (ArrayList<? extends Parcelable>) Persons);
        startActivity(intent);
    }
}
