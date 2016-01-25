package com.example.keerthana.prezaster;

import android.app.Application;
import android.content.Intent;
import android.location.Location;
import android.net.ParseException;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.parse.ParseACL;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CreatePost extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener{

    GoogleApiClient mGoogleApiClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

    }


    public void pick (View view) {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, 1);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Log.e("Custom","Error GP");
            Toast.makeText(CreatePost.this,"Unable to access google play services at the moment",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, UserSpace.class));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i( "Place: " ,place.getName().toString());
                //TODO Optionally using the current or the last known location
                //
                try {
                    Post post = new Post();
                    post.setHead(((EditText) findViewById(R.id.phead)).getText().toString());
                    post.setBody(((EditText) findViewById(R.id.pbody)).getText().toString());
                    post.setUser(ParseUser.getCurrentUser());
                    if (ParseUser.getCurrentUser().getInt("reputation") >= 100) {
                        post.put("type","expert");
                    } else {
                        post.put("type","community");
                    }
                    LatLng latlng = place.getLatLng();
                    ParseGeoPoint geoPoint = new ParseGeoPoint(latlng.latitude, latlng.longitude);
                    post.setLocation(geoPoint);

                    ParseACL acl = new ParseACL();
                    acl.setPublicReadAccess(true);
                    post.setACL(acl);

                    post.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {
                            finish();
                            Toast.makeText(CreatePost.this,"Content Posted", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),UserSpace.class));
                        }
                    });

                    } catch (Exception e) {
                    Log.e("Custom",e.toString());
                    Toast.makeText(CreatePost.this,e.getMessage(),Toast.LENGTH_LONG);
                }
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e("Custom","Error AC");
                Toast.makeText(CreatePost.this,"Unable to access google play services at the moment",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, UserSpace.class));
                //Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(CreatePost.this, "Cannot Access Data", Toast.LENGTH_SHORT).show();
    }
}
