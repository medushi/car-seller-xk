package com.example.carsellerxk.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.carsellerxk.Helpers.Adapters.CarPostsAdapter;
import com.example.carsellerxk.Helpers.FireBaseRTDHelper;
import com.example.carsellerxk.Helpers.Statics;
import com.example.carsellerxk.Interfaces.IOnGetDataListener;
import com.example.carsellerxk.Models.NewPostUploadModel;
import com.example.carsellerxk.Models.PostsModel;
import com.example.carsellerxk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    DatabaseReference _dbReference;
    DatabaseReference _dbReferenceUsersInfo;
    RecyclerView postsRecyclerView;
    CarPostsAdapter carPostsAdapter;
    List<PostsModel> postsList = new ArrayList<>();
    ProgressBar loader;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loader=findViewById(R.id.loader);
        _dbReference = FirebaseDatabase.getInstance().getReference().child("posts");
        _dbReferenceUsersInfo =FirebaseDatabase.getInstance().getReference().child("userinfo");
        _dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    boolean hasImagesStored = dsp.child("images").getChildrenCount()>0;
                    if(hasImagesStored){
                        int imagesCount = (int) dsp.child("images").getChildrenCount();
                        if(imagesCount==1){
                            postsList.add(new PostsModel("Filani",dsp.child("title").getValue().toString(),dsp.child("city").getValue().toString(),
                                    dsp.child("typeOfAccelerate").getValue().toString(),dsp.child("manufacturer").getValue().toString(),
                                    dsp.child("images").child("0").getValue().toString(), Statics.DefaultImage,Statics.DefaultImage,
                                    Integer.parseInt(dsp.child("yearOfProduction").getValue().toString()),
                                    Double.valueOf(dsp.child("price").getValue().toString())));
                        }
                        if(imagesCount==2){
                            postsList.add(new PostsModel("Filani",dsp.child("title").getValue().toString(),dsp.child("city").getValue().toString(),
                                    dsp.child("typeOfAccelerate").getValue().toString(),dsp.child("manufacturer").getValue().toString(),
                                    dsp.child("images").child("0").getValue().toString(), dsp.child("images").child("1").getValue().toString(),
                                    Statics.DefaultImage,
                                    Integer.parseInt(dsp.child("yearOfProduction").getValue().toString()),
                                    Double.valueOf(dsp.child("price").getValue().toString())));
                        }
                        if(imagesCount>=3){
                            postsList.add(new PostsModel("Filani",dsp.child("title").getValue().toString(),dsp.child("city").getValue().toString(),
                                    dsp.child("typeOfAccelerate").getValue().toString(),dsp.child("manufacturer").getValue().toString(),
                                    dsp.child("images").child("0").getValue().toString(), dsp.child("images").child("1").getValue().toString(),
                                    dsp.child("images").child("2").getValue().toString(),
                                    Integer.parseInt(dsp.child("yearOfProduction").getValue().toString()),
                                    Double.valueOf(dsp.child("price").getValue().toString())));
                        }

                    }
                    //get count of images stored if not 3 then add a default images for the remaining empty image views
                    //testingDataList.add((new PostsModel("","","","","","","",""
                    //,100)));
                }
                postsRecyclerView=findViewById(R.id.postsRecyclerView);
                carPostsAdapter = new CarPostsAdapter(postsList, HomeActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL, false);
                postsRecyclerView.setLayoutManager(layoutManager);
                postsRecyclerView.setItemAnimator(new DefaultItemAnimator());
                postsRecyclerView.setAdapter(carPostsAdapter);
                carPostsAdapter.notifyDataSetChanged();
                postsRecyclerView.setVisibility(View.VISIBLE);
                loader.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        postsRecyclerView=findViewById(R.id.postsRecyclerView);
//        carPostsAdapter = new CarPostsAdapter(testingDataList, HomeActivity.this);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        postsRecyclerView.setLayoutManager(layoutManager);
//        postsRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        postsRecyclerView.setAdapter(carPostsAdapter);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        int pos = 0;
        //test recycleview
//        while (pos < 10) {
//            pos++;
//            testingDataList.add(new NewPostUploadModel(
//                    "","TEST","","",
//                    "","",0,null
//            ));
//        }

        //

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.newpost:
                        startActivity(new Intent(getApplicationContext()
                                , NewPostActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext()
                                , SavedActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                , ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.more:
                        startActivity(new Intent(getApplicationContext()
                                , Login.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


    }
//    public String getUserNameByUid(final String uid){
//        final String[] nametoReturn = {""};
//        _dbReferenceUsersInfo.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                nametoReturn[0]=dataSnapshot.child(uid).child("firstname").getValue().toString();
//
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        return nametoReturn[0];
//    }
//public String getUserNameByUid(DatabaseReference ref,String uid) {
//    final String[] result = {""};
//    FireBaseRTDHelper.readData(ref, new IOnGetDataListener() {
//        @Override
//        public void onSuccess(DataSnapshot dataSnapshot) {
//            //whatever you need to do with the data
//            result[0] = dataSnapshot.child("uid1").child("firstname").getValue().toString();
//        }
//        @Override
//        public void onStart() {
//        }
//
//        @Override
//        public void onFailure() {
//
//        }
//    });
//    return result[0];
//}

}
