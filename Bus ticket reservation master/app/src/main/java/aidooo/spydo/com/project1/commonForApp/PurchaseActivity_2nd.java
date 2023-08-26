
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Random;

import aidooo.spydo.com.project1.Common.LoginSignUp.Login;
import aidooo.spydo.com.project1.Common.LoginSignUp.LoginSignUp;
import aidooo.spydo.com.project1.Common.LoginSignUp.Verify_OTP;
import aidooo.spydo.com.project1.Database.userDatabaseHelperClass;
import aidooo.spydo.com.project1.R;

public class PurchaseActivity_2nd extends AppCompatActivity {

    private String fromStationNameTxt, toStationNameTxt, journeyDate, user, _fullname, _email, _phonenum, _gender, busNameTxt, busTypeTxt, seatNoTxt;
    private AutoCompleteTextView busName;
    private AutoCompleteTextView seatNo;
    private AutoCompleteTextView busType;
    private String doubleRandom,ticketKey;
    private TextInputLayout busNameLayout,seatNoLayout,busTypeLayout;
    HashMap<String, String> ticketMap;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_2nd);

        busName = findViewById(R.id.busNameText);
        seatNo = findViewById(R.id.busSeatText);
        busType = findViewById(R.id.busTypeNameText);
        busNameLayout = findViewById(R.id.busName);
        seatNoLayout = findViewById(R.id.busSeat);
        busTypeLayout = findViewById(R.id.busTypeName);

        reference = FirebaseDatabase.getInstance().getReference();

        fromStationNameTxt = getIntent().getStringExtra("fromStation");
        toStationNameTxt = getIntent().getStringExtra("toStation");
        journeyDate = getIntent().getStringExtra("journyDate");
        _fullname = getIntent().getStringExtra("fullname");
        _phonenum = getIntent().getStringExtra("phonenum");
        _email = getIntent().getStringExtra("email");
        _gender = getIntent().getStringExtra("gender");
        user = getIntent().getStringExtra("user");

        itemsforBusName();
        itemsforSeatNo();
        itemsforBusType();

    }

    private void itemsforSeatNo() {
        String[] item = new String[]{

                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20",
                "21",
                "22",
                "23",
                "24",
                "25",
                "26",
                "27",
                "28",
                "29",
                "30",

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                PurchaseActivity_2nd.this,
                R.layout.dropdown_items,
                item
        );

        seatNo.setAdapter(adapter);
    }


    public void itemsforBusName() {

        String fromStationNameTxt0 = fromStationNameTxt;
        String toStationNameTxt1 = toStationNameTxt;

        /*Toast.makeText(this, journeyDate, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, fromStationNameTxt, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, toStationNameTxt, Toast.LENGTH_SHORT).show();*/

        String[] item = new String[]{

                "delhi express",
                "mumbai express"
                /*"chennai express",
                "patna express",
                "manali express",
                "Assam express",
                "Hyderabad express",
                "Goa express",
                "Gandhinagar express",
                "Chandigarh express",
                "Shimla express",
                "Bengaluru express",
                "Shillong express",
                "Jaipur express",
                "Kolkata express"*/

        };

        String[] item2 = new String[]{
                "patna express",
                "manali express",
        };

        String[] item3 = new String[]{
                "chennai express",
                "patna express",
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                PurchaseActivity_2nd.this,
                R.layout.dropdown_items,
                item
        );

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(
                PurchaseActivity_2nd.this,
                R.layout.dropdown_items,
                item2
        );

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(
                PurchaseActivity_2nd.this,
                R.layout.dropdown_items,
                item3
        );


        if (fromStationNameTxt.equals("delhi") && toStationNameTxt.equals("mumbai")
                || fromStationNameTxt.equals("mumbai") && toStationNameTxt.equals("delhi")) {
            busName.setAdapter(adapter);
        } else if (fromStationNameTxt.equals("patna") && toStationNameTxt.equals("manali")
                || fromStationNameTxt.equals("manali") && toStationNameTxt.equals("patna")) {
            busName.setAdapter(adapter2);
        } else if (fromStationNameTxt.equals("patna") && toStationNameTxt.equals("chennai")
                || fromStationNameTxt.equals("chennai") && toStationNameTxt.equals("patna")) {
            busName.setAdapter(adapter3);
        }

    }

    public void itemsforBusType() {

        String[] item = new String[]{

                "AC",
                "Non_AC"

        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                PurchaseActivity_2nd.this,
                R.layout.dropdown_items,
                item
        );

        busType.setAdapter(adapter);


    }

    public void booking(View view) {

        if (!validateBusType() | !validateBusName() | !validateSeatNo()) {
            return;
        }

        ticketKey();

        busNameTxt = busName.getText().toString().trim();
        busTypeTxt = busType.getText().toString().trim();
        seatNoTxt = seatNo.getText().toString().trim();

        final ProgressDialog _loadingBar;
        _loadingBar = new ProgressDialog(this);

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("tickets");

        ticketMap = new HashMap<>();

        ticketMap.put("fullname", _fullname);
        ticketMap.put("phoneNum", _phonenum);
        ticketMap.put("gender", _gender);
        ticketMap.put("email", _email);
        ticketMap.put("uid", user);
        ticketMap.put("fromStation", fromStationNameTxt);
        ticketMap.put("toStation", toStationNameTxt);
        ticketMap.put("journeyDate", journeyDate);
        ticketMap.put("BusName", busNameTxt);
        ticketMap.put("BusType", busTypeTxt);
        ticketMap.put("seatNo", seatNoTxt);
        ticketMap.put("TicketKey", ticketKey);

        _loadingBar.setTitle("Booking");
        _loadingBar.setMessage("Please Wait...");
        _loadingBar.setCanceledOnTouchOutside(true);
        _loadingBar.show();

        reference.child(fromStationNameTxt).child(toStationNameTxt).child(busTypeTxt).child(user).setValue(ticketMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(PurchaseActivity_2nd.this, "Booked successfully", Toast.LENGTH_SHORT).show();
                            _loadingBar.dismiss();
                            Intent intent = new Intent(getApplicationContext(), Main_home.class);
                            intent.putExtra("currentUser", user);
                            startActivity(intent);
                            finish();

                        } else {
                            String massage = task.getException().toString().trim();
                            _loadingBar.dismiss();
                            Toast.makeText(PurchaseActivity_2nd.this, "Error :" + massage, Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

    private void ticketKey () {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        ticketKey = buffer.toString();


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

    private boolean validateBusName() {
        String val = busNameLayout.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            busNameLayout.setError("Field can not be empty");
            return false;
        } else {
            busNameLayout.setError(null);
            busNameLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateSeatNo() {
        String val = seatNoLayout.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            seatNoLayout.setError("Field can not be empty");
            return false;
        } else {
            seatNoLayout.setError(null);
            seatNoLayout.setErrorEnabled(false);
            return true;
        }
    }



}