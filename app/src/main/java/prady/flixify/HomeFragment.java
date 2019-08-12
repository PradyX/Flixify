package prady.flixify;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;


public class HomeFragment extends Fragment {
    public HomeFragment() {
    }

    private ViewFlipper flipper;
    private FirebaseDatabase mRef;
    private MaterialButton tvBtn, movieBtn, showActiv;
    private FloatingActionButton mSearchBtn;
    private ShimmerFrameLayout progressBar, progressBar2;
    private SliderLayout sliderLayout;
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerLayout, recyclerLayout2;
    private DatabaseReference databaseReference;
    FirebaseRecyclerOptions<DBAdapter> options;
    FirebaseRecyclerAdapter<DBAdapter, DBViewHolder> firebaseUsersAdapter;

    private DatabaseReference databaseReference2;
    FirebaseRecyclerOptions<DBAdapter> options2;
    FirebaseRecyclerAdapter<DBAdapter, DBViewHolder> firebaseUsersAdapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("TvSeries");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Movies");

        sliderLayout = rootView.findViewById(R.id.imageSlider);
//        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :
        setSliderViews();

        flipper = (ViewFlipper) rootView.findViewById(R.id.vFlipper);
        int images[] = {R.drawable.test_img, R.drawable.test_img, R.drawable.test_img};
        for (int image : images) {
            flipper(image);
        }
        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.homeFragmentCoordinator);

        progressBar = (ShimmerFrameLayout) rootView.findViewById(R.id.shimmer_view_container);
        progressBar2 = (ShimmerFrameLayout) rootView.findViewById(R.id.shimmer_view_container2);
        recyclerLayout = (RecyclerView) rootView.findViewById(R.id.recyclerlayout);
        recyclerLayout.setHasFixedSize(true);
        recyclerLayout2 = (RecyclerView) rootView.findViewById(R.id.recyclerlayout2);
        recyclerLayout2.setHasFixedSize(true);
        mSearchBtn = (FloatingActionButton) rootView.findViewById(R.id.searchBtn);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newCase = new SearchFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.down_to_up, R.anim.slide_out_left, R.anim.up_to_down, R.anim.slide_out_to_right);
                transaction.replace(R.id.f1Layout, newCase); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        tvBtn = (MaterialButton) rootView.findViewById(R.id.tvMoreBtn);
        tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newCase = new TvFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.down_to_up, R.anim.slide_out_left, R.anim.up_to_down, R.anim.slide_out_to_right);
                transaction.replace(R.id.f1Layout, newCase); // give your fragment container id in first parameter
                transaction.addToBackStack("t");  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        movieBtn = (MaterialButton) rootView.findViewById(R.id.moreBtn2);
        movieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment newCase = new MoviesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.down_to_up, R.anim.slide_out_left, R.anim.up_to_down, R.anim.slide_out_to_right);
                transaction.replace(R.id.f1Layout, newCase); // give your fragment container id in first parameter
                transaction.addToBackStack("m");  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myRefresh();
            }
        }, 2000);

        return rootView;
    }

    private void setSliderViews() {
        for (int i = 0; i <= 4; i++) {
            SliderView sliderView = new SliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fhouse_of_cards-banner.jpg?alt=media&token=e9d9402f-23a6-4275-b87c-8f1624f8f275");
                    sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent in = new Intent(getContext(), ShowHome.class);
                            in.putExtra("url", String.valueOf("http://dl3.melimedia.net/mersad/serial/House%20of%20Cards/"));
                            in.putExtra("name", String.valueOf("house of cards"));
                            in.putExtra("image", String.valueOf("https://firebasestorage.googleapis.com/v0/b/index-of-21b21.appspot.com/o/house%20of%20cards.jpg?alt=media&token=778a0c07-0792-4f22-a869-b2ceaf40440a"));
                            in.putExtra("banner", String.valueOf("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fhouse_of_cards-banner.jpg?alt=media&token=e9d9402f-23a6-4275-b87c-8f1624f8f275"));
                            in.putExtra("year", String.valueOf("(2013 – 2018)"));
                            in.putExtra("rating", String.valueOf("8.8/10"));
                            in.putExtra("season", String.valueOf("1 2 3 4 5 6"));
                            in.putExtra("plot", String.valueOf("Majority House Whip Francis Underwood takes you on a long journey as he exacts his vengeance on those he feels wronged him - that is, his own cabinet members including the President of the United States himself. Dashing, cunning, methodical and vicious, Frank Underwood along with his equally manipulative yet ambiguous wife, Claire take Washington by storm through climbing the hierarchical ladder to power in this Americanized recreation of the BBC series of the same name."));
                            in.putExtra("genre", String.valueOf("Drama"));
                            in.putExtra("cast", String.valueOf("Robin Wright, Michael Kelly, Kevin Spacey, Justin Doescher"));
                            in.putExtra("yt", String.valueOf("fdfdfd"));
                            startActivity(in);
                        }
                    });

                    break;
                case 1:
//                    sliderView.setDescription("The Haunting of Hill House");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fhousenetflixthumb-1538491479365_270h.jpg?alt=media&token=288d4a45-6a62-402c-adec-79127092827a");
                    sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent in = new Intent(getContext(), ShowHome.class);
                            in.putExtra("url", String.valueOf("http://dl3.melimedia.net/mersad/serial/The%20Haunting%20of%20Hill%20House/"));
                            in.putExtra("name", String.valueOf(" The haunting of hill house"));
                            in.putExtra("image", String.valueOf("https://firebasestorage.googleapis.com/v0/b/index-of-21b21.appspot.com/o/haunting%20of%20hill%20house.jpg?alt=media&token=ec7fac6d-4581-4d8e-85af-0b55b59e3980"));
                            in.putExtra("banner", String.valueOf("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fhousenetflixthumb-1538491479365_270h.jpg?alt=media&token=288d4a45-6a62-402c-adec-79127092827a"));
                            in.putExtra("year", String.valueOf("(2018 -)"));
                            in.putExtra("rating", String.valueOf("8.8/10"));
                            in.putExtra("season", String.valueOf("1 "));
                            in.putExtra("plot", String.valueOf("Flashing between past and present, a fractured family confronts haunting memories of their old home and the terrifying events that drove them from it."));
                            in.putExtra("genre", String.valueOf("Drama, Horror, Mystery, Thriller"));
                            in.putExtra("cast", String.valueOf("Michiel Huisman, Carla Gugino, Henry Thomas, Elizabeth Reaser"));
                            in.putExtra("yt", String.valueOf("hhh"));
                            startActivity(in);
                        }
                    });
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fnarcos-mexico.jpg?alt=media&token=5873ecc3-27ba-4dd3-89d5-13022f7c6a77");
                    sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent in = new Intent(getContext(), ShowHome.class);
                            in.putExtra("url", String.valueOf("http://dl.tehmovies.pro/94/series/nacos.mexico/"));
                            in.putExtra("name", String.valueOf("narcos mexico"));
                            in.putExtra("image", String.valueOf("https://firebasestorage.googleapis.com/v0/b/index-of-21b21.appspot.com/o/narcos%20mexico.jpg?alt=media&token=30104836-dc54-4e89-991f-75f8594175ea"));
                            in.putExtra("banner", String.valueOf("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fnarcos-mexico.jpg?alt=media&token=5873ecc3-27ba-4dd3-89d5-13022f7c6a77"));
                            in.putExtra("year", String.valueOf("(2018 -)"));
                            in.putExtra("rating", String.valueOf("8.6/10"));
                            in.putExtra("season", String.valueOf("1 2"));
                            in.putExtra("plot", String.valueOf("The rise of the Guadalajara Cartel as an American DEA agent learns the danger of targeting narcos in Mexico."));
                            in.putExtra("genre", String.valueOf("Crime, Drama"));
                            in.putExtra("cast", String.valueOf("Diego Luna, Scoot McNairy, Michael Peña, Alyssa Diaz"));
                            in.putExtra("yt", String.valueOf("ddd"));
                            startActivity(in);
                        }
                    });
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fdaredevil-splash.jpg?alt=media&token=016fb51e-a4fe-45fa-b19e-44c7f7a3162f");
                    sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent in = new Intent(getContext(), ShowHome.class);
                            in.putExtra("url", String.valueOf("http://dl2.funsaber.net/serial/Daredevil/"));
                            in.putExtra("name", String.valueOf("daredevil"));
                            in.putExtra("image", String.valueOf("https://firebasestorage.googleapis.com/v0/b/index-of-21b21.appspot.com/o/Daredevil%20(2015).jpg?alt=media&token=d3c13771-ecf5-48a9-bd1c-f1297464c693"));
                            in.putExtra("banner", String.valueOf("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fdaredevil-splash.jpg?alt=media&token=016fb51e-a4fe-45fa-b19e-44c7f7a3162f"));
                            in.putExtra("year", String.valueOf("(2015 – 2018)"));
                            in.putExtra("rating", String.valueOf("8.7/10"));
                            in.putExtra("season", String.valueOf("1 2 3"));
                            in.putExtra("plot", String.valueOf("As a child Matt Murdock was blinded by a chemical spill in a freak accident. Instead of limiting him it gave him superhuman senses that enabled him to see the world in a unique and powerful way. Now he uses these powers to deliver justice, not only as a lawyer in his own law firm, but also as vigilante at night, stalking the streets of Hell's Kitchen as Daredevil, the man without fear."));
                            in.putExtra("genre", String.valueOf("Action, Crime, Drama, Fantasy, Sci-Fi, Thriller"));
                            in.putExtra("cast", String.valueOf("Charlie Cox, Deborah Ann Woll, Elden Henson, Vincent D'Onofrio"));
                            in.putExtra("yt", String.valueOf(""));
                            startActivity(in);
                        }
                    });
                    break;
                case 4:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fnew-poster-for-the-flash-season-2-its-go-time.png?alt=media&token=e9e96f1a-97f9-48e2-b6fe-250058989978");
                    sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(SliderView sliderView) {
                            Intent in = new Intent(getContext(), ShowHome.class);
                            in.putExtra("url", String.valueOf("http://dl3.melimedia.net/mersad/serial/the%20flash/"));
                            in.putExtra("name", String.valueOf("The flash"));
                            in.putExtra("image", String.valueOf("https://firebasestorage.googleapis.com/v0/b/index-of-21b21.appspot.com/o/flash.jpg?alt=media&token=b2011baa-c89c-4994-9179-67c267411492"));
                            in.putExtra("banner", String.valueOf("https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fnew-poster-for-the-flash-season-2-its-go-time.png?alt=media&token=e9e96f1a-97f9-48e2-b6fe-250058989978"));
                            in.putExtra("year", String.valueOf("(2014 -)"));
                            in.putExtra("rating", String.valueOf("7.9/10"));
                            in.putExtra("season", String.valueOf("1 2 3 4 5"));
                            in.putExtra("plot", String.valueOf("Barry Allen is a Central City police forensic scientist with a reasonably happy life, despite the childhood trauma of a mysterious red and yellow lightning killing his mother and framing his father. All that changes when a massive particle accelerator accident leads to Barry being struck by lightning in his lab. Coming out of coma nine months later, Barry and his new friends at S.T.A.R labs find that he now has the ability to move at superhuman speed. Furthermore, Barry learns that he is but one of many affected by that event, most of whom are using their powers for evil. Determined to make a difference, Barry dedicates his life to fighting such threats, as The Flash. While he gains allies he never expected, there are also secret forces determined to aid and manipulate him for their own agenda."));
                            in.putExtra("genre", String.valueOf("Action, Adventure, Drama, Sci-Fi"));
                            in.putExtra("cast", String.valueOf("Grant Gustin, Candice Patton, Danielle Panabaker, Carlos Valdes"));
                            in.putExtra("yt", String.valueOf("https://www.youtube.com/watch?v=Yj0l7iGKh8g"));
                            startActivity(in);
                        }
                    });
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            //at last add this view in your layout :
            sliderLayout.setIndicatorAnimation(SliderLayout.Animations.THIN_WORM);
            sliderLayout.addSliderView(sliderView);
        }
    }

    private void firebaseLoad() {
        options = new FirebaseRecyclerOptions.Builder<DBAdapter>().setQuery(databaseReference, DBAdapter.class).build();
        firebaseUsersAdapter = new FirebaseRecyclerAdapter<DBAdapter, DBViewHolder>(options) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onBindViewHolder(@NonNull final DBViewHolder holder, final int position, @NonNull final DBAdapter model) {
                progressBar.setVisibility(View.VISIBLE);
//                Picasso.get().load(model.getImage()).into(holder.show_IMG, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        if (progressBar != null) {
//                            progressBar.setVisibility(View.GONE);
//                        }
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
                holder.season_text.setText(model.getSeason());
                holder.plot_Text.setText(model.getPlot());
                holder.genre_Text.setText(model.getGenre());
                holder.cast_Text.setText(model.getCast());
                holder.yt_Text.setText(model.getYt());
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
                return new DBViewHolder(view);
            }
        };

        int mNoOfColumns = Utility.calculateNoOfColumns(getContext());
        LinearLayoutManager gridmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
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

    private void firebaseMoviesLoad() {
        options2 = new FirebaseRecyclerOptions.Builder<DBAdapter>().setQuery(databaseReference2, DBAdapter.class).build();
        firebaseUsersAdapter2 = new FirebaseRecyclerAdapter<DBAdapter, DBViewHolder>(options2) {
            @Override
            public void onDataChanged() {
                super.onDataChanged();
                progressBar2.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onBindViewHolder(@NonNull final DBViewHolder holder, final int position, @NonNull final DBAdapter model) {
//                Picasso.get().load(model.getImage()).into(holder.show_IMG, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        if (progressBar2 != null) {
//                            progressBar2.setVisibility(View.GONE);
//                        }
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
                        if (progressBar2 != null) {
                            progressBar2.setVisibility(View.GONE);
                        }
                        return false;
                    }
                }).into(holder.show_IMG);
                holder.name_Text.setText(model.getName());
                holder.year_Text.setText(model.getYear());
                holder.url_Text.setText(model.getUrl());
                holder.rating_Text.setText(model.getRating());
                holder.season_text.setText(model.getSeason());
                holder.plot_Text.setText(model.getPlot());
                holder.genre_Text.setText(model.getGenre());
                holder.cast_Text.setText(model.getCast());
                holder.yt_Text.setText(model.getYt());
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
//
                return new DBViewHolder(view);
            }
        };

        int mNoOfColumns = Utility.calculateNoOfColumns(getContext());
        LinearLayoutManager gridmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerLayout2.setLayoutManager(gridmanager);
        firebaseUsersAdapter2.startListening();
        recyclerLayout2.setAdapter(firebaseUsersAdapter2);
    }

    public void myRefresh() {
        try {
            firebaseLoad();
            firebaseMoviesLoad();
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void flipper(int image) {
        ImageView view = new ImageView(getContext());
        view.setBackgroundResource(image);
        flipper.addView(view);
        flipper.setFlipInterval(1000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(getContext(), R.anim.slide_in_from_left);
        flipper.setOutAnimation(getContext(), R.anim.slide_out_to_right);
    }
}
