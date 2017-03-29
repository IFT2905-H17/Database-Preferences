package ca.umontreal.iro.hurtubin.toutv;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;

import ca.umontreal.iro.hurtubin.toutv.db.Favorite;

public class LineupsActivity extends AppCompatActivity {
    private String type = "Documentaires";
    private SQLiteDatabase db;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineups);

        type = getIntent().getExtras().getString("type");

        list = (ListView) findViewById(R.id.lineups);

        db = new DBHelper(this).getDB();

        LineupFetcher fetcher = new LineupFetcher();

        fetcher.execute();
    }

    public class LineupFetcher extends AsyncTask<Object, Object, Lineup[]> {

        @Override
        protected Lineup[] doInBackground(Object... params) {

            Lineup[] lineups = new Lineup[0];

            try {
                lineups = TouTV.getLineups(type);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return lineups;
        }

        @Override
        protected void onPostExecute(final Lineup[] lineups) {

            list.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return lineups.length;
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    if (convertView == null)
                        convertView = getLayoutInflater().inflate(R.layout.lineup_view, parent, false);

                    Lineup lineup = lineups[position];

                    TextView title = (TextView) convertView.findViewById(R.id.title);
                    TextView description = (TextView) convertView.findViewById(R.id.description);
                    ImageView image = (ImageView) convertView.findViewById(R.id.image);

                    title.setText(lineup.title);
                    description.setText(lineup.shortDescription);

                    Picasso.with(getApplicationContext())
                            .load(lineup.image)
                            .into(image);

                    // Colore en vert les favorits
                    if (Favorite.exists(db, lineup.key))
                        convertView.setBackgroundColor(Color.rgb(136, 241, 136));
                    else
                        convertView.setBackgroundColor(Color.TRANSPARENT);

                    return convertView;
                }
            });

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(LineupsActivity.this, LineupsPagerActivity.class);

                    intent.putExtra("type", type);
                    intent.putExtra("position", position);

                    startActivity(intent);
                }
            });

            // Ajoute/retire des favorits au long click
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    String key = lineups[position].key;

                    if (Favorite.exists(db, key)) {
                        Favorite.remove(db, key);
                        Toast.makeText(LineupsActivity.this, "Favorit retiré", Toast.LENGTH_SHORT).show();
                    } else {
                        Favorite.add(db, key);
                        Toast.makeText(LineupsActivity.this, "Favorit ajouté", Toast.LENGTH_SHORT).show();
                    }

                    // Invalide les rows pour les faire se redessiner
                    list.invalidateViews();

                    // Indique qu'on a consommé l'événement
                    return true;
                }
            });
        }
    }
}
