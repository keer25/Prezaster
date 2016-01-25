package com.example.keerthana.prezaster;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by keerthana on 25/1/16.
 */
@ParseClassName("Posts")
public class Post extends ParseObject {

    public String getHead() {
        return getString("head");
    }

    public void setHead(String value) {
        put("head", value);
    }

    public String getBody() {
        return getString("body");
    }

    public void setBody(String value) {
        put("body", value);
    }

    public String getType() {
        return getString("type");
    }

    public void setType(String value) {
        put("type", value);
    }

    public ParseUser getUser() {
        return getParseUser("user");
    }

    public void setUser(ParseUser value) {
        put("user", value);
    }

    public String getDisaster() {
        return getString("disaster");
    }
    public void setDisaster(String value) {
        put ("disaster",value);
    }

    public ParseGeoPoint getLocation() {
        return getParseGeoPoint("location");
    }

    public void setLocation(ParseGeoPoint value) {
        put("location", value);
    }

    public int getUpvotes() {return getInt("upvotes");}

    public void setUpvotes(int value) { put("upvotes",value); }

    public int getDownVotes() { return getInt("downvotes"); }

    public void setDownVotes(int value ) { put("downvotes",value); }

    public static ParseQuery<Post> getQuery() {
        return ParseQuery.getQuery(Post.class);
    }
}


