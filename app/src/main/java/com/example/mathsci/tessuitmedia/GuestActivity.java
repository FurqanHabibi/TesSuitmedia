package com.example.mathsci.tessuitmedia;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathsci.tessuitmedia.models.Event;
import com.example.mathsci.tessuitmedia.models.Guest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class GuestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        new HttpAsyncTask().execute("http://dry-sierra-6832.herokuapp.com/api/people");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guest, menu);
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


    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return getData(urls[0]);
        }
        protected void onPostExecute(String result) {
            updateGridView(result);
        }
    }

    private String getData(String urlString) {
        String data = "";
        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            try {
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(in));
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    data += line;
                }
            }
            finally {
                in.close();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private void updateGridView(String data) {
        final ArrayList<Guest> listGuests = new ArrayList<>();
        Resources res = getResources();
        try {
            JSONArray jsonArray = new JSONArray(data);
            Guest g;
            JSONObject jsonObject;
            for (int i=0; i<jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                g = new Guest(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("birthdate"), res.getDrawable(R.drawable.dummy));
                listGuests.add(g);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GridView gridView = (GridView)findViewById(R.id.gridView);
        GuestAdapter guestAdapter = new GuestAdapter(this, R.layout.list_guest, listGuests);
        gridView.setAdapter(guestAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("GUEST", listGuests.get(position).getName());
                setResult(RESULT_OK, returnIntent);
                Toast.makeText(getApplicationContext(), getStringToast(listGuests.get(position).getBirthdate()), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private String getStringToast(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(stringDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String toast="";
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int tanggal = calendar.get(Calendar.DATE);
        int bulan = calendar.get(Calendar.MONTH);

        if (tanggal%6==0) {
            toast+="iOS";
        }
        else if (tanggal%2==0) {
            toast+="blackberry";
        }
        else if (tanggal%3==0) {
            toast+="android";
        }
        else {
            toast+="feature phone";
        }

        if (isPrime(bulan)) {
            toast+=" dan prima";
        }
        else {
            toast+=" dan tidak prima";
        }

        return toast;
    }

    private boolean isPrime(int input) {
        boolean retval = true;
        int i=1;
        int akar = (int)Math.sqrt((double)input);
        while ((i<akar)&&(retval)) {
            retval = retval &&(input%i!=0);
            i++;
        }
        return retval;
    }

    private class GuestAdapter extends ArrayAdapter<Guest> {
        private ArrayList<Guest> objects;
        private int resourceLayout;

        public GuestAdapter(Context context, int resource, ArrayList<Guest> objects) {
            super(context, resource, objects);
            this.objects = objects;
            this.resourceLayout = resource;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(resourceLayout, null);
            }
            Guest e = objects.get(position);
            if (e != null) {
                ImageView image = (ImageView) v.findViewById(R.id.imageViewGuest);
                TextView name = (TextView) v.findViewById(R.id.textViewNameGuest);
                TextView date = (TextView) v.findViewById(R.id.textViewBirthdateGuest);

                image.setImageDrawable(e.getImage());
                name.setText(e.getName());
                date.setText(e.getBirthdate());
            }

            return v;
        }
    }
}
