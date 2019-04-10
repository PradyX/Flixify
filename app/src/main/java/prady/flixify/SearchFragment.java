package prady.flixify;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchFragment extends Fragment {
    public SearchFragment() {
    }
    private LottieAnimationView animationView;
    private ImageView mSearchButton;
    private TextInputEditText mSearchInputText;
    private FloatingActionButton closeBtn;
//    private ShimmerFrameLayout shimmerFrameLayout;
    private RecyclerView recyclerLayout;
    private DatabaseReference databaseReference;
    FirebaseRecyclerOptions<DBAdapter> options;
    FirebaseRecyclerAdapter<DBAdapter, DBViewHolder> firebaseUsersAdapter;

    private DatabaseReference databaseReference2;
    FirebaseRecyclerOptions<DBAdapter> options2;
    FirebaseRecyclerAdapter<DBAdapter, DBViewHolder> firebaseUsersAdapter2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.search_fragment, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("Search");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Movies");
//        closeBtn = (FloatingActionButton) rootView.findViewById(R.id.searchCloseBtn);
//        closeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment newCase=new HomeFragment();
//                FragmentTransaction transaction=getFragmentManager().beginTransaction();
//                transaction.replace(R.id.f1Layout,newCase); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
//            }
//        });
        animationView = (LottieAnimationView) rootView.findViewById(R.id.loadin_anim);
//        shimmerFrameLayout = (ShimmerFrameLayout) rootView.findViewById(R.id.shimmer_view_container);
//        shimmerFrameLayout.setVisibility(View.VISIBLE);
        recyclerLayout = (RecyclerView) rootView.findViewById(R.id.recyclerlayout);
        recyclerLayout.setHasFixedSize(true);
        mSearchButton = (ImageView) rootView.findViewById(R.id.mSearchBtn);
        mSearchInputText = (TextInputEditText) rootView.findViewById(R.id.searchText);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = mSearchInputText.getText().toString();
                String str = searchText.toLowerCase();
                tv_firebasesearch(str);
//                movies_firebasesearch(str);
            }
        });
        mSearchInputText.requestFocus();
        if (mSearchInputText.requestFocus()) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
            );
            mSearchInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    boolean handled = false;
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        String searchText = mSearchInputText.getText().toString();
                        String str = searchText.toLowerCase();
//                        movies_firebasesearch(str);
                        tv_firebasesearch(str);
                        InputMethodManager imm = (InputMethodManager) rootView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                        handled = true;
                    }
                    return handled;
                }
            });
        }
        return rootView;
    }

    private void tv_firebasesearch(String str) {
        Query firebaseSearchQuery = databaseReference.orderByChild("name").startAt(str).endAt(str + "\uff8f");
        options = new FirebaseRecyclerOptions.Builder<DBAdapter>().setQuery(firebaseSearchQuery, DBAdapter.class).build();
        firebaseUsersAdapter = new FirebaseRecyclerAdapter<DBAdapter, DBViewHolder>(options) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
//                shimmerFrameLayout.setVisibility(View.VISIBLE);
                animationView.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onBindViewHolder(@NonNull final DBViewHolder holder, final int position, @NonNull final DBAdapter model) {
//                shimmerFrameLayout.setVisibility(View.GONE);
                animationView.setVisibility(View.GONE);
//                Picasso.get().load(model.getImage()).into(holder.show_IMG, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
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
                        Intent in = new Intent(getContext(), ShowHome.class);
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
                return new DBViewHolder(view);
            }
        };

        int mNoOfColumns = Utility.calculateNoOfColumns(getContext());
        GridLayoutManager gridmanager = new GridLayoutManager(getContext(), mNoOfColumns);
        recyclerLayout.setLayoutManager(gridmanager);
        firebaseUsersAdapter.startListening();
        recyclerLayout.setAdapter(firebaseUsersAdapter);
    }
//    private void movies_firebasesearch(String str) {
//        Query firebaseSearchQuery2 = databaseReference2.orderByChild("name").startAt(str).endAt(str + "\uff8f");
//        options2 = new FirebaseRecyclerOptions.Builder<DBAdapter>().setQuery(firebaseSearchQuery2, DBAdapter.class).build();
//        firebaseUsersAdapter2 = new FirebaseRecyclerAdapter<DBAdapter, DBViewHolder>(options2) {
//            @Override
//            public void onDataChanged() {
//                super.onDataChanged();
////                shimmerFrameLayout.setVisibility(View.VISIBLE);
//                animationView.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull final DBViewHolder holder, final int position, @NonNull final DBAdapter model) {
////                shimmerFrameLayout.setVisibility(View.GONE);
//                animationView.setVisibility(View.GONE);
//                Picasso.get().load(model.getImage()).into(holder.show_IMG, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//                holder.name_Text.setText(model.getName());
//                holder.year_Text.setText(model.getYear());
//                holder.url_Text.setText(model.getUrl());
//                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String url = model.getUrl();
//                        String title = model.getName();
//                        String image = model.getImage();
//                        String year = model.getYear();
//                        String banner = model.getImage();
//                        String rate = model.getRating();
//                        String seas = model.getSeason();
//                        String plot = model.getPlot();
//                        String genre = model.getGenre();
//                        String cast = model.getCast();
//                        String yt = model.getYt();
//                        Intent in = new Intent(getContext(), ShowHome.class);
//                        in.putExtra("url", String.valueOf(url));
//                        in.putExtra("name", String.valueOf(title));
//                        in.putExtra("image", String.valueOf(image));
//                        in.putExtra("image", String.valueOf(banner));
//                        in.putExtra("year", String.valueOf(year));
//                        in.putExtra("rating", String.valueOf(rate));
//                        in.putExtra("season", String.valueOf(seas));
//
//                        in.putExtra("plot", String.valueOf(plot));
//                        in.putExtra("genre", String.valueOf(genre));
//                        in.putExtra("cast", String.valueOf(cast));
//                        in.putExtra("yt", String.valueOf(yt));
//                        startActivity(in);
//                    }
//                });
//            }
//
//            @NonNull
//            @Override
//            public DBViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int i) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_tv_item, parent, false);
//                return new DBViewHolder(view);
//            }
//        };
//
//        int mNoOfColumns = Utility.calculateNoOfColumns(getContext());
//        GridLayoutManager gridmanager = new GridLayoutManager(getContext(), mNoOfColumns);
//        recyclerLayout.setLayoutManager(gridmanager);
//        firebaseUsersAdapter2.startListening();
//        recyclerLayout.setAdapter(firebaseUsersAdapter2);
//    }
    public static class Utility {
        public static int calculateNoOfColumns(Context context) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int noOfColumns = (int) (dpWidth / 120);
            return noOfColumns;
        }
    }
}
