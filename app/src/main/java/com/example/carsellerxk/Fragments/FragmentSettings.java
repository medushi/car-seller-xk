package com.example.carsellerxk.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.carsellerxk.Activities.Login;
import com.example.carsellerxk.Activities.ProfileActivity;
import com.example.carsellerxk.Helpers.LoginHelper;
import com.example.carsellerxk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSettings extends Fragment {


    public FragmentSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view = inflater.inflate(R.layout.fragment_fragment_settings,container,false);
       Button btnSignOut = (Button)view.findViewById(R.id.btnSignOut);

       btnSignOut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               LoginHelper.clearLoggedInEmailAddress(getActivity().getApplication());
               startActivity(new Intent(getActivity(), Login.class));

           }
       });
        return view;
    }

}
