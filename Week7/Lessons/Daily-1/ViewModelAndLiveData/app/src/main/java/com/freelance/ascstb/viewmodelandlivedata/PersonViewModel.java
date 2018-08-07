package com.freelance.ascstb.viewmodelandlivedata;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.freelance.ascstb.viewmodelandlivedata.model.Person;
import com.freelance.ascstb.viewmodelandlivedata.model.PersonDataSource;

public class PersonViewModel extends AndroidViewModel {
    MutableLiveData<Person> personMutableLiveData;
    PersonDataSource personDataSource;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        this.personDataSource = new PersonDataSource(application.getApplicationContext(), this);
    }

    public LiveData getPerson() {
        if (personMutableLiveData == null) {
            personMutableLiveData = new MutableLiveData<>();
            loadPerson();
        }

        return personMutableLiveData;
    }

    public void insertPerson(Person person) {
        personDataSource.insert(person);
    }

    public void updateViewModel(Person person) {
        personMutableLiveData.setValue(person);
    }

    private void loadPerson() {
        personMutableLiveData.setValue(PersonDataSource.getPerson());
    }
}
