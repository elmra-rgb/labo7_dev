package com.example.lab7_dev.service;

import com.example.lab7_dev.beans.Celebrity;
import com.example.lab7_dev.dao.IGenericDao;
import java.util.ArrayList;
import java.util.List;

public class CelebrityManager implements IGenericDao<Celebrity> {
    private List<Celebrity> celebrityList;
    private static CelebrityManager uniqueInstance;

    private CelebrityManager() {
        celebrityList = new ArrayList<>();
        initializeData();
    }

    public static CelebrityManager getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CelebrityManager();
        }
        return uniqueInstance;
    }

    private void initializeData() {
        // Utilisation de vos images
        celebrityList.add(new Celebrity("Emma Watson", "emma_watson", 4.5f));
        celebrityList.add(new Celebrity("Tom Cruise", "tom_cruise", 4.2f));
        celebrityList.add(new Celebrity("Elle Fanning", "elle_fanning", 4.3f));
        celebrityList.add(new Celebrity("Leonardo DiCaprio", "leo", 4.8f));
        celebrityList.add(new Celebrity("Zendaya", "zendaya", 4.7f));
        celebrityList.add(new Celebrity("Keanu Reeves", "keanu", 4.9f));
        celebrityList.add(new Celebrity("Meryl Streep", "meryl", 4.6f));
        celebrityList.add(new Celebrity("Brad Pitt", "brad", 4.4f));
        celebrityList.add(new Celebrity("Angelina Jolie", "angelina", 4.3f));
        celebrityList.add(new Celebrity("Monica Bellucci", "monica_belluci", 4.5f));
    }

    @Override
    public boolean insert(Celebrity object) {
        return celebrityList.add(object);
    }

    @Override
    public boolean modify(Celebrity object) {
        for (Celebrity celeb : celebrityList) {
            if (celeb.getUniqueId() == object.getUniqueId()) {
                celeb.setFullName(object.getFullName());
                celeb.setImageUrl(object.getImageUrl());
                celeb.setAverageRating(object.getAverageRating());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Celebrity object) {
        return celebrityList.remove(object);
    }

    @Override
    public Celebrity searchById(int id) {
        for (Celebrity celeb : celebrityList) {
            if (celeb.getUniqueId() == id) {
                return celeb;
            }
        }
        return null;
    }

    @Override
    public List<Celebrity> retrieveAll() {
        return celebrityList;
    }
}