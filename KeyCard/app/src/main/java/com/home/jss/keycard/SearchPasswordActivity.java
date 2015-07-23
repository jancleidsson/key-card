package com.home.jss.keycard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Browser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SearchPasswordActivity extends Activity {

    private SharedPreferences mSharedPreferences;

    private Button mChangeMainPassword;
    private Button mSavePassword;
    private Button mButtonSearchPassword;

    private EditText mEditTextKeyPassword;
    private EditText mEditTextValuePassword;
    private EditText mEditTextSearchPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_password);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        mChangeMainPassword = (Button) findViewById(R.id.buttonChangePassword);
        mSavePassword = (Button) findViewById(R.id.buttonSavePassword);
        mButtonSearchPassword = (Button) findViewById(R.id.buttonSearchPassword);

        mEditTextKeyPassword = (EditText) findViewById(R.id.editTextKeyPassword);
        mEditTextValuePassword = (EditText) findViewById(R.id.editTextValuePassword);
        mEditTextSearchPassword = (EditText) findViewById(R.id.editTextSearchPassword);

        mChangeMainPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(SearchPasswordActivity.this, MainActivity.class);
                intentMainActivity.putExtra(Constants.CHANGE_MAIN_PASSWORD_BUNDLE_KEY, true);
                startActivity(intentMainActivity);
            }
        });

        mSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String keyPassword = mEditTextKeyPassword.getText().toString();
                 String valuePassword = mEditTextValuePassword.getText().toString();

                if((keyPassword.length() == 2) && (valuePassword.length() == 4) && (Integer.parseInt(keyPassword) <= Constants.MAX_PASSWORDS) && saveKeyCode(keyPassword, valuePassword)){
                    Toast.makeText(SearchPasswordActivity.this, getResources().getString(R.string.password_saved), Toast.LENGTH_SHORT).show();

                    mEditTextValuePassword.setText("");
                    mEditTextKeyPassword.setText("");
                    mEditTextKeyPassword.requestFocus();

                }else{
                    Toast.makeText(SearchPasswordActivity.this, getResources().getString(R.string.error_on_save_password), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonSearchPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyPasswordToSearch = mEditTextSearchPassword.getText().toString();
                if(keyPasswordToSearch.length() == 2 && getKeyCode(keyPasswordToSearch).length() == 4){
                    Toast.makeText(SearchPasswordActivity.this, getKeyCode(keyPasswordToSearch), Toast.LENGTH_SHORT).show();
                    mEditTextSearchPassword.setText("");
                }else{
                    Toast.makeText(SearchPasswordActivity.this, getResources().getString(R.string.error_on_search_password), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean saveKeyCode(String keyPassword, String valuePassword)
    {
        return mSharedPreferences.edit().putString(keyPassword, valuePassword).commit();
    }

    private String getKeyCode(String keyPassword)
    {
        //To avoid return null value
        return (mSharedPreferences.getString(keyPassword, new String()));
    }
}
