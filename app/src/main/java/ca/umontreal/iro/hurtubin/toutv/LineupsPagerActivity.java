package ca.umontreal.iro.hurtubin.toutv;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;

public class LineupsPagerActivity extends AppCompatActivity {

    private String type = "Documentaires";

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineups_pager);

        pager = (ViewPager) findViewById(R.id.pager);

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
            pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public int getCount() {
                    return lineups.length;
                }

                @Override
                public Fragment getItem(int position) {

                    Lineup lineup = lineups[position];

                    LineupFragment fragment = new LineupFragment();

                    Bundle args = new Bundle();

                    args.putString("title", lineup.title);
                    args.putString("description", lineup.longDescription);
                    args.putString("image", lineup.image);

                    fragment.setArguments(args);

                    return fragment;
                }
            });
        }
    }
}
