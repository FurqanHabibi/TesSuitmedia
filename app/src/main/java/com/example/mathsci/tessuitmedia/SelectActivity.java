package com.example.mathsci.tessuitmedia;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SelectActivity extends ActionBarActivity {

    private final int REQUEST_EVENT = 1;
    private final int REQUEST_GUEST = 2;

    private TextView textViewName;
    private Button buttonEvent;
    private Button buttonGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        getViewComponents();

        String name = getIntent().getStringExtra("NAME");
        textViewName.setText("Nama : " + name);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getViewComponents() {
        textViewName = (TextView)findViewById(R.id.textViewName);
        buttonEvent = (Button)findViewById(R.id.buttonEvent);
        buttonGuest = (Button)findViewById(R.id.buttonGuest);
    }

    public void goToEvent(View v) {
        Intent intent_event = new Intent(this, EventActivity.class);
        this.startActivityForResult(intent_event, REQUEST_EVENT);
    }

    public void goToGuest(View v) {
        Intent intent_guest = new Intent(this, GuestActivity.class);
        this.startActivityForResult(intent_guest, REQUEST_GUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            if (requestCode==REQUEST_EVENT) {
                String eventName = data.getStringExtra("EVENT");
                buttonEvent.setText(eventName);
            }
            else if (requestCode==REQUEST_GUEST) {
                String guestName = data.getStringExtra("GUEST");
                buttonGuest.setText(guestName);
            }
        }
    }
}
