package com.example.keerthana.prezaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.parse.ParseACL;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class UserSpace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userspace);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_userspace, menu);
        return true;
    }

    public void logout(MenuItem item) {
        startActivity(new Intent(this, Logout.class));
    }

    public void create(View view) {
        startActivity(new Intent(this, CreatePost.class));
    }

    public void seeexp(View view) {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, 1);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Log.e("Custom","Error GP");
            Toast.makeText(UserSpace.this,"Unable to access google play services at the moment",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, UserSpace.class));
        }

    }

    public void seecom(View view) {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, 1);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Log.e("Custom","Error GP");
            Toast.makeText(UserSpace.this,"Unable to access google play services at the moment",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, UserSpace.class));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("Place: ", place.getName().toString());
                try {
                    startActivity(new Intent(this,SeeExpPosts.class).putExtra("place",place.getLatLng()));
                } catch (Exception e) {
                    Log.e("Custom",e.toString());
                    Toast.makeText(UserSpace.this,e.getMessage(),Toast.LENGTH_LONG);
                }
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e("Custom","Error AC");
                Toast.makeText(UserSpace.this,"Unable to access google play services at the moment",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, UserSpace.class));
                //Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

}
