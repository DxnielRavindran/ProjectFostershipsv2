  package com.danielappdev.fosterships;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class authentication extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference defReference = database.getReference("AuthCodes");
    DatabaseReference defReferenceTeams = database.getReference("Teams");
    EditText authcode;
    Button btnStart;
    String teamName = "Team Banana";
    boolean UpdatedUserCount = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        //ConstraintLayout CS_Auth = findViewById(R.id.CS_Auth);
        authcode = findViewById(R.id.etAuthCode);


        btnStart = (Button)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String authCodeToCheck;
                //String pushKey;
                //pushKey = TeamLeaderAuthentication.getActivityInstance().ReturnPushKey();
                authCodeToCheck = authcode.getText().toString();
                CheckAuthKey(defReference,authCodeToCheck,teamName);
            }
        });

    }

    private void CheckAuthKey(final DatabaseReference reference, final String authCodetoCheck, final String teamName) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {//Single data load
            @Override
            public void onDataChange(DataSnapshot snapshot) { //snapshot is the root reference
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String authCode = (ds.child("TeamAuthCode").getValue(String.class));//this checks all childs
                    String tName = (ds.child("Name").getValue(String.class));
                    if(authCode.equals(authCodetoCheck) && tName.equals(teamName)){
                        //Update user count here!
                        //WHen the users press the "Start/Authenticate" button, it runs the check and Updates the

                        UpdatedUserCount=true;

                        //Original Code
//                        Intent intent = new Intent(getApplicationContext(), gamephase2.class);
//                        startActivity(intent);
                        break;

                    }
                    else{

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void UpdateUserCounts(String teamName,final boolean UpdatedUserCount){

    }
}
