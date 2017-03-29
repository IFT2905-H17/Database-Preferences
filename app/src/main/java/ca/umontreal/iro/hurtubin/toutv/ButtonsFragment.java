package ca.umontreal.iro.hurtubin.toutv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ButtonsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(getContext());

        // Écrase le nom défini dans les préférences
        sharedPreferences.edit().putString("nom", "Nicolas").commit();

        String nom = sharedPreferences.getString("nom", "Valeur par défaut");

        Toast.makeText(getContext(), "Bonjour, " + nom, Toast.LENGTH_SHORT).show();


        View v = inflater.inflate(R.layout.lineup_buttons, container, false);

        Button films = (Button) v.findViewById(R.id.films);
        Button emissions = (Button) v.findViewById(R.id.emissions);
        Button documentaires = (Button) v.findViewById(R.id.documentaires);
        Button episodes = (Button) v.findViewById(R.id.episodes);
        Button favorites = (Button) v.findViewById(R.id.favorites);
        Button preferences = (Button) v.findViewById(R.id.preferences);

        films.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LineupsActivity.class);

                intent.putExtra("type", "Films");

                startActivity(intent);
            }
        });

        emissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LineupsActivity.class);

                intent.putExtra("type", "Émissions");

                startActivity(intent);
            }
        });

        documentaires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LineupsActivity.class);

                intent.putExtra("type", "Documentaires");

                startActivity(intent);
            }
        });

        episodes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LineupsActivity.class);

                intent.putExtra("type", "Épisodes");

                startActivity(intent);
            }
        });


        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FavoritesActivity.class);

                startActivity(intent);
            }
        });

        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PreferencesActivity.class);

                startActivity(intent);
            }
        });

        return v;
    }
}
