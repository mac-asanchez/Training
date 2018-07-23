package com.example.user.firebase.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.firebase.data.model.Person;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RemoteDatabaseManager {
    private static final String TAG = RemoteDatabaseManager.class.getSimpleName() + "_TAG";
    private static RemoteDatabaseManager instance = null;
    FirebaseDatabase database;

    private RemoteDatabaseManager() {

    }

    public static RemoteDatabaseManager getInstance() {
        if (instance == null) {
            instance = new RemoteDatabaseManager();
        }
        return instance;
    }

    public void uploadData(String data) {
        Log.d(TAG, "uploadData: ");
        database = FirebaseDatabase.getInstance();
        Log.d(TAG, "uploadData: 2");
//        create a reference
        DatabaseReference homeReference = database.getReference("home");
        Log.d(TAG, "uploadData: 3");
        homeReference.setValue(data);
        Log.d(TAG, "uploadData: 4");
    }

    public void uploadPerson(Person person) {
        database = FirebaseDatabase.getInstance();

//        create a reference for person
        DatabaseReference personRef = database.getReference("people");
        personRef.push().setValue(person);//push creates a new references under the main reference

    }

    public void readPeople() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference personRef = database.getReference("people");

        personRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Person person = snapshot.getValue(Person.class);
                    Log.d(TAG, "onDataChange: " + person.getFirstName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
