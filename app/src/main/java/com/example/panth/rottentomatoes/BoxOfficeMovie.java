package com.example.panth.rottentomatoes;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by Jimmy Carlson on 7/13/2016.
 */

// Base data model
public class BoxOfficeMovie {
    private String synopsis;
    private String posterURL;
    private String title;
    private int criticScore;
    private int year;
    private ArrayList<String> castList;

    public int getYear()
    {
        return year;
    }

    public int getCriticScore()
    {
        return criticScore;
    }

    public String getCastList()
    {
        return TextUtils.join(", ", castList);
    }

    public String getPosterURL()
    {
        return  posterURL;
    }

    public String getSynopsis()
    {
        return synopsis;
    }

    public String getTitle()
    {
        return title;
    }


}
