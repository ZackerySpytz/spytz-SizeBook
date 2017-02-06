package com.example.spytz_sizebook;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The skeleton of this singleton class was generated
 * by Android Studio, including the ourInstance variable.
 * It was recommended by the TAs to use a singleton in order
 * to access data between classes.
 * This article helped clarify a few things for me about Singletons:
 * http://stackoverflow.com/questions/16517702/singleton-in-android
 * 2017-02-03
 * Created by spytz on 2017-02-03.
 */
public class Singleton {
    private int uniqueID = 1;
    private static Singleton ourInstance = new Singleton();
    private ArrayList<PersonRecord> personRecords;

    /**
     * Constructor of Singleton.
     */
    private Singleton() {
        personRecords = new ArrayList<PersonRecord>();
    }

    public static Singleton getInstance() {
        return ourInstance;
    }

    public void addRecord(PersonRecord record) {
        personRecords.add(record);
    }

    public ArrayList<PersonRecord> getRecords() {
        return personRecords;
    }

    public void setRecords(ArrayList<PersonRecord> records) {
        personRecords = records;
    }

    public int getUniqueID() {
        int newID = uniqueID;
        uniqueID++;
        return newID;
    }

    public boolean deleteRecord(String name) {
        // These next two methods use an iterator technique
        // taken from http://stackoverflow.com/questions/10502164/how-do-i-remove-an-object-from-an-arraylist-in-java
        // 2017-02-03
        Iterator<PersonRecord> it = personRecords.iterator();
        while (it.hasNext()) {
            PersonRecord person = it.next();
            if (person.getName().equals(name)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public PersonRecord getRecord(String name) {
        Iterator<PersonRecord> it = personRecords.iterator();
        while (it.hasNext()) {
            PersonRecord person = it.next();
            if (person.getName().equals(name)) {
                return person;
            }
        }
        // Shouldn't happen.
        return null;
    }
}

