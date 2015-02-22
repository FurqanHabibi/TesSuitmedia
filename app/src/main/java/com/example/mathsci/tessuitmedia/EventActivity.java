package com.example.mathsci.tessuitmedia;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mathsci.tessuitmedia.models.Event;

import java.util.ArrayList;


public class EventActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        final ArrayList<Event> listEvents = new ArrayList<>();
        Resources res = getResources();
        listEvents.add(new Event("Pasar Seni ITB 2014", "21 November 2014", res.getDrawable(R.drawable.dummy)));
        listEvents.add(new Event("Pasar Seni ITB 2013", "21 November 2013", res.getDrawable(R.drawable.dummy)));
        listEvents.add(new Event("Pasar Seni ITB 2012", "21 November 2012", res.getDrawable(R.drawable.dummy)));
        listEvents.add(new Event("Pasar Seni ITB 2011", "21 November 2011", res.getDrawable(R.drawable.dummy)));
        listEvents.add(new Event("Pasar Seni ITB 2010", "21 November 2010", res.getDrawable(R.drawable.dummy)));

        ListView listView = (ListView)findViewById(R.id.listView);
        EventAdapter eventAdapter = new EventAdapter(this, R.layout.list_event, listEvents);
        listView.setAdapter(eventAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("EVENT", listEvents.get(position).getName());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
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

    private class EventAdapter extends ArrayAdapter<Event> {
        private ArrayList<Event> objects;
        private int resourceLayout;

        public EventAdapter(Context context, int resource, ArrayList<Event> objects) {
            super(context, resource, objects);
            this.objects = objects;
            this.resourceLayout = resource;
        }

        public View getView (int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(resourceLayout, null);
            }
            Event e = objects.get(position);
            if (e != null) {
                ImageView image = (ImageView)v.findViewById(R.id.imageViewEvent);
                TextView name = (TextView)v.findViewById(R.id.textViewNameEvent);
                TextView date = (TextView)v.findViewById(R.id.textViewDateEvent);

                image.setImageDrawable(e.getImage());
                name.setText(e.getName());
                date.setText(e.getDate());
            }

            return v;
        }
    }
}
