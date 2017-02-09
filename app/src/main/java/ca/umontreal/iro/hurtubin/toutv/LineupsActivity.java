package ca.umontreal.iro.hurtubin.toutv;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;

public class LineupsActivity extends AppCompatActivity {
    private String type = "Documentaires";

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineups);

        list = (ListView) findViewById(R.id.lineups);

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

                    if(convertView == null)
                        convertView = getLayoutInflater().inflate(R.layout.lineup_view, parent, false);

                    TextView title = (TextView) convertView.findViewById(R.id.title);
                    TextView description = (TextView) convertView.findViewById(R.id.description);
                    ImageView image = (ImageView) convertView.findViewById(R.id.image);

                    title.setText(lineups[position].title);
                    description.setText(lineups[position].shortDescription);

                    Picasso.with(getApplicationContext())
                            .load(lineups[position].image)
                            .into(image);

                    return convertView;
                }
            });

        }
    }
}
