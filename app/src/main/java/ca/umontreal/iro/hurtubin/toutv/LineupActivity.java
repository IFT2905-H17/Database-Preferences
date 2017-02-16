package ca.umontreal.iro.hurtubin.toutv;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;

import java.io.IOException;

public class LineupActivity extends AppCompatActivity {

    ViewPager pager;

    String type = "Films";
    int startItem = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup);

        pager = (ViewPager) findViewById(R.id.pager);

        type = getIntent().getStringExtra("type");
        startItem = new Long(getIntent().getLongExtra("first_lineup_id", 0)).intValue();

        LineupFetcher fetcher = new LineupFetcher();

        fetcher.execute();
    }

    class LineupFetcher extends AsyncTask<Object, Object, Lineup[]> {

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
                public Fragment getItem(int i) {

                    LineupFragment fragment = new LineupFragment();

                    Bundle bundle = new Bundle();

                    bundle.putString("title", lineups[i].title);
                    bundle.putString("description", lineups[i].longDescription);
                    bundle.putString("img", lineups[i].image);
                    bundle.putString("url", lineups[i].url);

                    fragment.setArguments(bundle);

                    return fragment;
                }

                @Override
                public int getCount() {
                    return lineups.length;
                }
            });

            pager.setCurrentItem(startItem);
        }
    }
}
