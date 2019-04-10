package prady.flixify;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class frag3Fragment extends Fragment {
    public frag3Fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word(R.string.link1, R.string.anime2, R.drawable.ic_child_black));
        words.add(new word(R.string.link2, R.string.anime1, R.drawable.ic_child_black));


        wordAdapter adapter = new wordAdapter(getActivity(), words);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
                if (position==1) {
                    String url = "http://sr1.animelist1.ir/Anime/Ended/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
//                    Intent intent = new Intent(getContext(), A1.class);
//                    getContext().startActivity(intent);
                }
                if (position==0) {
                    String url = "http://ftp.alphamediazone.com/Anime%20%26%20Cartoon%20TV%20Series/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
            }
        });

        return rootView;
    }
}
