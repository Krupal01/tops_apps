package com.example.storageassignment.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.storageassignment.R;

public class schoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

            IsFirstTime();

            SharedPreferences preferences = getSharedPreferences("IsfirstTime", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isfirstTime", false);
            editor.commit();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame, new RegistrationSchoolFragment())
                    .commit();

    }

    private void IsFirstTime() {
        SharedPreferences preferences = getSharedPreferences("IsfirstTime", MODE_PRIVATE);
        Boolean firstTime = preferences.getBoolean("isfirstTime",true);
        SharedPreferences preferences2 = getSharedPreferences("IsLogin", MODE_PRIVATE);
        Boolean login = preferences2.getBoolean("isLogin",false);
                if(firstTime == false && login == false){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame, new LoginSchoolFragment())
                            .commit();
                }else if (firstTime == false && login == true){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame, new homeFragment())
                            .commit();
                }
    }
}