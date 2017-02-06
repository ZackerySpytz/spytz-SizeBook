package com.example.spytz_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * This is the main class for the app.
 * The member personRecords is an ArrayList that contains
 * all of the records that have been entered into the app,
 * represented as PersonRecord objects.
 *
 * This file and app includes code from the "Building Your First App" tutorial.
 * https://developer.android.com/training/basics/firstapp/index.html
 * This file and app includes code from the "Lonely Twitter" app,
 * and the associated lab exercises.
 * https://github.com/joshua2ua/lonelyTwitter
 */
public class MainActivity extends AppCompatActivity {
    // The file that data will be written to.
    public final static String DATA_SAVEFILE = "spytz-SizeBook.sav";
    final static int ADD_RECORD_REQUEST = 10;
    final static int SELECT_RECORD_REQUEST = 20;
    private ListView recordsListView;
    private TextView numberOfRecordsText;

    // personRecords holds all records
    // but it is used and updated in tandem with the Singleton
    // in order to pass data between Activities.
    private ArrayList<PersonRecord> personRecords = new ArrayList<PersonRecord>();
    // personRecordsAdapter will be used for displaying records in the app's UI
    private ArrayAdapter<PersonRecord> personRecordsAdapter;

    /**
     * When the app is run, this sets up the GUI elements
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberOfRecordsText = (TextView) findViewById(R.id.numberOfRecords);

        /**
         * Set up the text view that shows the number of records.
         * Android Studio gives warnings about translation issues when not using a
         * resource string, but I don't think that's an issue for this assignment.
         */
        // Taken from http://stackoverflow.com/questions/3994315/integer-value-in-textview
        // 2017-02-05 16:00
        numberOfRecordsText.setText("Number of Records: " + Integer.toString(Singleton.getInstance().getRecords().size()));
        setRecords(Singleton.getInstance().getRecords());

        recordsListView = (ListView) findViewById(R.id.currentRecords);

        // Taken from http://stackoverflow.com/questions/4508979/android-listview-get-selected-item
        // 2017-02-03 20:00
        recordsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedFromList = recordsListView.getItemAtPosition(myItemInt).toString();
                Toast toast = Toast.makeText(getApplicationContext(), selectedFromList, Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(MainActivity.this, SelectExistingRecordActivity.class);
                // taken from http://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-on-android
                // 2017-02-03 23:00
                intent.putExtra("RECORD_NAME", selectedFromList);
                startActivityForResult(intent, SELECT_RECORD_REQUEST);
            }
        });
    }

    /**
     * Set up the button that will create a new record for the app.
     * This method includes code from the Android lesson on activities.
     * https://developer.android.com/training/basics/intents/result.html
     * 2017-02-03 19:00
     */
    public void addRecord(View view) {
        Intent intent = new Intent(this, CreateRecordActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, ADD_RECORD_REQUEST);
    }

    /**
     * This method, and the corresponding one in SelectExistingRecordActivity.java,
     * is also largely from the Android lesson on activities.
     * https://developer.android.com/training/basics/intents/result.html
     * It will listen for activities finishing (for example, creating new Records).
     * 2017-02-03 19:00
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_RECORD_REQUEST) {
            if (resultCode == RESULT_OK) {
                saveInFile();
                numberOfRecordsText = (TextView) findViewById(R.id.numberOfRecords);
                numberOfRecordsText.setText("Number of Records: " + Integer.toString(Singleton.getInstance().getRecords().size()));
                setRecords(Singleton.getInstance().getRecords());
                personRecordsAdapter.notifyDataSetChanged();
                // Toasts are fun to use, and they add some character to the app.
                // This one shows a small notification to let users know they (successfully) added a record.
                // From https://developer.android.com/guide/topics/ui/notifiers/toasts.html
                // 2017-02-03 22:00
                Toast toast = Toast.makeText(getApplicationContext(), "Added Record", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(), "No Record Added", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else if (requestCode == SELECT_RECORD_REQUEST) {
                saveInFile();
                numberOfRecordsText = (TextView) findViewById(R.id.numberOfRecords);
                numberOfRecordsText.setText("Number of Records: " + Integer.toString(Singleton.getInstance().getRecords().size()));
                setRecords(Singleton.getInstance().getRecords());
                personRecordsAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Set the records for the MainActivity to handle (for the main list).
     * @param records
     */
    public void setRecords(ArrayList<PersonRecord> records) {
        personRecords = records;
    }

    /**
     * This method is called on start.
     * It is important as it call loadRecordData(), which
     * retrieves data on records that had been entered into the app.
     */
    @Override
    public void onStart() {
        super.onStart();
        loadRecordData();
        numberOfRecordsText = (TextView) findViewById(R.id.numberOfRecords);

        numberOfRecordsText.setText("Number of Records: " + Integer.toString(Singleton.getInstance().getRecords().size()));
        personRecordsAdapter = new ArrayAdapter<PersonRecord>(this,
                R.layout.records_layout, personRecords);
        recordsListView.setAdapter(personRecordsAdapter);
    }

    /**
     * This method is from the Lab 3 Demo on Gson serialization.
     */
    private void loadRecordData() {
        try {
            FileInputStream fileInStream = openFileInput(DATA_SAVEFILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fileInStream));
            Gson gson = new Gson();
            // The fromJson method was demonstrated in the Lab 3 demo, and it was taken from:
            // http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-26 17:53:59
            personRecords = gson.fromJson(in, new TypeToken<ArrayList<PersonRecord>>(){}.getType());
            Singleton.getInstance().setRecords(personRecords);
            fileInStream.close();
        } catch (FileNotFoundException e) {
            personRecords = new ArrayList<PersonRecord>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * This method is also largely from the Lab 3 Demo on Gson serialization.
     * (It works very well.)
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(DATA_SAVEFILE,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(Singleton.getInstance().getRecords(), out);
            //gson.toJson(personRecords, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
