package aidooo.spydo.com.project1.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import aidooo.spydo.com.project1.R;

public class ForgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pasword);
    }

    public void back(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void makeSelection(View view){
        Intent intent = new Intent(this,MakeSelectionForgetPass.class);
        startActivity(intent);
    }

}