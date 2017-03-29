package ca.umontreal.iro.hurtubin.toutv;

import android.content.ContentResolver;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TouTV {

    private static String url = "http://nasv3d.iro.umontreal.ca/toutv/section-populaires.json";
    private static OkHttpClient http;
    private static JSONObject json = null;

    private static Context context = null;

    public static void setContext(Context context) {
        TouTV.context = context;
    }

    public static JSONObject getJSON() throws IOException, JSONException {

        if(json != null)
            return json;

        Request request = new Request.Builder().url(url).build();

        if(http == null) {
            long cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(new File(context.getCacheDir(), "HttpResponseCache"), cacheSize);

            http = new OkHttpClient
                    .Builder()
                    .cache(cache)
                    .build();
        }

        Response response = http.newCall(request).execute();

        json = new JSONObject(response.body().string());

        return json;
    }

    public static Lineup[] getLineups(String type) throws IOException, JSONException {


        JSONObject json = getJSON();

        JSONArray categories = json.getJSONArray("Lineups");

        JSONObject category = null;

        for(int i=0; i<categories.length(); i++) {
            category = categories.getJSONObject(i);

            if(categories.getJSONObject(i).getString("Title").equals(type)) {
                break;
            }
        }

        JSONArray jsonLineups = category.getJSONArray("LineupItems");

        Lineup[] lineups = new Lineup[jsonLineups.length()];

        for(int i=0; i<jsonLineups.length(); i++) {

            JSONObject lineup = jsonLineups.getJSONObject(i);

            lineups[i] = new Lineup(
                    lineup.getString("Key"),
                    lineup.getString("Title"),
                    lineup.getString("Description"),
                    lineup.getString("ImageUrl"),
                    lineup.getJSONObject("Share").getString("AbsoluteUrl"),
                    lineup.getJSONObject("Details").getString("Description")
            );
        }

        return lineups;
    }
}
