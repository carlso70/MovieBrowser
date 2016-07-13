package com.example.panth.rottentomatoes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy Carlson on 7/13/2016.
 */
public class BoxOfficeMovieAdapter extends ArrayAdapter<BoxOfficeMovie> {
    public BoxOfficeMovieAdapter(Context context, ArrayList<BoxOfficeMovie> movies)
    {
        super(context, 0, movies);
    }

    @Override
    public View getView(int postition, View convertView, ViewGroup parent)
    {
        return null;
    }

}
