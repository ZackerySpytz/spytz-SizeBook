package com.example.spytz_sizebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by spytz on 2017-02-03.
 */

/**
 * This Activity will allow the user to edit the details/fields
 * of a record that they have already created.
 * Restrictions similar to that of the Create Activity are followed:
 * The name field must be present for any given record.
 * The fields that have already been entered will be displayed.
 */
public class EditRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String recordName = getIntent().getStringExtra("RECORD_NAME");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        PersonRecord selectedPerson = Singleton.getInstance().getRecord(recordName);

        if (selectedPerson == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "Error retrieving " + recordName, Toast.LENGTH_SHORT);
            toast.show();
            setResult(RESULT_CANCELED);
            finish();
        }

        // Set up the fields that have been entered previously
        // so that they show up in the EditTexts.
        EditText editName = (EditText) findViewById(R.id.edit_name);
        EditText editRecordDate = (EditText) findViewById(R.id.edit_recordDate);
        EditText editNeck = (EditText) findViewById(R.id.edit_neck);
        EditText editBust = (EditText) findViewById(R.id.edit_bust);
        EditText editChest = (EditText) findViewById(R.id.edit_chest);
        EditText editWaist = (EditText) findViewById(R.id.edit_waist);
        EditText editHip = (EditText) findViewById(R.id.edit_hip);
        EditText editInseam = (EditText) findViewById(R.id.edit_inseam);
        EditText editComment = (EditText) findViewById(R.id.edit_comment);

        editName.setText(selectedPerson.getName());
        editRecordDate.setText(selectedPerson.getRecordDate());
        if (selectedPerson.getNeck() != 0.0)
            editNeck.setText(Double.toString(selectedPerson.getNeck()));
        if (selectedPerson.getBust() != 0.0)
            editBust.setText(Double.toString(selectedPerson.getBust()));
        if (selectedPerson.getChest() != 0.0)
            editChest.setText(Double.toString(selectedPerson.getChest()));
        if (selectedPerson.getWaist() != 0.0)
            editWaist.setText(Double.toString(selectedPerson.getWaist()));
        if (selectedPerson.getHip() != 0.0)
            editHip.setText(Double.toString(selectedPerson.getHip()));
        if (selectedPerson.getInseam() != 0.0)
            editInseam.setText(Double.toString(selectedPerson.getInseam()));
        editComment.setText(selectedPerson.getComment());

    }

    /**
     * The user has pressed the button to signify that
     * they have finished editing this record.
     * Like the Create Activity, all the fields have to be
     * saved, and at the very least, the name field has to have
     * a valid input.
     * @param view
     */
    public void finishEditingRecord(View view) {
        String recordName = getIntent().getStringExtra("RECORD_NAME");
        PersonRecord selectedPerson = Singleton.getInstance().getRecord(recordName);

        EditText editName = (EditText) findViewById(R.id.edit_name);

        if (editName.getText().length() == 0) {
            return;
        }

        EditText editRecordDate = (EditText) findViewById(R.id.edit_recordDate);
        EditText editNeck = (EditText) findViewById(R.id.edit_neck);
        EditText editBust = (EditText) findViewById(R.id.edit_bust);
        EditText editChest = (EditText) findViewById(R.id.edit_chest);
        EditText editWaist = (EditText) findViewById(R.id.edit_waist);
        EditText editHip = (EditText) findViewById(R.id.edit_hip);
        EditText editInseam = (EditText) findViewById(R.id.edit_inseam);
        EditText editComment = (EditText) findViewById(R.id.edit_comment);

        selectedPerson.setName(editName.getText().toString());
        if (editRecordDate.getText().length() > 0)
            selectedPerson.setRecordDate(editRecordDate.getText().toString());
        if (editNeck.getText().length() > 0)
            selectedPerson.setNeck(Double.parseDouble(editNeck.getText().toString()));
        if (editBust.getText().length() > 0)
            selectedPerson.setBust(Double.parseDouble(editBust.getText().toString()));
        if (editChest.getText().length() > 0)
            selectedPerson.setChest(Double.parseDouble(editChest.getText().toString()));
        if (editWaist.getText().length() > 0)
            selectedPerson.setWaist(Double.parseDouble(editWaist.getText().toString()));
        if (editHip.getText().length() > 0)
            selectedPerson.setHip(Double.parseDouble(editHip.getText().toString()));
        if (editInseam.getText().length() > 0)
            selectedPerson.setInseam(Double.parseDouble(editInseam.getText().toString()));
        if (editComment.getText().length() > 0)
            selectedPerson.setComment(editComment.getText().toString());


        setResult(RESULT_OK);
        finish();
    }
}
