package aidooo.spydo.com.project1.Common.LoginSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import aidooo.spydo.com.project1.R;
import aidooo.spydo.com.project1.commonForApp.Main_home;

public class Login extends AppCompatActivity {

    TextInputLayout user,pass;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        user=findViewById(R.id.login_email);
        pass = findViewById(R.id.login_pass);
        progressBar = findViewById(R.id.progressBar);



    }

    public void startDashboardPage(View view){
        if (!validateFields()){
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        final String _username = user.getEditText().getText().toString().trim();
        final String _pass = pass.getEditText().getText().toString().trim();

        Query checkUser= FirebaseDatabase.getInstance().getReference("users").orderByChild("username").equalTo(_username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    user.setError(null);
                    user.setErrorEnabled(false);

                    String _systemPass = dataSnapshot.child(_username).child("pass").getValue(String.class);
                    String _systemUID = dataSnapshot.child(_username).child("currentUserID").getValue(String.class);


                    if (_systemPass.equals(_pass)){
                        pass.setError(null);
                        pass.setErrorEnabled(false);

                        progressBar.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(getApplicationContext(), Main_home.class);
                        intent.putExtra("currentUser",_systemUID);
                        startActivity(intent);
                        Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                       // Toast.makeText(Login.this, _systemUID, Toast.LENGTH_SHORT).show();
                        finish();
                    }else{

                        pass.setError("Password Incorrect");
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                }else{
                    user.setError("User not exists");
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void back(View view){
        Intent intent = new Intent(this, LoginSignUp.class);
        startActivity(intent);
    }
    public void regiPage(View view){
        startActivity(new Intent(getApplicationContext(), Registration.class));
        finish();
    }
    public void callforgetPassPage(View view){
        startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
        finish();
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginSignUp.class));
        finish();
    }

    private boolean validateFields(){
        String _email = user.getEditText().getText().toString().trim();
        String _pass = pass.getEditText().getText().toString().trim();
        if (_email.isEmpty()){
            user.setError("Field can not be empty");
            user.requestFocus();
            return false;
        }else if(_pass.isEmpty()){
            pass.setError("Field can not be empty");
            pass.requestFocus();
            return false;
        }else{
            user.setError(null);
            user.setErrorEnabled(false);
            pass.setError(null);
            pass.setErrorEnabled(false);

            return true;

        }
    }

}