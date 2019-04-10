package prady.flixify;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MoviesFragment extends Fragment {
    public MoviesFragment() {
    }
    private FloatingActionButton mSearchBtn;
    private ShimmerFrameLayout progressBar;
    private RecyclerView recyclerLayout;
    private DatabaseReference databaseReference;
    FirebaseRecyclerOptions<DBAdapter> options;
    FirebaseRecyclerAdapter<DBAdapter, DBViewHolder> firebaseUsersAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tv_fragment, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Movies");
        progressBar = (ShimmerFrameLayout) rootView.findViewById(R.id.shimmer_view_container2);
        recyclerLayout = (RecyclerView) rootView.findViewById(R.id.recyclerlayout);
        recyclerLayout.setHasFixedSize(true);
        mSearchBtn = (FloatingActionButton) rootView.findViewById(R.id.searchBtn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newCase=new MoviesSearchFragment();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.down_to_up,R.anim.slide_out_left,R.anim.up_to_down,R.anim.slide_out_to_right);
                transaction.replace(R.id.f1Layout,newCase); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myRefresh();
                progressBar.setVisibility(View.GONE);
            }
        }, 2000);
        return rootView;
    }
    private void firebaseLoad() {
        options = new FirebaseRecyclerOptions.Builder<DBAdapter>().setQuery(databaseReference, DBAdapter.class).build();
        firebaseUsersAdapter = new FirebaseRecyclerAdapter<DBAdapter, DBViewHolder>(options) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                progressBar.setVisibility(View.GONE);
            }


            @Override
            protected void onBindViewHolder(@NonNull final DBViewHolder holder, final int position, @NonNull final DBAdapter model) {
//                Picasso.get().load(model.getImage()).into(holder.show_IMG, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        if (progressBar != null) {
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });

                Glide.with(getContext()).load(model.getImage()).listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                        return false;
                    }
                }).into(holder.show_IMG);

                holder.name_Text.setText(model.getName());
                holder.year_Text.setText(model.getYear());
                holder.url_Text.setText(model.getUrl());
                holder.rating_Text.setText(model.getRating());
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = model.getUrl();
                        String title = model.getName();
                        String image = model.getImage();
                        String year = model.getYear();
                        String banner = model.getImage();
                        String rate = model.getRating();
                        String seas = model.getSeason();
                        String plot = model.getPlot();
                        String genre = model.getGenre();
                        String cast = model.getCast();
                        String yt = model.getYt();
                        Intent in = new Intent(getContext(), ShowHome2.class);
                        in.putExtra("url", String.valueOf(url));
                        in.putExtra("name", String.valueOf(title));
                        in.putExtra("image", String.valueOf(image));
                        in.putExtra("image", String.valueOf(banner));
                        in.putExtra("year", String.valueOf(year));
                        in.putExtra("rating", String.valueOf(rate));
                        in.putExtra("season", String.valueOf(seas));
                        in.putExtra("plot", String.valueOf(plot));
                        in.putExtra("genre", String.valueOf(genre));
                        in.putExtra("cast", String.valueOf(cast));
                        in.putExtra("yt", String.valueOf(yt));
                        startActivity(in);

                    }
                });
            }

            @NonNull
            @Override
            public DBViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int i) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_tv_item, parent, false);
//
                return new DBViewHolder(view);
            }
        };

        int mNoOfColumns = Utility.calculateNoOfColumns(getContext());
        GridLayoutManager gridmanager = new GridLayoutManager(getContext(), mNoOfColumns);
        recyclerLayout.setLayoutManager(gridmanager);
        firebaseUsersAdapter.startListening();
        recyclerLayout.setAdapter(firebaseUsersAdapter);
    }
    public static class Utility {
        public static int calculateNoOfColumns(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (dpWidth / 120);
            return noOfColumns;
        }
    }
    public void myRefresh() {
        try {
            firebaseLoad();
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
