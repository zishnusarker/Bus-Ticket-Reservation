package aidooo.spydo.com.project1.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import aidooo.spydo.com.project1.R;

public class SignUp2ndPage extends AppCompatActivity {

    ImageView signUpBck;
    Button nxt;
    TextView signUpTitle;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2nd_page);

        signUpBck = findViewById(R.id.signUp_back_button);
        nxt = findViewById(R.id.signUp_nxt_btn);
        signUpTitle = findViewById(R.id.signUp_title_text);
        radioGroup = findViewById(R.id.radio_group);
        datePicker = findViewById(R.id.age_picker);

        String usernamE = getIntent().getStringExtra("username");
        String fullnamE = getIntent().getStringExtra("fullname");
        String pasS = getIntent().getStringExtra("pass");
        String comPasS = getIntent().getStringExtra("comPass");
        String emaiL = getIntent().getStringExtra("email");

    }

    public void nxt_signUp_scrn(View view) {

        if (!validateGender() | !validateAge()){
            return;
        }


        String usernamE = getIntent().getStringExtra("username");
        String fullnamE = getIntent().getStringExtra("fullname");
        String pasS = getIntent().getStringExtra("pass");
        String comPasS = getIntent().getStringExtra("comPass");
        String emaiL = getIntent().getStringExtra("email");

        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String gender = selectedGender.getText().toString().trim();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String age = day+"/"+month+"/"+year;

        Intent intent = new Intent(getApplicationContext(), SignUp3rdPage.class);

        //send to another activity
        intent.putExtra("username", usernamE);
        intent.putExtra("fullname", fullnamE);
        intent.putExtra("pass", pasS);
        intent.putExtra("comPass", comPasS);
        intent.putExtra("email", emaiL);
        intent.putExtra("gendeR", gender);
        intent.putExtra("agE", age);

        //animated startup for next page
        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View, String>(signUpBck, "signUp_back_button");
        pairs[1] = new Pair<View, String>(signUpTitle, "signUp_title_text");
        pairs[2] = new Pair<View, String>(nxt, "signUp_nxt_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndPage.this, pairs);
        startActivity(intent, options.toBundle());

    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), Registration.class));
        finish();
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "please select gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAge(){
        int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int checkAge = currentyear-userAge;

        if (checkAge<13){
            Toast.makeText(this, "You are not eligible to registration", Toast.LENGTH_SHORT).show();
            return  false;
        }else{
            return true;
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginSignUp.class));
        finish();
    }


}