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

    private Button buttonSaveCheckPassword;
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

        buttonSaveCheckPassword = (Button) findViewById(R.id.buttonSaveCheckPassword);
        editTextInputPassword = (EditText) findViewById(R.id.editTextPassword);

        //If the application has a main password this activity will finalize
        if(mSharedPreferences.contains(Constants.MAIN_KEY) && !mForceChangePassword){
            buttonSaveCheckPassword.setText(getResources().getString(R.string.check), null);
            setCheckAction();
        }else{
            setSaveAction();
        }
    }

    private void setSaveAction(){
        buttonSaveCheckPassword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Getting the password
                        String mainPassword = editTextInputPassword.getText().toString();

                        //Saving the main password and check if it was saved in shared preference
                        boolean result = mSharedPreferences.edit().putString(Constants.MAIN_KEY, mainPassword).commit();
                        if (result) {
                            startActivity(new Intent(MainActivity.this, SearchPasswordActivity.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.error_on_save_main_password), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void setCheckAction(){
        buttonSaveCheckPassword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Getting password to check
                        String passwordToCheck = editTextInputPassword.getText().toString();
                        String mainPassword =  mSharedPreferences.getString(Constants.MAIN_KEY, new String());

                        //Check if user put the correct password
                        if (mainPassword.equalsIgnoreCase(passwordToCheck)) {
                            startActivity(new Intent(MainActivity.this, SearchPasswordActivity.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.error_on_check_main_password), Toast.LENGTH_SHORT).show();
                            editTextInputPassword.setText("");
                            editTextInputPassword.requestFocus();
                        }
                    }
                }
        );
    }

}
