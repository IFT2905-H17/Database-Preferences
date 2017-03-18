package ca.umontreal.iro.hurtubin.toutv;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                if(position == 0)
                    return new ButtonsFragment();

                ListFragment f = new ListFragment();

                ArrayList<String> items = new ArrayList<String>();

                items.add("Rangée 1");
                items.add("Rangée 2");
                items.add("Rangée 3");

                items.add("Test");
                items.add("...");

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        items
                );

                f.setListAdapter(arrayAdapter);

                return f;
            }

            @Override
            public int getCount() {
                return 2;
            }

            private String[] titles = new String[]{
                    "Films",
                    "Liste"
            };

            @Override
            public CharSequence getPageTitle(int position) {

                return titles[position];
            }
        });

        tabs.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem about = menu.findItem(R.id.about);

        about.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Toast.makeText(MainActivity.this, "Cette application a été créée pour des fins pédagogies seulement. Veuillez l'utiliser pour des fins pédagogiques seulement.", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        return true;
    }
}
