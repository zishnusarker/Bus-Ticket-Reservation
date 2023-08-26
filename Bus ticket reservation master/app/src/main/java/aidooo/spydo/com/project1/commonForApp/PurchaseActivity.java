package aidooo.spydo.com.project1.commonForApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import aidooo.spydo.com.project1.Common.LoginSignUp.Login;
import aidooo.spydo.com.project1.Common.LoginSignUp.LoginSignUp;
import aidooo.spydo.com.project1.R;

public class PurchaseActivity extends AppCompatActivity {

    private AutoCompleteTextView fromStationName;
    private AutoCompleteTextView toStationName;
    private String fromStationNameTxt,toStationNameTxt,user,_fullname,_email,_phonenum,_gender;
    private DatePicker datePicker;
    private TextInputLayout fromStation,toStation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        fromStationName = findViewById(R.id.FromStationNameText);
        toStationName = findViewById(R.id.ToStationNameText);

        fromStation = findViewById(R.id.FromStationName);
        toStation = findViewById(R.id.ToStationName);

        user = getIntent().getStringExtra("user");
        _fullname = getIntent().getStringExtra("fullname");
        _phonenum = getIntent().getStringExtra("phonenum");
        _email = getIntent().getStringExtra("email");
        _gender = getIntent().getStringExtra("gender");

        datePicker = findViewById(R.id.date_picker);

        itemsforStationName();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), Main_home.class);
        intent.putExtra("currentUser",user);
        startActivity(intent);
        finish();
    }

    public void itemsforStationName() {

        String[] item = new String[]{

                "delhi",
                "mumbai",
                "chennai",
                "patna",
                "manali",
                "Assam",
                "Hyderabad",
                "Goa",
                "Gandhinagar",
                "Chandigarh",
                "Shimla",
                "Bengaluru ",
                "Shillong",
                "Jaipur",
                "Kolkata"

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                PurchaseActivity.this,
                R.layout.dropdown_items,
                item
        );

        fromStationName.setAdapter(adapter);
        toStationName.setAdapter(adapter);

    }

    public void nextPurchasePage(View view){

        if (!validateFromStaton() | !validateToStaton()) {
            return;
        }

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String journyDate = day+"/"+month+"/"+year;

        fromStationNameTxt = fromStationName.getText().toString().trim();
        toStationNameTxt = toStationName.getText().toString().trim();

        Intent intent = new Intent(this,PurchaseActivity_2nd.class);

        intent.putExtra("fromStation",fromStationNameTxt);
        intent.putExtra("toStation",toStationNameTxt);
        intent.putExtra("journyDate",journyDate);
        intent.putExtra("fullname",_fullname);
        intent.putExtra("phonenum",_phonenum);
        intent.putExtra("email",_email);
        intent.putExtra("gender",_gender);
        intent.putExtra("user",user);
        startActivity(intent);

    }

    private boolean validateFromStaton() {
        String val = fromStation.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fromStation.setError("Field can not be empty");
            return false;
        } else {
            fromStation.setError(null);
            fromStation.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateToStaton() {
        String val = toStation.getEditText().getText().toString().trim();
        String val1 = fromStation.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            toStation.setError("Field can not be empty");
            return false;
        }
        else if (val.equals(val1)){
            toStation.setError("From Station and To Station Can't be same");
            return false;
        }
        else {
            toStation.setError(null);
            toStation.setErrorEnabled(false);
            return true;
        }
    }


}