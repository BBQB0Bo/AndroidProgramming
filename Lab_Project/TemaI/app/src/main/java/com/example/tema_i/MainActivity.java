package com.example.tema_i;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.TypedValue;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "Message";
    private ListView listView;
    private TextView textView;
    private final String[] description = {"Descrierea 1","Descrierea 2","Descrierea 3","Descrierea 4","Descrierea 5","Descrierea 6"};
    private int pos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.textview1);

        textView.setTypeface(null, Typeface.BOLD_ITALIC);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Pc");
        arrayList.add("Laptop");
        arrayList.add("Headphones");
        arrayList.add("Smart Phone");
        arrayList.add("Earphones");
        arrayList.add("Mouse");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                TextView tv = view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTypeface(null, Typeface.BOLD_ITALIC);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,23);

                // Return the view
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, description[position], Toast.LENGTH_SHORT).show();
                pos = position;
                textView.setText(description[position]);
            }
        });

        if(savedInstanceState != null){
            int pozitie = savedInstanceState.getInt("pos");
            textView.setText(description[pozitie]);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG , "OnStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG , "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG , "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG , "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG , "onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG , "onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("pos",pos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.application_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Login Selected", Toast.LENGTH_LONG).show();
                addModal();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Register Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Show Settings Selected", Toast.LENGTH_LONG).show();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                StringBuilder info = new StringBuilder();

                info.append("Settings for this application are: ");
                info.append("\nTelephone Number: " + prefs.getString("phoneNumber",""));
                info.append("\nEmail address: " + prefs.getString("email",""));
                sendMessage(info.toString());
                return true;
            case R.id.item4:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL, "robertdaraban@yahoo.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello friend");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
            case R.id.item5:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_LONG).show();
                startPreferenceActivity();
                return true;
            case R.id.item6:
                Toast.makeText(this, "Storage Selected", Toast.LENGTH_LONG).show();
                startStorageActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*Functie prin care pornim activitatea Display Message si salvam un mesaj in Intent ca sa il afisam la
    pornirea acesteia
     */

    public void sendMessage(String message) {
        Intent intent = new Intent(this, DisplayMessage.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /*Functie prin care pornim activtatea pentru setarile aplicatiei*/
    public void startPreferenceActivity(){
        Intent intent = new Intent(this, MyPreferenceActivity.class);
        startActivity(intent);
    }

    /*Functie prin care pornin activitatea pentru storage*/
    public void startStorageActivity(){
        Intent intent = new Intent(this, StorageActivity.class);
        startActivity(intent);
    }

    public void addModal(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Do you want to login?");
        // alert.setMessage("Message");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        alert.show();
    }
}
