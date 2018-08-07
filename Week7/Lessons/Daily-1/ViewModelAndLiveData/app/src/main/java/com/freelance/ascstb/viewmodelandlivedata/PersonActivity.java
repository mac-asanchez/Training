package com.freelance.ascstb.viewmodelandlivedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.freelance.ascstb.viewmodelandlivedata.model.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends AppCompatActivity {
    private static final String TAG = PersonActivity.class.getSimpleName() + "_TAG";

    //region Butter Knife injection
    @BindView(R.id.etPersonName)
    EditText etPersonName;
    @BindView(R.id.etPersonAge)
    EditText etPersonAge;
    @BindView(R.id.etPersonGender)
    EditText etPersonGender;
    @BindView(R.id.btnUpdatePerson)
    Button btnUpdatePerson;
    @BindView(R.id.tvPersonName)
    TextView tvPersonName;
    //endregion

    private PersonViewModel personViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //UI Dependency injection
        ButterKnife.bind(this);

        //get the view model from the view model providers
        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);

        personViewModel.getPerson().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                Person person = (Person) o;
                tvPersonName.setText(person.toString());
            }
        });
    }

    @OnClick(R.id.btnUpdatePerson)
    public void onViewClicked() {
        String personName = etPersonName.getText().toString();
        String personAge = etPersonAge.getText().toString();
        String personGender = etPersonGender.getText().toString();

        Person person = new Person(personName, personAge, personGender);
        personViewModel.insertPerson(person);
    }


}
