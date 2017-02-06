package com.example.spytz_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by spytz on 2017-02-03.
 */

/**
 * This class handles activity when an existing PersonRecord
 * is selected from the ListView.
 * Options are given to delete, view, or edit the record (as outlined
 * in the assignment description), or the user can cancel and return
 * to MainActivity.
 */
public class SelectExistingRecordActivity extends AppCompatActivity {

    final static int VIEW_RECORD_REQUEST = 30;
    final static int EDIT_RECORD_REQUEST = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_existing_record);
    }

    /**
     *
     * @param view
     */
    public void viewExistingRecord(View view) {
        String recordName = getIntent().getStringExtra("RECORD_NAME");
        Intent intent = new Intent(SelectExistingRecordActivity.this, ViewRecordActivity.class);

        intent.putExtra("RECORD_NAME", recordName);
        startActivityForResult(intent, VIEW_RECORD_REQUEST);
    }

    /**
     *
     * @param view
     */
    public void editExistingRecord(View view) {
        String recordName = getIntent().getStringExtra("RECORD_NAME");
        Intent intent = new Intent(SelectExistingRecordActivity.this, EditRecordActivity.class);

        intent.putExtra("RECORD_NAME", recordName);
        startActivityForResult(intent, EDIT_RECORD_REQUEST);
    }

    /**
     * Delete (remove) a record from the records
     * held by the app.
     * Delete is special because we can return immediately to the previous activity.
     * @param view
     */
    public void deleteExistingRecord(View view) {
        String recordName = getIntent().getStringExtra("RECORD_NAME");
        if (Singleton.getInstance().deleteRecord(recordName)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Deleted " + recordName, Toast.LENGTH_SHORT);
            toast.show();
        }

        // Delete and finish.
        setResult(RESULT_OK);
        finish();
    }

    /**
     * This button will return from the Select Activity.
     * @param view
     */
    public void cancelSelectExistingRecord(View view) {
        // Just finish the activity
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIEW_RECORD_REQUEST) {
            setResult(RESULT_OK);
            finish();
        }
        else if (requestCode == EDIT_RECORD_REQUEST) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
