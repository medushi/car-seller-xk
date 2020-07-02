package com.example.carsellerxk;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.carsellerxk.Helpers.LoginHelper;
import com.example.carsellerxk.Models.MyProfileModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {

    DatabaseReference _dbReference;
    TextView tvLoggedUserFirstName,tvLoggedUserLastName,tvLoggedUserEmail;

    public FragmentProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_profile,container,false);
        tvLoggedUserFirstName=view.findViewById(R.id.tvLoggedUserFirstName);
        tvLoggedUserLastName=view.findViewById(R.id.tvLoggedUserLastName);
        tvLoggedUserEmail=view.findViewById(R.id.tvLoggedUserEmail);
        _dbReference= FirebaseDatabase.getInstance().getReference().child("userinfo");
        final String loggedinUserEmail=LoginHelper.getLoggedInEmailUser(getActivity().getApplication());
        FetchAndSetLoggerUserInfo(loggedinUserEmail);
        return view;
    }

    private void FetchAndSetLoggerUserInfo(final String loggedUserEmail){
        final MyProfileModel[] profileModel = new MyProfileModel[1];
        _dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    if(dsp.child("email").getValue().toString().equals(loggedUserEmail)){
                        profileModel[0] =new MyProfileModel(dsp.child("firstName").getValue().toString(),dsp.child("lastName").getValue().toString(), loggedUserEmail);
                        tvLoggedUserFirstName.setText(profileModel[0].getFirstName());
                        tvLoggedUserLastName.setText(profileModel[0].getLastName());
                        tvLoggedUserEmail.setText(profileModel[0].getEmail());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
