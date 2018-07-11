package com.example.user.myzoo.model.Entity;

public class Category {
    int CategoryId;
    String CategoryDescription;

    public Category(int categoryId, String categoryDescription) {
        CategoryId = categoryId;
        CategoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return "Category{" +
                "CategoryId=" + CategoryId +
                ", CategoryDescription='" + CategoryDescription + '\'' +
                '}';
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }
}
