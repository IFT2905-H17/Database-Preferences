package ca.umontreal.iro.hurtubin.toutv;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;

import ca.umontreal.iro.hurtubin.toutv.db.Favorite;

public class FavoritesActivity extends AppCompatActivity {
    private SQLiteDatabase db;

    ListView list;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        list = (ListView) findViewById(R.id.list);
        title = (TextView) findViewById(R.id.title);

        db = new DBHelper(this).getDB();

        Cursor c = Favorite.list(db);

        title.setText("" + c.getCount() + " favorit(s)");

        CursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c,
                new String[]{"key", "added"},
                new int[]{android.R.id.text1, android.R.id.text2},
                0);

        list.setAdapter(adapter);
    }
}



















