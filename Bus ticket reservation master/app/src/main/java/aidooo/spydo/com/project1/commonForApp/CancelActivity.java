package aidooo.spydo.com.project1.commonForApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import aidooo.spydo.com.project1.Common.LoginSignUp.Login;
import aidooo.spydo.com.project1.Common.LoginSignUp.LoginSignUp;
import aidooo.spydo.com.project1.Common.LoginSignUp.Verify_OTP;
import aidooo.spydo.com.project1.Database.userDatabaseHelperClass;
import aidooo.spydo.com.project1.R;

public class CancelActivity extends AppCompatActivity {

    DatabaseReference reference;
    private AutoCompleteTextView fromStationName;
    private AutoCompleteTextView toStationName;
    private AutoCompleteTextView busType;
    private TextInputLayout fromStation, toStation, busTypeLayout;

    private String user, fromStationNameTxt, toStationNameTxt, busTypeTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        user = getIntent().getStringExtra("user");

        reference = FirebaseDatabase.getInstance().getReference();

        fromStationName = findViewById(R.id.FromStationNameText);
        toStationName = findViewById(R.id.ToStationNameText);
        busType = findViewById(R.id.busTypeNameText);
        fromStation = findViewById(R.id.FromStationName);
        toStation = findViewById(R.id.ToStationName);
        busTypeLayout = findViewById(R.id.busTypeName);

        itemsforStationName();
        itemsforBusType();

    }

    public void deleteTicket(View view) {

        fromStationNameTxt = fromStationName.getText().toString().trim();
        toStationNameTxt = toStationName.getText().toString().trim();
        busTypeTxt = busType.getText().toString().trim();

        final ProgressDialog _loadingBar;
        _loadingBar = new ProgressDialog(this);
        _loadingBar.setTitle("Cancelling");
        _loadingBar.setMessage("Please Wait...");
        _loadingBar.show();

        if (!validateBusType() | !validateFromStaton() | !validateToStaton()) {
            _loadingBar.dismiss();
            return;
        }

        reference.child("tickets").child(fromStationNameTxt).child(toStationNameTxt).child(busTypeTxt).child(user)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists() /*&& dataSnapshot.hasChild("fullname") && dataSnapshot.hasChild("phoneNum")*/) {

                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                dataSnapshot1.getRef().removeValue();
                            }

                            Toast.makeText(CancelActivity.this, "Cancellation complete", Toast.LENGTH_SHORT).show();
                            _loadingBar.dismiss();
                            Intent intent = new Intent(CancelActivity.this, Main_home.class);
                            intent.putExtra("currentUser", user);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(CancelActivity.this, "Ticket Not Found", Toast.LENGTH_SHORT).show();
                            _loadingBar.dismiss();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(CancelActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.INVISIBLE);
                    }


                });

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
                CancelActivity.this,
                R.layout.dropdown_items,
                item
        );

        fromStationName.setAdapter(adapter);
        toStationName.setAdapter(adapter);

    }

    public void itemsforBusType() {

        String[] item = new String[]{

                "AC",
                "Non_AC"

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                CancelActivity.this,
                R.layout.dropdown_items,
                item
        );

        busType.setAdapter(adapter);


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

    private boolean validateBusType() {
        String val = busTypeLayout.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            busTypeLayout.setError("Field can not be empty");
            return false;
        } else {
            busTypeLayout.setError(null);
            busTypeLayout.setErrorEnabled(false);
            return true;
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(this,Main_home.class);
        intent.putExtra("currentUser",user);
        startActivity(intent);
        finish();
    }
}