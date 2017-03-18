package ca.umontreal.iro.hurtubin.toutv;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ButtonsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lineup_buttons, container, false);

        Button films = (Button) v.findViewById(R.id.films);
        Button emissions = (Button) v.findViewById(R.id.emissions);
        Button documentaires = (Button) v.findViewById(R.id.documentaires);
        Button episodes = (Button) v.findViewById(R.id.episodes);

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

        return v;
    }
}
