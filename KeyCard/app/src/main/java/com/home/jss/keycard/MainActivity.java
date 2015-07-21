package com.home.jss.keycard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.HashMap;

public class MainActivity extends Activity {

    private HashMap<Integer, Integer> mPasswordsMap;
    private SharedPreferences mSharedPreferences;
    private boolean mForceChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeParameters();
        checkMainPassword();
    }

    private void initializeParameters()
    {
        mPasswordsMap =  new HashMap<>();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private void checkMainPassword(){

      if(mSharedPreferences.contains(Constants.MAIN_KEY) || !mForceChangePassword) {
          Intent intent = new Intent();
          startActivity(new Intent(this, SearchPasswordActivity.class));
      };
    }

}
