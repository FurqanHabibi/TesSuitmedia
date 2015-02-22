package com.example.mathsci.tessuitmedia;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void goToSelect(View v) {
        EditText editTextName = (EditText)findViewById(R.id.editTextName);
        Intent intent_select = new Intent(this, SelectActivity.class);
        intent_select.putExtra("NAME", editTextName.getText().toString());

        String toast = "";
        if (isPalindrome(editTextName.getText().toString())) {
            toast = "palindrome";
        }
        else {
            toast = "tidak palindrome";
        }
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();

        this.startActivity(intent_select);
    }

    private boolean isPalindrome(String input) {
        input = input.toUpperCase();
        boolean retval = true;
        int length = input.length();
        int i=0;
        while ((i<length)&&(retval)) {
            retval = retval&&(input.charAt(i)==input.charAt(length-1-i));
            i++;
        }
        return retval;
    }


}
