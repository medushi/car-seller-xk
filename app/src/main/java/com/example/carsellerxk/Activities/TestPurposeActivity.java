package com.example.carsellerxk.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carsellerxk.Helpers.TestingPurposeHelpers;
import com.example.carsellerxk.Models.TestModel;
import com.example.carsellerxk.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestPurposeActivity extends AppCompatActivity {

    TextView tvTest;
    Button btnTest;

    DatabaseReference _dbReference;
    List<TestModel> _testlist =new ArrayList<>();
    String _nextUserIdToInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_purpose);
        tvTest = (TextView) findViewById(R.id.tvTest);
        btnTest = (Button) findViewById(R.id.btnTest);

        _dbReference = FirebaseDatabase.getInstance().getReference().child("login");
        _dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp : dataSnapshot.getChildren())
                {
//                       _testlist.add(new TestModel((String)dsp.child("username").getValue(),"",1));
                }
                _nextUserIdToInsert="uid"+(int)(dataSnapshot.getChildrenCount()+1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error fetching/reading from firebase database",Toast.LENGTH_LONG);
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tvTest.setText(_testlist.get(0).getUserEmail());
                _dbReference.child(_nextUserIdToInsert).setValue(new TestModel("test@test.com","testpw",_nextUserIdToInsert));
//                TestingPurposeHelpers.set_currentUser();
            }
        });
    }
}