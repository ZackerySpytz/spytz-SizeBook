package com.example.spytz_sizebook;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by spytz on 2017-02-03.
 */

/**
 * This class handles new records created by the user.
 * Not all fields need to be entered.
 */
public class CreateRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record);

    }

    /**
     * The button that is pressed when the record has been finished.
     * @param view
     */
    public void finishAddingRecord(View view) {
        EditText addName = (EditText) findViewById(R.id.add_name);
        // According to the assignment description, each record must have,
        // at the very least, a name.
        if (addName.getText().length() == 0) {
            return;
        }

        // Actually create the new object.
        PersonRecord newPersonRecord = new PersonRecord(addName.getText().toString());
        EditText addRecordDate = (EditText) findViewById(R.id.add_recordDate);
        EditText addNeck = (EditText) findViewById(R.id.add_neck);
        EditText addBust = (EditText) findViewById(R.id.add_bust);
        EditText addChest = (EditText) findViewById(R.id.add_chest);
        EditText addWaist = (EditText) findViewById(R.id.add_waist);
        EditText addHip = (EditText) findViewById(R.id.add_hip);
        EditText addInseam = (EditText) findViewById(R.id.add_inseam);
        EditText addComment = (EditText) findViewById(R.id.add_comment);
        Singleton.getInstance().addRecord(newPersonRecord);

        // Taken from http://stackoverflow.com/questions/7474319/how-to-parse-a-double-from-edittext-to-textview-android
        // 2017-02-04
        if (addNeck.getText().length() > 0)
            newPersonRecord.setNeck(Double.parseDouble(addNeck.getText().toString()));
        if (addBust.getText().length() > 0)
            newPersonRecord.setBust(Double.parseDouble(addBust.getText().toString()));
        if (addChest.getText().length() > 0)
            newPersonRecord.setChest(Double.parseDouble(addChest.getText().toString()));
        if (addWaist.getText().length() > 0)
            newPersonRecord.setWaist(Double.parseDouble(addWaist.getText().toString()));
        if (addHip.getText().length() > 0)
            newPersonRecord.setHip(Double.parseDouble(addHip.getText().toString()));
        if (addInseam.getText().length() > 0)
            newPersonRecord.setInseam(Double.parseDouble(addInseam.getText().toString()));
        if (addInseam.getText().length() > 0)
            newPersonRecord.setComment(addComment.getText().toString());

        setResult(RESULT_OK);
        finish();
    }

}
