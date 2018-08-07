package com.freelance.ascstb.viewmodelandlivedata.model;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.freelance.ascstb.viewmodelandlivedata.PersonViewModel;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class PersonDataSource {
    private static final String TAG = PersonDataSource.class.getSimpleName() + "_TAG";
    private PersonViewModel personViewModel;
    private PersonDatabase personDatabase;
    private PersonDAO personDAO;

    public PersonDataSource(Context context, PersonViewModel personViewModel) {
        this.personViewModel = personViewModel;
        personDatabase = Room.databaseBuilder(context, PersonDatabase.class, "person-database").build();
        personDAO = personDatabase.personDAO();
    }

    public static Person getPerson() {
        return new Person("John Doe", "23", "Male");
    }

    public void insert(final Person person) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                long id = personDAO.updatePerson(person);
                person.setPersonId((int) id);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onComplete() {
                        personViewModel.updateViewModel(person);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }
                });
    }

    public void getAllPerson() {
        personDAO.getAllPerson()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new FlowableSubscriber<List<Person>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(List<Person> personList) {
                        Log.d(TAG, "onNext: ");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "onError: " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    interface OnResults {
        void onPerson(Person person);

        void onPersonList(List<Person> personList);
    }
}
