package com.example.user.contentprovider;

import android.database.sqlite.SQLiteOpenHelper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ProductContractTest {

    @Test
    public void TestCreateTable() {
        System.out.println(ProductContract.SQL_CREATE_ENTRIES);
    }

    @Test
    public void TestInitialLoad() {
        List<String> tblProducts = ProductContract.SQL_INSERT_PRODUCTS_INITIAL_LOAD();

        for (String row : tblProducts) {
            System.out.println(row);
        }
    }
}