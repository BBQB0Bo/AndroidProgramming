package com.example.tema_i;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class MyPreferenceActivity extends AppCompatActivity
{
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String phoneNumber = "phoneNumber";
    public static final String email = "email";

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_preference);
        getSupportActionBar().setTitle("Application Settings");
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }


    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }

    /*Am presupus ca trebuie salvez preferintele din setari chiar daca ele sunt salvate deja in
    getDefaultSharedPreferences...
    Ele au fost salvate in SHARED_PREFS
    Functia nu este apelata nicaieri
    Nu am inteles exact unde trebuie sa le salvam , deoarece ele sunt salvate deja ..
    ***Setarile le-am afisat in meniul aplicatiei , in show settings ***
     */
    public void saveSharedPref(){

        /*Preferintele default*/
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        /*Salvam in preferintele noastre - variabila SHARED_PREFS*/
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(phoneNumber,prefs.getString("phoneNumber",""));
        editor.putString(email,prefs.getString("email",""));
        editor.apply();
    }
}