package ca.umontreal.iro.hurtubin.toutv;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TouTV {

    private static String url = "http://nasv3d.iro.umontreal.ca/toutv/section-populaires.json";
    private static OkHttpClient http;
    private static JSONObject json = null;

    private static JSONObject getJSON() throws IOException, JSONException {

        if(json != null)
            return json;

        Request request = new Request.Builder().url(url).build();

        if(http == null)
            http = new OkHttpClient();

        Response response = http.newCall(request).execute();

        json = new JSONObject(response.body().string());

        return json;
    }

    public static Lineup[] getLineups(String type) throws IOException, JSONException {
        ArrayList<Lineup> list = new ArrayList<>();

        // TODO : construire un tableau d'objets Lineup à partir du JSON

        return null;
    }
}
