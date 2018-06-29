package com.example.user.activitylifecicleandintent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.user.activitylifecicleandintent.model.Person;
import com.example.user.activitylifecicleandintent.model.PersonP;
import com.example.user.activitylifecicleandintent.utils.Constants;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Person person = (Person) getIntent().getSerializableExtra(Constants.KEY.PERSON);
        PersonP personP = getIntent().getParcelableExtra(Constants.KEY.PERSON_P);

        Toast.makeText(this, person.getName(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, personP.getName(), Toast.LENGTH_SHORT).show();
    }
}
