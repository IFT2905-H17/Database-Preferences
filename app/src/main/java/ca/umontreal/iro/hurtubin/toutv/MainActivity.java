package ca.umontreal.iro.hurtubin.toutv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button films = (Button) findViewById(R.id.films);
        Button emissions = (Button) findViewById(R.id.emissions);
        Button documentaires = (Button) findViewById(R.id.documentaires);
        Button episodes = (Button) findViewById(R.id.episodes);

        films.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LineupsActivity.class);

                intent.putExtra("type", "Films");

                startActivity(intent);
            }
        });

        emissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LineupsActivity.class);

                intent.putExtra("type", "Émissions");

                startActivity(intent);
            }
        });

        documentaires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LineupsActivity.class);

                intent.putExtra("type", "Documentaires");

                startActivity(intent);
            }
        });

        episodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LineupsActivity.class);

                intent.putExtra("type", "Épisodes");

                startActivity(intent);
            }
        });
    }
}
