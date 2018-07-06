package com.example.user.personsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.personsapp.Code.Constants;
import com.example.user.personsapp.Code.Person;

import java.util.ArrayList;

public class ListPersonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_persons);

        ArrayList<Person> Persons = this.getIntent().getParcelableArrayListExtra(Constants.KEY.PERSONS);

        ListView lvPersons = findViewById(R.id.lvPersons);

        ArrayAdapter<Person> arrayAdapter = new ArrayAdapter<Person>(
                this,
                android.R.layout.simple_list_item_1,
                Persons );

        lvPersons.setAdapter(arrayAdapter);
    }
}
