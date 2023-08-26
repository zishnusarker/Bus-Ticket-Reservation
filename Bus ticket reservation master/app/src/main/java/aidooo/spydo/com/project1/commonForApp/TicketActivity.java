package aidooo.spydo.com.project1.commonForApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import aidooo.spydo.com.project1.R;

public class TicketActivity extends AppCompatActivity {


    private String user,_fullname,_email,_phonenum,_gender,fromStationNameTxt,toStationNameTxt,busTypeTxt;
    private String _fromStationNameTxt,_toStationNameTxt,_busTypeTxt,_busName,_journeyDate,_seatNo,ticketKey;
    DatabaseReference reference;
    private TextView fullname,email,phonenum,gender,from,to,busType,journeyDate,seatNo,invoice,busName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        user = getIntent().getStringExtra("currentUser");
        fromStationNameTxt = getIntent().getStringExtra("fromStationNameTxt");
        toStationNameTxt = getIntent().getStringExtra("toStationNameTxt");
        busTypeTxt = getIntent().getStringExtra("busTypeTxt");

        reference = FirebaseDatabase.getInstance().getReference();

        fullname = findViewById(R.id.fullnameTxt);
        invoice = findViewById(R.id.invoiceTxt);
        email = findViewById(R.id.emailTxt);
        phonenum = findViewById(R.id.phoneTxt);
        gender = findViewById(R.id.genderTxt);
        from = findViewById(R.id.fromStationName);
        to = findViewById(R.id.toStationName);
        busName = findViewById(R.id.busNameText);
        busType = findViewById(R.id.busTypeNameText);
        seatNo = findViewById(R.id.busSeatText);
        journeyDate = findViewById(R.id.journeyDateTxt);


    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    private void loadData() {

        final ProgressDialog _loadingBar;
        _loadingBar = new ProgressDialog(this);
        _loadingBar.setTitle("Searching");
        _loadingBar.setMessage("Please Wait....");
        _loadingBar.setCanceledOnTouchOutside(true);
        _loadingBar.show();

        reference.child("tickets").child(fromStationNameTxt).child(toStationNameTxt).child(busTypeTxt).child(user)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists() && dataSnapshot.hasChild("fullname") && dataSnapshot.hasChild("phoneNum")){

                            _fullname = dataSnapshot.child("fullname").getValue().toString();
                            _phonenum = dataSnapshot.child("phoneNum").getValue().toString();
                            _email = dataSnapshot.child("email").getValue().toString();
                            _gender = dataSnapshot.child("gender").getValue().toString();
                            _busName = dataSnapshot.child("BusName").getValue().toString();
                            _busTypeTxt = dataSnapshot.child("BusType").getValue().toString();
                            _seatNo = dataSnapshot.child("seatNo").getValue().toString();
                            _fromStationNameTxt = dataSnapshot.child("fromStation").getValue().toString();
                            _toStationNameTxt = dataSnapshot.child("toStation").getValue().toString();
                            _journeyDate = dataSnapshot.child("journeyDate").getValue().toString();
                            ticketKey = dataSnapshot.child("TicketKey").getValue().toString();

                            invoice.setText(ticketKey);
                            fullname.setText(_fullname);
                            email.setText(_email);
                            gender.setText(_gender);
                            phonenum.setText(_phonenum);
                            from.setText(_fromStationNameTxt);
                            to.setText(_toStationNameTxt);
                            busName.setText(_busName);
                            busType.setText(_busTypeTxt);
                            seatNo.setText(_seatNo);
                            journeyDate.setText(_journeyDate);

                            _loadingBar.dismiss();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(TicketActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,HistoryActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }
}