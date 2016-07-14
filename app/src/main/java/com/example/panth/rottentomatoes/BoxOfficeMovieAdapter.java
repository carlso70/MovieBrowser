package com.example.panth.rottentomatoes;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
        BoxOfficeMovie movie = getItem(postition);
        // Check if existing view is being reused, otherwise inflate the view
        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_box_office_movie, parent, false);
        }
        // Lookup views within item layout
        TextView tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
        TextView tvCrititcsScore = (TextView)convertView.findViewById(R.id.tvCriticsScore);
        TextView tvCast = (TextView)convertView.findViewById(R.id.tvCast);
        ImageView ivPosterImage = (ImageView)convertView.findViewById(R.id.ivPosterImage);

        // Set the data within the movie item view
        tvTitle.setText(movie.getTitle());
        tvCrititcsScore.setText("Score: " + movie.getCriticScore() + "%");
        tvCast.setText(movie.getCastList());
        Picasso.with(getContext()).load(movie.getPosterURL()).into(ivPosterImage);

        return convertView;
    }

}
