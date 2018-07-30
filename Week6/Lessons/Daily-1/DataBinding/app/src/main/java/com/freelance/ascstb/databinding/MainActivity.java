package com.freelance.ascstb.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.freelance.ascstb.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainBinding.setPerson(new Person("John Doe", "Male", "36"));
        mainBinding.setPersonViewModel(new PersonViewModel());
    }
}
