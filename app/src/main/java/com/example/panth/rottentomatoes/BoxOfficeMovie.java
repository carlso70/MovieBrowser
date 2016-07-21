package com.example.panth.rottentomatoes;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.jar.Pack200;

/**
 * Created by Jimmy Carlson on 7/13/2016.
 */

// Base data model
public class BoxOfficeMovie implements Serializable {
    private static final long serialVersionUID = -8959832007991513854L;
    private String synopsis;
    private String posterURL;
    private String title;
    private String largePosterUrl;
    private String criticsConsensus;
    private int audienceScore;
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

    // Returns a BoxOfficeMovie given the expected JSON
    // Stores title, year, synopsis, posterURL, critic score, and cast list
    public static BoxOfficeMovie fromJson(JSONObject jsonObject)
    {
        BoxOfficeMovie boxOfficeMovie = new BoxOfficeMovie();
        try {
            boxOfficeMovie.largePosterUrl = jsonObject.getJSONObject("posters").getString("detailed");
            boxOfficeMovie.criticsConsensus = jsonObject.getString("critics_consensus");
            boxOfficeMovie.audienceScore = jsonObject.getJSONObject("ratings").getInt("audience_score");
            boxOfficeMovie.title = jsonObject.getString("title");
            boxOfficeMovie.year = jsonObject.getInt("year");
            boxOfficeMovie.synopsis = jsonObject.getString("synopsis");
            boxOfficeMovie.posterURL = jsonObject.getJSONObject("posters").getString("thumbnail");
            boxOfficeMovie.criticScore = jsonObject.getJSONObject("ratings").getInt("critics_score");

            boxOfficeMovie.castList = new ArrayList<String>();
            JSONArray abridgedCast = jsonObject.getJSONArray("abridged_cast");
            for (int i = 0; i < abridgedCast.length(); i++)
            {
                boxOfficeMovie.castList.add(abridgedCast.getJSONObject(i).getString("name"));
            }


        }catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
        return boxOfficeMovie;
    }

    // Transforms array of box office movie json results into the BoxOfficeMovies
    public static ArrayList<BoxOfficeMovie> fromJson(JSONArray jsonArray)
    {
        ArrayList<BoxOfficeMovie> movies = new ArrayList<BoxOfficeMovie>(jsonArray.length());
        // Each result in json array, decode and convert to a BoxOfficeMovie
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject moviesJSON = null;
            try {
                moviesJSON = jsonArray.getJSONObject(i);
            }catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }

            BoxOfficeMovie movie = BoxOfficeMovie.fromJson(moviesJSON);
            if (movie != null)
            {
                movies.add(movie);
            }
        }

        return movies;
    }

    public String getLargePosterUrl() {
        return largePosterUrl;
    }

    public String getCriticsConsensus() {
        return criticsConsensus;
    }

    public int getAudienceScore() {
        return audienceScore;
    }
}
