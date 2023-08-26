package aidooo.spydo.com.project1.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import aidooo.spydo.com.project1.R;

public class MakeSelectionForgetPass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection_forget_pass);
    }
    public void back(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    public void otpVerifyPage(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }
}