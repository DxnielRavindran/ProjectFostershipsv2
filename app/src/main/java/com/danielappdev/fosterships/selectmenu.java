package com.danielappdev.fosterships;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class selectmenu extends AppCompatActivity {

    Button btnJoin;
    Button btnBook;
    Button btnAdminPage;
    EditText inviteCode;
    TextView textview2;
    Integer eventID;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference defReference = database.getReference("Events");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmenu);
        //Log.d("selectmenu", "runs on selectmenu");
        inviteCode = findViewById(R.id.txtInviteCode);
        btnJoin = findViewById(R.id.btnJoinSelect);
        btnBook = findViewById(R.id.btnBook);
        btnAdminPage = findViewById(R.id.btnAdminPage);

        textview2 = findViewById(R.id.textView2);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer input = Integer.parseInt((inviteCode.getText().toString()));
                if(input > 0){
                    CheckFireData(defReference, input);
                }
                else{
                    inviteCode.setError("Check if you have the proper invite code");
                }

            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                startActivity(intent);
            }
        });
        btnAdminPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
            }
        });
        btnAdminPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
            }
        });




    }
    private void ShowDialog(String title,String text) {
        AlertDialog alertDialog = new AlertDialog.Builder(selectmenu.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(text);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    private void CheckFireData(final DatabaseReference reference, final Integer eventIDCurrent) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snapshot) { //snapshot is the root reference
                int test = 0;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    int eventID = (ds.child("eventID").getValue(Integer.class));
                    if (eventID == eventIDCurrent) {
                        Intent intent = new Intent(getApplicationContext(), normaluserwaitingscreen.class);
                        startActivity(intent);
                        test = 1;
                        break;
                    }
//                    else {
//
//                        ShowDialog("EventID not found!","EventID was not found... Please try again or look for your event organisers.");
//
//                    }
                }
                Log.d("test", "test"+test);
                if (test==0){
                    ShowDialog("EventID not found!","EventID was not found... Please try again or look for your event organisers.");}

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
