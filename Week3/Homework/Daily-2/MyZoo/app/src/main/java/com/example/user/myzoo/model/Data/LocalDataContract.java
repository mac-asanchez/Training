package com.example.user.myzoo.model.Data;

import android.content.Context;
import android.provider.BaseColumns;

import com.example.user.myzoo.R;

import java.util.ArrayList;
import java.util.List;

public class LocalDataContract {
    public static final int VERSION = 1;

    public static class DDL {
        //region Create Category Table
        public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE "
                + Table.CATEGORY_NAME + "("
                + Category.ID + " INT, "
                + Category.DESCRIPTION + " TEXT)";
        //endregion

        //region Create Animal Table
        public static final String CREATE_ANIMAL_TABLE = "CREATE TABLE "
                + Table.ANIMAL_NAME + "("
                + Animal.ID + " INT, "
                + Animal.CATEGORYID + " INT, "
                + Animal.DESCRIPTION + " TEXT,"
                + Animal.DETAIL + " TEXT, "
                + Animal.SOUND + " TEXT, "
                + Animal.IMAGEURL + " TEXT)";
        //endregion
    }

    public static class DML {
        public static final String GET_ALL_CATEGORIES = "SELECT * FROM " + Table.CATEGORY_NAME;

        //region Get Animals by Category
        public static final String GET_ANIMALS_BY_CATEGORY =
                "SELECT  a." + Animal.ID
                        + ", a." + Animal.CATEGORYID
                        + ", b." + Category.DESCRIPTION
                        + ", a." + Animal.DESCRIPTION
                        + ", a." + Animal.DETAIL
                        + ", a." + Animal.SOUND
                        + ", a." + Animal.IMAGEURL
                        + " FROM " + Table.ANIMAL_NAME + " AS a INNER JOIN "
                        + Table.CATEGORY_NAME
                        + " AS b ON a.CategoryId = b.CategoryId "
                        + "WHERE a.CategoryId = ?";
        //endregion

        public static List<String> INSERT_CATEGORIES_QUERIES() {
            List<String> CategoriesQuery = new ArrayList<>();
            CategoriesQuery.add("INSERT INTO " + Table.CATEGORY_NAME + " (" + Category.ID + "," + Category.DESCRIPTION + ") VALUES (1, 'Mammal') ");
            CategoriesQuery.add("INSERT INTO " + Table.CATEGORY_NAME + " (" + Category.ID + "," + Category.DESCRIPTION + ") VALUES (2, 'Bird') ");
            CategoriesQuery.add("INSERT INTO " + Table.CATEGORY_NAME + " (" + Category.ID + "," + Category.DESCRIPTION + ") VALUES (3, 'Fish') ");
            CategoriesQuery.add("INSERT INTO " + Table.CATEGORY_NAME + " (" + Category.ID + "," + Category.DESCRIPTION + ") VALUES (4, 'Reptile')");

            return CategoriesQuery;
        }

        public static List<String> INSERT_ANIMALS_QUERIES(Context context) {
            List<String> AnimalsQuery = new ArrayList<>();
            //region Mammals
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (1, 1, '" + context.getResources().getString(R.string.bear) + "', '" + context.getResources().getString(R.string.bear_detail) + "', '" + context.getResources().getString(R.string.bear_sound_url) + "', '" + context.getResources().getString(R.string.bear_image_url) + "') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (2, 1, '" + context.getResources().getString(R.string.kangaroo) + "', '" + context.getResources().getString(R.string.kangaroo_detail) + "', '" + context.getResources().getString(R.string.kangaroo_sound_url) + "', '" + context.getResources().getString(R.string.kangaroo_image_url) + "') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (3, 1, '" + context.getResources().getString(R.string.tiger) + "', '" + context.getResources().getString(R.string.tiger_detail) + "', '" + context.getResources().getString(R.string.tiger_sound_url) + "', '" + context.getResources().getString(R.string.tiger_image_url) + "') ");
            //endregion

            //region Birds
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (4, 2, '" + context.getResources().getString(R.string.colibri) + "', '" + context.getResources().getString(R.string.colibri_detail) + "', '" + context.getResources().getString(R.string.colibri_sound_url) + "', '" + context.getResources().getString(R.string.colibri_image_url) + "') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (5, 2, '" + context.getResources().getString(R.string.eagle) + "', '" + context.getResources().getString(R.string.eagle_detail) + "', '" + context.getResources().getString(R.string.eagle_sound_url) + "', '" + context.getResources().getString(R.string.eagle_image_url) + "') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (6, 2, '" + context.getResources().getString(R.string.owl) + "', '" + context.getResources().getString(R.string.owl_detail) + "', '" + context.getResources().getString(R.string.owl_sound_url) + "', '" + context.getResources().getString(R.string.owl_image_url) + "') ");
            //endregion

            //region Fish
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (7, 3, '" + context.getResources().getString(R.string.banded_fish) + "', '" + context.getResources().getString(R.string.banded_fish_detail) + "', '" + context.getResources().getString(R.string.banded_fish_sound_url) + "', '" + context.getResources().getString(R.string.banded_fish_image_url) + "') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (8, 3, '" + context.getResources().getString(R.string.cichlid) + "', '" + context.getResources().getString(R.string.cichlid_detail) + "', '" + context.getResources().getString(R.string.cichlid_sound_url) + "', '" + context.getResources().getString(R.string.cichlid_image_url) + "') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (9, 3, '" + context.getResources().getString(R.string.koi) + "', '" + context.getResources().getString(R.string.koi_detail) + "', '" + context.getResources().getString(R.string.koi_sound_url) + "', '" + context.getResources().getString(R.string.koi_image_url) + "') ");
            //endregion

            //region Reptile
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (10, 4, '" + context.getResources().getString(R.string.crocodile) + "', '" + context.getResources().getString(R.string.crocodile_detail) + "', '" + context.getResources().getString(R.string.crocodile_sound_url) + "', '" + context.getResources().getString(R.string.crocodile_image_url) + "') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (11, 4, '" + context.getResources().getString(R.string.snake) + "', '" + context.getResources().getString(R.string.snake_detail) + "', '" + context.getResources().getString(R.string.snake_sound_url) + "', '" + context.getResources().getString(R.string.snake_image_url) + "') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + "," + Animal.IMAGEURL
                    + ") VALUES (12, 4, '" + context.getResources().getString(R.string.tortoise) + "', '" + context.getResources().getString(R.string.tortoise_detail) + "', '" + context.getResources().getString(R.string.tortoise_sound_url) + "', '" + context.getResources().getString(R.string.tortoise_image_url) + "') ");
            //endregion

            return AnimalsQuery;
        }
    }

    public static class Table {
        public static final String CATEGORY = "Category.db";
        public static final String CATEGORY_NAME = "category";

        public static final String ANIMAL = "Animal.db";
        public static final String ANIMAL_NAME = "animal";
    }

    public static class Category implements BaseColumns {
        public static final String ID = "CategoryId";
        public static final String DESCRIPTION = "CategoryDescription";
    }

    public static class Animal implements BaseColumns {
        public static final String ID = "AnimalId";
        public static final String CATEGORYID = "CategoryId";
        public static final String DESCRIPTION = "AnimalDescription";
        public static final String DETAIL = "Detail";
        public static final String SOUND = "SoundUrl";
        public static final String IMAGEURL = "ImageUrl";
    }
}
