package aidooo.spydo.com.project1.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import aidooo.spydo.com.project1.R;

public class LoginSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_sign_up);
    }
    public void loginPage(View view){
        Intent intent = new Intent(this,Login.class);

        Pair[] pairs = new Pair[1];

        pairs[0]= new Pair<View,String>(findViewById(R.id.starting_login_btn),"transition_login_btn");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,pairs);
        startActivity(intent,options.toBundle());
        finish();

    }
    public void regiPage(View view){
        Intent intent = new Intent(this,Registration.class);

        Pair[] pairs = new Pair[1];

        pairs[0]= new Pair<View,String>(findViewById(R.id.starting_regi_btn),"transition_regi_btn");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,pairs);
        startActivity(intent,options.toBundle());
        finish();
    }



}