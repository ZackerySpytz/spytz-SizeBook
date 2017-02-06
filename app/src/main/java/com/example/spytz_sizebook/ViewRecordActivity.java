package com.example.spytz_sizebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by spytz on 2017-02-03.
 */

/**
 * This activity simply allows the user to view
 * a record that has already been created.
 */
public class ViewRecordActivity extends AppCompatActivity {

    /**
     * For this class, onCreate must display the any fields
     * that have been entered into this PersonRecord.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String recordName = getIntent().getStringExtra("RECORD_NAME");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        // "Query" the Singleton.
        PersonRecord selectedPerson = Singleton.getInstance().getRecord(recordName);

        if (selectedPerson == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "Error retrieving " + recordName, Toast.LENGTH_SHORT);
            toast.show();
            setResult(RESULT_CANCELED);
            finish();
        }

        // They are TextViews to prevent the user from editing in the View Activity.
        TextView name = (TextView) findViewById(R.id.name);

        TextView recordDate = (TextView) findViewById(R.id.recordDate);

        TextView neck = (TextView) findViewById(R.id.neck);
        TextView bust = (TextView) findViewById(R.id.bust);
        TextView chest = (TextView) findViewById(R.id.chest);
        TextView waist = (TextView) findViewById(R.id.waist);
        TextView hip = (TextView) findViewById(R.id.hip);
        TextView inseam = (TextView) findViewById(R.id.inseam);
        TextView comment = (TextView) findViewById(R.id.comment);

        name.setText(selectedPerson.getName());
        // These checks are a bit hacky, but it is nice to show
        // the default text if no value has been entered.
        if (selectedPerson.getRecordDate() != "")
            recordDate.setText(selectedPerson.getRecordDate());
        else
            comment.setText("Not Entered");
        if (selectedPerson.getNeck() != 0.0)
            neck.setText(Double.toString(selectedPerson.getNeck()));
        else
            neck.setText("Not Entered");
        if (selectedPerson.getBust() != 0.0)
            bust.setText(Double.toString(selectedPerson.getBust()));
        else
            bust.setText("Not Entered");
        if (selectedPerson.getChest() != 0.0)
            chest.setText(Double.toString(selectedPerson.getChest()));
        else
            chest.setText("Not Entered");
        if (selectedPerson.getWaist() != 0.0)
            waist.setText(Double.toString(selectedPerson.getWaist()));
        else
            waist.setText("Not Entered");
        if (selectedPerson.getHip() != 0.0)
            hip.setText(Double.toString(selectedPerson.getHip()));
        else
            hip.setText("Not Entered");
        if (selectedPerson.getInseam() != 0.0)
            inseam.setText(Double.toString(selectedPerson.getInseam()));
        else
            inseam.setText("Not Entered");
        if (selectedPerson.getComment() == null)
            comment.setText(selectedPerson.getComment());
        else
            comment.setText("Not Entered");
    }

    /**
     * Not much to do for this method/button; when the user
     * stops viewing, return to the previous screen.
     * @param view
     */
    public void finishViewingRecord(View view) {
        setResult(RESULT_OK);
        finish();
    }
}
