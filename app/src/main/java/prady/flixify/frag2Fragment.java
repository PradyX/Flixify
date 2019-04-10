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

public class frag2Fragment extends Fragment {
    public frag2Fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word(R.string.link1, R.string.tv9, R.drawable.ic_tv_black));
        words.add(new word(R.string.link2, R.string.tv1, R.drawable.ic_tv_black));
        words.add(new word(R.string.link3, R.string.tv2, R.drawable.ic_tv_black));
        words.add(new word(R.string.link4, R.string.tv3, R.drawable.ic_tv_black));
        words.add(new word(R.string.link5, R.string.tv6, R.drawable.ic_tv_black));
        words.add(new word(R.string.link6, R.string.tv7, R.drawable.ic_tv_black));
        words.add(new word(R.string.link7, R.string.tv8, R.drawable.ic_tv_black));


        wordAdapter adapter = new wordAdapter(getActivity(), words);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
                if (position==0) {
                    String url = "http://dl3.melimedia.net/mersad/serial/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==1) {
                    String url = "http://dl2.funsaber.net/serial/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==2) {
                    String url = "http://dl.tehmovies.pro/94/series/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==3) {
                    String url = "http://dl2.mihanpix.com/Serial/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==4) {
                    String url = "http://dl8.heyserver.in/serial/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==5) {
                    String url = "http://79.127.126.110/Serial/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==6) {
                    String url = "http://ftp.alphamediazone.com/Tv%20Series/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
            }
        });

        return rootView;
    }
}
