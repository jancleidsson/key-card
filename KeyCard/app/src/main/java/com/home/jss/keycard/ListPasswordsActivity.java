package com.home.jss.keycard;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.widget.ListView;

import java.util.ArrayList;


public class ListPasswordsActivity extends Activity {

    private ListView listPasswords;
    private SharedPreferences mSharedPreferences;
    private ArrayList<String> mPasswordsValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_passwords);

        listPasswords = (ListView) findViewById(R.id.listViewPasswords);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mPasswordsValues = passwordsListFromPreferences();

        ListPasswordAdapter listPasswordAdapter = new ListPasswordAdapter(this, mPasswordsValues);
        listPasswords.setAdapter(listPasswordAdapter);
    }

    private ArrayList<String> passwordsListFromPreferences()
    {
        ArrayList<String> passwordsList =  new ArrayList<>();
        for (int i = 1; i <= Constants.MAX_PASSWORDS; ++ i)
        {
            String key = new String();

            if(i < 10){
               key = key.concat("0").concat(String.valueOf(i));
            }else{
               key = key.concat(String.valueOf(i));
            }

            if(mSharedPreferences.contains(key)){
                passwordsList.add(mSharedPreferences.getString(key, new String()));
            }
        }
        return passwordsList;
    }
}
