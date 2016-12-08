package app.invictus.com.popularmovies.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by invictus on 12/8/16.
 */

public class AppUtils {


    public static String prettyDate(String date) {
        SimpleDateFormat datefm = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = null;
        try {
            currentDate = datefm.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        datefm = new SimpleDateFormat("MMM yyyy");
        return datefm.format(currentDate);
    }


    private static String getGenreById(int _id) {
        switch (_id) {
            case 28:
                return "Action";
            case 12:
                return "Adventure";
            case 16:
                return "Animation";
            case 35:
                return "Comedy";
            case 80:
                return "Crime";
            case 99:
                return "Documentary";
            case 18:
                return "Drama";
            case 10751:
                return "Family";
            case 14:
                return "Fantasy";
            case 36:
                return "History";
            case 27:
                return "Horror";
            case 10402:
                return "Music";
            case 9648:
                return "Mystery";
            case 10749:
                return "Romance";
            case 878:
                return "Science Fiction";
            case 10770:
                return "TV Movie";
            case 53:
                return "Thriller";
            case 10752:
                return "War";
            case 37:
                return "Western";
            default:
                return "General";
        }

    }

    public static List<String> getGenre(List<Integer> list) {
        if (list.isEmpty()) {
            return null;
        }
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String pre = getGenreById(list.get(i));
            String x = pre.split(" ")[0];
            if (temp.size() != 2) {
                temp.add(x);
            }
        }
        return temp;
    }
}
