package com.example.user.testingapp;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;
import android.util.Log;

import com.example.android.app.Constants;
import com.example.android.app.DataLayer;
import com.example.android.app.provider.contract.ActiveUserContract;

@RunWith(AndroidJUnit4.class)
public class MyContentProdiverTest extends ProviderTestCase2<MyContentProdiver> {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void query() {
    }

    @Test
    public void getType() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }
}