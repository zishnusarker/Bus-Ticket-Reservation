package aidooo.spydo.com.project1.Common.LoginSignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import aidooo.spydo.com.project1.R;

public class Registration extends AppCompatActivity {

    ImageView signUpBck;
    Button nxt;
    TextView signUpTitle;
    TextInputLayout fullname, username, pass, comPass, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        signUpBck = findViewById(R.id.signUp_back_button);
        nxt = findViewById(R.id.signUp_nxt_btn);
        signUpTitle = findViewById(R.id.signUp_title_text);

        fullname = findViewById(R.id.regi_fullname);
        username = findViewById(R.id.regi_username);
        pass = findViewById(R.id.regi_pass);
        comPass = findViewById(R.id.regiCom_pass);
        email = findViewById(R.id.regi_email);

    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), LoginSignUp.class));
        finish();
    }

    public void nxt_signUp_scrn(View view) {

        if (!validateFullname() | !validateUsername() | !validateEmail() | !validatePass() | !validateComPass()) {
            return;
        }

        String fullnamE = fullname.getEditText().getText().toString().trim();
        String usernamE = username.getEditText().getText().toString().trim();
        String pasS = pass.getEditText().getText().toString().trim();
        String comPasS = comPass.getEditText().getText().toString().trim();
        String emaiL = email.getEditText().getText().toString().trim();

        Intent intent = new Intent(getApplicationContext(), SignUp2ndPage.class);

        //Toast.makeText(this, pasS, Toast.LENGTH_SHORT).show();

        intent.putExtra("username", usernamE);
        intent.putExtra("fullname", fullnamE);
        intent.putExtra("pass", pasS);
        intent.putExtra("comPass", comPasS);
        intent.putExtra("email", emaiL);

        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View, String>(signUpBck, "signUp_back_button");
        pairs[1] = new Pair<View, String>(signUpTitle, "signUp_title_text");
        pairs[2] = new Pair<View, String>(nxt, "signUp_nxt_btn");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Registration.this, pairs);
        startActivity(intent, options.toBundle());


    }

    private boolean validateFullname() {
        String val = fullname.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullname.setError("Field can not be empty");
            return false;
        } else {
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = username.getEditText().getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z";

        if (val.isEmpty()) {
            username.setError("Field can not be empty");
            return false;
        } else if (val.length() > 20) {
            username.setError("Username is too large");
            return false;
        } else if (!val.matches(checkSpaces)) {
            username.setError("No white spaces");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

        if (val.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePass() {
        String val = pass.getEditText().getText().toString().trim();
        String checkPass = "(?=^.{6,255}$)((?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))^.*";
        if (val.isEmpty()) {
            pass.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPass)) {
            pass.setError("Password should be Complex");
            return false;
        } else {
            pass.setError(null);
            pass.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateComPass() {
        String com_Pass = comPass.getEditText().getText().toString().trim();
        String check_pass = pass.getEditText().getText().toString().trim();
        if (!com_Pass.equals(check_pass)) {
            comPass.setError("Password mismatch");
            return false;
        } else {
            comPass.setError(null);
            comPass.setErrorEnabled(false);
            return true;
        }
    }

    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginSignUp.class));
        finish();
    }


}