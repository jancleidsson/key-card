package com.home.jss.keycard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends Activity {

    private HashMap<Integer, Integer> mPasswordsMap;
    private SharedPreferences mSharedPreferences;
    private boolean mForceChangePassword;
    private String mMainPassword;

    private Button buttonSavePassword;
    private EditText editTextInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent requestIntent = getIntent();
        if(requestIntent != null){
            mForceChangePassword = requestIntent.getBooleanExtra(Constants.CHANGE_MAIN_PASSWORD_BUNDLE_KEY, false);
        }

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //If the application has a main password this activity will finalize
        if(mSharedPreferences.contains(Constants.MAIN_KEY) && !mForceChangePassword){
            startActivity(new Intent(MainActivity.this, SearchPasswordActivity.class));
            finish();
        }

        buttonSavePassword = (Button) findViewById(R.id.buttonSavePassword);
        editTextInputPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonSavePassword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Getting the password
                        mMainPassword = editTextInputPassword.getText().toString();

                        //Saving the main password and check if it was saved in shared preference
                        boolean result = mSharedPreferences.edit().putString(Constants.MAIN_KEY, mMainPassword).commit();
                        if (result) {
                            startActivity(new Intent(MainActivity.this, SearchPasswordActivity.class));
                        } else {
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.error_on_save_main_password), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
