package aidooo.spydo.com.project1.commonForApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import aidooo.spydo.com.project1.Common.LoginSignUp.Login;
import aidooo.spydo.com.project1.Common.LoginSignUp.LoginSignUp;
import aidooo.spydo.com.project1.R;

public class Main_home extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference reference;
    private String user,_fullname,_email,_phonenum,_gender;
    private TextView fullname,phone;

    private CardView purchase, history,info,cancel;
    ProgressDialog _loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_main);

        user = getIntent().getStringExtra("currentUser");

        fullname = findViewById(R.id.Txt_username);
        phone = findViewById(R.id.Txt_number);

        reference = FirebaseDatabase.getInstance().getReference("usersByUID");

        _loadingBar = new ProgressDialog(this);
        _loadingBar.setTitle("Loading");
        _loadingBar.setMessage("Please Wait");

        retriveUserInfo();

        purchase = (CardView)findViewById(R.id.purchase);
        history = (CardView)findViewById(R.id.history);
        info = (CardView)findViewById(R.id.information);
        cancel = (CardView)findViewById(R.id.cancel);

        purchase.setOnClickListener(this);
        history.setOnClickListener(this);
        info.setOnClickListener(this);
        cancel.setOnClickListener(this);


        //BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        //bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

    }


    private void retriveUserInfo() {
        reference.child(user)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists() && dataSnapshot.hasChild("fullname") && dataSnapshot.hasChild("phoneNum")){

                            _fullname = dataSnapshot.child("fullname").getValue().toString();
                            _phonenum = dataSnapshot.child("phoneNum").getValue().toString();
                            _email = dataSnapshot.child("email").getValue().toString();
                            _gender = dataSnapshot.child("gender").getValue().toString();
                            fullname.setText(_fullname);
                            phone.setText(_phonenum);

                            _loadingBar.dismiss();




                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Main_home.this, error.getMessage(), Toast.LENGTH_SHORT).show();
               //progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){
            case R.id.purchase:
                intent = new Intent(getApplicationContext(), PurchaseActivity.class);
                intent.putExtra("fullname",_fullname);
                intent.putExtra("phonenum",_phonenum);
                intent.putExtra("email",_email);
                intent.putExtra("gender",_gender);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
                break;

            case R.id.history:
                intent = new Intent(getApplicationContext(),HistoryActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
                break;

            case R.id.cancel:
                intent = new Intent(getApplicationContext(),CancelActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
                break;

            case R.id.information:
                intent = new Intent(getApplicationContext(),InformationActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
                finish();
                break;

        }

    }
}