package com.example.keerthana.prezaster;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseGeoPoint;
import com.parse.ParseQuery;
import com.google.android.gms.location.places.Place;
import java.util.ArrayList;
import java.util.List;

public class SeeExpPosts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_exp_posts);
        ParseQuery<Post> mapQuery =  Post.getQuery();
        LatLng loc = (LatLng) getIntent().getExtras().get("place");
        //Only shows places within 10 kms
        mapQuery.whereWithinKilometers("location", new ParseGeoPoint(loc.latitude, loc.longitude), 10);
        mapQuery.include("user");
        mapQuery.whereEqualTo("type","expert");
        mapQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, com.parse.ParseException e) {

            }
        });
        // Construct the data source
        ArrayList<Post> arrayOfUsers = new ArrayList<Post>();
// Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.posts_listview);
        listView.setAdapter(adapter);
    }

    public class UsersAdapter extends ArrayAdapter<Post> {
        public UsersAdapter(Context context, ArrayList<Post> posts) {
            super(context, 0, posts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Post post = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_element, parent, false);
            }
            TextView tvName = (TextView) convertView.findViewById(R.id.title);
            TextView tvHome = (TextView) convertView.findViewById(R.id.content);
            tvName.setText(post.getHead());
            tvHome.setText(post.getBody());
            return convertView;
        }
    }
}