package ca.umontreal.iro.hurtubin.toutv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class LineupFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.lineup_fragment, container, false);

        Bundle args = getArguments();

        String title = args.getString("title");
        String description = args.getString("description");
        String image = args.getString("image");

        TextView titleView = (TextView) v.findViewById(R.id.title);

        titleView.setText(title);

        TextView descriptionView = (TextView) v.findViewById(R.id.description);

        descriptionView.setText(description);

        ImageView imageView = (ImageView) v.findViewById(R.id.image);

        Picasso.with(getActivity())
                .load(image)
                .into(imageView);

        return v;
    }
}
