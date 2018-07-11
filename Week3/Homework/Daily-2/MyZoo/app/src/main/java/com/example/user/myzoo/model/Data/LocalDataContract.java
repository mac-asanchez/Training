package com.example.user.myzoo.model.Data;

import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class LocalDataContract {
    public static final int VERSION = 1;

    public static class DDL {
        public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE "
                + Table.CATEGORY_NAME + "("
                + Category.ID + " INT, "
                + Category.DESCRIPTION + " TEXT)";

        public static final String CREATE_ANIMAL_TABLE = "CREATE TABLE "
                + Table.ANIMAL_NAME + "("
                + Animal.ID + " INT, "
                + Animal.CATEGORYID + " INT, "
                + Animal.DESCRIPTION + " TEXT,"
                + Animal.DETAIL + " TEXT, "
                + Animal.SOUND + " TEXT)";
    }

    public static class DML {
        public static final String GET_ALL_CATEGORIES = "SELECT * FROM " + Table.CATEGORY_NAME;
        public static final String GET_ANIMALS_BY_CATEGORY = "SELECT a.AnimalId, a.CategoryId, b.CategoryDescription, a.AnimalDescription, a.Detail, a.Sound FROM " + Table.ANIMAL_NAME + " AS a INNER JOIN " + Table.CATEGORY_NAME + " AS b ON a.CategoryId = b.CategoryId WHERE a.CategoryId = ?";

        public static List<String> INSERT_CATEGORIES_QUERIES() {
            List<String> CategoriesQuery = new ArrayList<>();
            CategoriesQuery.add("INSERT INTO " + Table.CATEGORY_NAME + " (" + Category.ID + "," + Category.DESCRIPTION + ") VALUES (1, 'Mammal') ");
            CategoriesQuery.add("INSERT INTO " + Table.CATEGORY_NAME + " (" + Category.ID + "," + Category.DESCRIPTION + ") VALUES (2, 'Bird') ");
            CategoriesQuery.add("INSERT INTO " + Table.CATEGORY_NAME + " (" + Category.ID + "," + Category.DESCRIPTION + ") VALUES (3, 'Fish') ");
            CategoriesQuery.add("INSERT INTO " + Table.CATEGORY_NAME + " (" + Category.ID + "," + Category.DESCRIPTION + ") VALUES (4, 'Reptile')");

            return CategoriesQuery;
        }

        public static List<String> INSERT_ANIMALS_QUERIES() {
            List<String> AnimalsQuery = new ArrayList<>();
            //region Mammals
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (1, 1, 'Bear', 'Bears are carnivoran mammals of the family Ursidae. They are classified as caniforms, or doglike carnivorans.', 'Roaaaaar') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (2, 1, 'Kangaroo', 'The kangaroo is a marsupial from the family Macropodidae (macropods, meaning \"large foot\"). In common use the term is used to describe the largest species from this family', 'chortle') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (3, 1, 'Tiger', 'The tiger (Panthera tigris) is the largest cat species, most recognizable for its pattern of dark vertical stripes on reddish-orange fur with a lighter underside.', 'growl') ");
            //endregion

            //region Birds
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (4, 2, 'Colibri', 'The violetears are hummingbirds of the genus Colibri. They are medium to large species found in Mexico, and Central and northern South America.', 'chirp chirp chirp') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (5, 2, 'Eagle', 'Eagle is the common name for many large birds of prey of the family Accipitridae. Eagles belong to several groups of genera, not all of which are closely related. Most of the 60 species of eagle are from Eurasia and Africa.', 'screeeeeeeeeeeam') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (6, 2, 'Owl', 'Owls are birds from the order Strigiformes, which includes about 200 species of mostly solitary and nocturnal birds of prey typified by an upright stance, a large, broad head, binocular vision, binaural hearing, sharp talons, and feathers adapted for silent flight.', 'hoot hoot') ");
            //endregion

            //region Fish
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (7, 3, 'Banded Archer Fish', 'The banded archerfish (Toxotes jaculatrix) is a brackish water perciform fish of the archerfish genus Toxotes. It is silvery in colour and has a dorsal fin towards the posterior end.', 'glup') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (8, 3, 'Cichlid', 'Cichlids /ˈsɪklɪdz/ are fish from the family Cichlidae in the order Perciformes. Cichlids are members of a suborder known as Labroidei, along with the wrasses (Labridae), damselfishes (Pomacentridae), and surfperches (Embiotocidae).', 'glup glup') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (9, 3, 'Koi', 'Koi or more specifically nishikigoi, literally \"brocaded carp\"), are colored varieties of Common carp (Cyprinus carpio) that are kept for decorative purposes in outdoor koi ponds or water gardens.', 'glup glup glup') ");
            //endregion

            //region Reptile
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (10, 4, 'Crocodile', 'Crocodiles (subfamily Crocodylinae) or true crocodiles are large aquatic reptiles that live throughout the tropics in Africa, Asia, the Americas and Australia.', 'growl') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (11, 4, 'Snake', 'Snakes are elongated, legless, carnivorous reptiles of the suborder Serpentes. Like all squamates, snakes are ectothermic, amniote vertebrates covered in overlapping scales.', 'sssssss') ");
            AnimalsQuery.add("INSERT INTO " + Table.ANIMAL_NAME + " (" + Animal.ID + "," + Animal.CATEGORYID + "," + Animal.DESCRIPTION + "," + Animal.DETAIL + "," + Animal.SOUND + ") VALUES (12, 4, 'Tortoise', 'Tortoises are a family, Testudinidae. Testudinidae is a Family under the order Testudines and suborder Cryptodira. There are fourteen extant families of the order Testudines, an order of reptile commonly known as turtles, tortoises, and terrapins.', 'Tortoise noises') ");
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
        public static final String SOUND = "Sound";
    }
}
