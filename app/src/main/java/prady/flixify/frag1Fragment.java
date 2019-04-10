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

public class frag1Fragment extends Fragment {
    public frag1Fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word(R.string.link1, R.string.movv11, R.drawable.ic_movie_org));
        words.add(new word(R.string.link2, R.string.mov2, R.drawable.ic_movie_org));
        words.add(new word(R.string.link3, R.string.mov1, R.drawable.ic_movie_org));
        words.add(new word(R.string.link4, R.string.mov3, R.drawable.ic_movie_org));
        words.add(new word(R.string.link5, R.string.mov4, R.drawable.ic_movie_org));
        words.add(new word(R.string.link6, R.string.mov5, R.drawable.ic_movie_org));
        words.add(new word(R.string.link7, R.string.mov7, R.drawable.ic_movie_org));
        words.add(new word(R.string.link8, R.string.mov8, R.drawable.ic_movie_org));
        words.add(new word(R.string.link9, R.string.mov9, R.drawable.ic_movie_org));
        words.add(new word(R.string.link10, R.string.movv10, R.drawable.ic_movie_org));
        words.add(new word(R.string.link11, R.string.movv13, R.drawable.ic_movie_org));
        words.add(new word(R.string.link12, R.string.movv14, R.drawable.ic_movie_org));

        wordAdapter adapter = new wordAdapter(getActivity(), words);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();

                if (position==0) {
                    String url = "http://103.91.144.230/ftpdata/Movies/Hollywood/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==1) {
                    String url = "http://dl2.haylimoviez.info/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==2) {
                    String url = "http://79.127.126.110/Movie/5/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==3) {
                    String url = "http://dl3.vaiomusic.org/Movie/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==4) {
                    String url = "http://sv4avadl.uploadt.com/Movie/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==5) {
                    String url = "http://dl.sitemovie.ir/movie/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==6) {
                    String url = "http://dl.filmbaroon.net/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==7) {
                    String url = "http://dl2.lavinmovie.ir/movie/2018/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==8) {
                    String url = "http://dl.mojoo.ir/upload/film/movies/2018/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==9) {
                    String url = "http://103.91.144.230/ftpdata/Movies/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==10) {
                    String url = "http://ftp.alphamediazone.com/Movies/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
                if (position==11) {
                    String url = "http://dl.galaxymovie.biz/Movie/";
                    Intent in = new Intent(getContext(), BrowserActivity.class);
                    in.putExtra("url", String.valueOf(url));
                    startActivity(in);
                }
            }
        });
        return rootView;
    }
}
