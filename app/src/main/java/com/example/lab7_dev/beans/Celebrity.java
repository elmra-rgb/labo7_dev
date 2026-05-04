package com.example.lab7_dev.beans;

public class Celebrity {
    private int uniqueId;
    private String fullName;
    private String imageUrl;
    private float averageRating;
    private static int idGenerator = 0;

    public Celebrity(String fullName, String imageUrl, float averageRating) {
        this.uniqueId = ++idGenerator;
        this.fullName = fullName;
        this.imageUrl = imageUrl;
        this.averageRating = averageRating;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }
}