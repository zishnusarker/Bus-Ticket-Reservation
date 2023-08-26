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

import aidooo.spydo.com.project1.R;

public class HistoryActivity extends AppCompatActivity {

    private String user,_fullname,_email,_phonenum,_gender,fromStationNameTxt,toStationNameTxt,busTypeTxt;
    private String _fromStationNameTxt,_toStationNameTxt,_busTypeTxt,_busName,_journeyDate,_seatNo,ticketKey;
    private AutoCompleteTextView fromStationName;
    private AutoCompleteTextView toStationName;
    private AutoCompleteTextView busType;
    private TextInputLayout fromStation,toStation,busTypeLayout;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Main_home.class);
        intent.putExtra("currentUser",user);
        startActivity(intent);
        finish();
    }

    public void searchTicket(View view){

        fromStationNameTxt = fromStationName.getText().toString().trim();
        toStationNameTxt = toStationName.getText().toString().trim();
        busTypeTxt = busType.getText().toString().trim();

        final ProgressDialog _loadingBar;
        _loadingBar = new ProgressDialog(this);
        _loadingBar.setTitle("Searching");
        _loadingBar.setMessage("Please Wait....");
        _loadingBar.setCanceledOnTouchOutside(true);
        _loadingBar.show();

        if (!validateBusType() | !validateFromStaton() | !validateToStaton()) {
            _loadingBar.dismiss();
            return;
        }


        reference.child("tickets").child(fromStationNameTxt).child(toStationNameTxt).child(busTypeTxt).child(user)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists() && dataSnapshot.hasChild("fullname") && dataSnapshot.hasChild("phoneNum")){

                            Intent intent = new Intent(HistoryActivity.this,TicketActivity.class);
                            intent.putExtra("currentUser",user);
                            intent.putExtra("fromStationNameTxt",fromStationNameTxt);
                            intent.putExtra("toStationNameTxt",toStationNameTxt);
                            intent.putExtra("busTypeTxt",busTypeTxt);
                            startActivity(intent);
                            finish();

                            _loadingBar.dismiss();

                        }else{
                            Toast.makeText(HistoryActivity.this, "Ticket Not Found", Toast.LENGTH_SHORT).show();
                            _loadingBar.dismiss();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(HistoryActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                HistoryActivity.this,
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
                HistoryActivity.this,
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

        if (val.isEmpty()) {
            toStation.setError("Field can not be empty");
            return false;
        } else {
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




}