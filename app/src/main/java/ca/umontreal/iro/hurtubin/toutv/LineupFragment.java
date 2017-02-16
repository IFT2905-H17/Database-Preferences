package ca.umontreal.iro.hurtubin.toutv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class LineupFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lineup_fragment, container, false);

        Bundle args = getArguments();

        String title = args.getString("title");
        String description = args.getString("description");
        String img = args.getString("img");
        final String url = args.getString("url");

        TextView titleView = (TextView) v.findViewById(R.id.title);
        titleView.setText(title);

        TextView descriptionView = (TextView) v.findViewById(R.id.description);
        descriptionView.setText(description);

        ImageView imageView = (ImageView) v.findViewById(R.id.image);

        Picasso.with(getActivity().getApplicationContext())
                .load(img)
                .into(imageView);

        Button link = (Button) v.findViewById(R.id.url);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                startActivity(intent);
            }
        });

        return v;
    }
}
