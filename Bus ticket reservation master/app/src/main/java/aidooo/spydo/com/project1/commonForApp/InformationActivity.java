package aidooo.spydo.com.project1.commonForApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import aidooo.spydo.com.project1.R;

public class InformationActivity extends AppCompatActivity {

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        user = getIntent().getStringExtra("user");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Main_home.class);
        intent.putExtra("currentUser",user);
        startActivity(intent);
        finish();
    }
}