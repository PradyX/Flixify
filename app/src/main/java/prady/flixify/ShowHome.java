package prady.flixify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static prady.flixify.BrowserActivity.PREFERENCES;
import static prady.flixify.BrowserActivity.WEB_LINKS;
import static prady.flixify.BrowserActivity.WEB_TITLE;

public class ShowHome extends AppCompatActivity {

	private ImageView showImage, showImageBanner;
	private TextView showYear, showTitle, showRating, showSeason, showPlot, showGenre, showCast;
	private TextView genre, cast, season;
	private CoordinatorLayout coordinatorLayout;
	private LottieAnimationView lottieAnimationView;
	private Toolbar toolbar;
	private MaterialButton downloadBtn, bookmarkBtn;
	private Animation fadeInAnim, slideUp;

	String showPlotDefault = "N/A";
	String showGenreDefault = "N/A";
	String showCastDefault = "N/A";
	String showYtDefault = "https://www.youtube.com/watch?v=Yj0l7iGKh8g";

	String showRatingDefault = "0.0";
	String showSeasonDefault = "0   0   0   0   0   0   0   0";
	String showBannerDefault = "https://firebasestorage.googleapis.com/v0/b/nemesis-tv.appspot.com/o/Big%20BG%2Fhousenetflixthumb-1538491479365_270h.jpg?alt=media&token=288d4a45-6a62-402c-adec-79127092827a";
	String showUrlDefault = "http://www.google.com";
	String showTextDefault = "Title";
	String showYearDefault = "year";
	String showImageDefault = "https://firebasestorage.googleapis.com/v0/b/index-of-21b21.appspot.com/o/haunting%20of%20hill%20house.jpg?alt=media&token=ec7fac6d-4581-4d8e-85af-0b55b59e3980";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_home);
		toolbar = (Toolbar) findViewById(R.id.show_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(null);
		fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_left);
		lottieAnimationView = (LottieAnimationView) findViewById(R.id.youtubeBtn);
		if (getIntent().getExtras() != null) {
			showBannerDefault = getIntent().getStringExtra("banner");
			showTextDefault = getIntent().getStringExtra("name");
			showUrlDefault = getIntent().getStringExtra("url");
			showImageDefault = getIntent().getStringExtra("image");
			showYearDefault = getIntent().getStringExtra("year");
			showRatingDefault = getIntent().getStringExtra("rating");
			showSeasonDefault = getIntent().getStringExtra("season");
			showPlotDefault = getIntent().getStringExtra("plot");
			showGenreDefault = getIntent().getStringExtra("genre");
			showCastDefault = getIntent().getStringExtra("cast");
			showYtDefault = getIntent().getStringExtra("yt");
		}

		showImageBanner = (ImageView) findViewById(R.id.show_image_banner);
		if (showBannerDefault == null) {
			Glide.with(this).load(showImageDefault).into(showImageBanner);
		} else {
			Glide.with(this).load(showBannerDefault).into(showImageBanner);
		}

		showImage = (ImageView) findViewById(R.id.show_image);
		Glide.with(this).load(showImageDefault).into(showImage);
		showImage.startAnimation(fadeInAnim);

		showTitle = (TextView) findViewById(R.id.show_title);
		showTitle.setText(showTextDefault);
		showTitle.startAnimation(fadeInAnim);

		showYear = (TextView) findViewById(R.id.show_year);
		showYear.setText(showYearDefault);
		showYear.startAnimation(fadeInAnim);

		showRating = (TextView) findViewById(R.id.show_rating);
		showRating.setText(showRatingDefault);
		showRating.startAnimation(fadeInAnim);

		showSeason = (TextView) findViewById(R.id.seasonDetails);
		showSeason.setText(showSeasonDefault);
		showSeason.startAnimation(slideUp);

		showPlot = (TextView) findViewById(R.id.plotDetails);
		showPlot.setText(showPlotDefault);
		showPlot.startAnimation(slideUp);

		showGenre = (TextView) findViewById(R.id.genreDetails);
		showGenre.setText(showGenreDefault);
		showGenre.startAnimation(slideUp);

		showCast = (TextView) findViewById(R.id.castDetails);
		showCast.setText(showCastDefault);
		showCast.startAnimation(slideUp);

		genre = (TextView) findViewById(R.id.seasonHeading2);
		genre.startAnimation(slideUp);
		cast = (TextView) findViewById(R.id.seasonHeading3);
		cast.startAnimation(slideUp);
		season = (TextView) findViewById(R.id.seasonHeading);
		season.startAnimation(slideUp);

		coordinatorLayout = (CoordinatorLayout) findViewById(R.id.show_coordinator);
		downloadBtn = (MaterialButton) findViewById(R.id.show_downloadBtn);
		downloadBtn.startAnimation(fadeInAnim);
		downloadBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(ShowHome.this, BrowserActivity.class);
				intent.putExtra("url", String.valueOf(showUrlDefault));
				startActivity(intent);
				Snackbar snackbar2 = Snackbar.make(coordinatorLayout, "Opening Browser...", Snackbar.LENGTH_LONG);
				snackbar2.show();
			}
		});
		bookmarkBtn = (MaterialButton) findViewById(R.id.shown_bookmarkBtn);
		bookmarkBtn.startAnimation(fadeInAnim);
		bookmarkCheck();
		bookmarkBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				bookmark();
			}
		});

		lottieAnimationView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Opening Youtube...", Snackbar.LENGTH_LONG);
				snackbar1.show();
				String a = showYtDefault;
				if(a.equals(""))
				{
					Snackbar snackbar2 = Snackbar.make(coordinatorLayout, "Sorry, No Youtube Link Found...", Snackbar.LENGTH_LONG);
					snackbar2.show();
				}
				else {
					try {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(a)));
					}
					catch (Exception e)
					{
						Snackbar snackbar2 = Snackbar.make(coordinatorLayout, "Sorry, No Youtube Link Found...", Snackbar.LENGTH_LONG);
						snackbar2.show();
					}
				}
			}
		});
	}

	private void bookmarkCheck() {
		SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
		String links = sharedPreferences.getString(WEB_LINKS, null);

		if (links != null) {

			Gson gson = new Gson();
			ArrayList<String> linkList = gson.fromJson(links, new TypeToken<ArrayList<String>>() {
			}.getType());

			if (linkList.contains(showUrlDefault)) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					bookmarkBtn.setIcon(getDrawable(R.drawable.ic_favorite_f));
				}
			} else {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					bookmarkBtn.setIcon(getDrawable(R.drawable.ic_favorite));
				}
			}
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				bookmarkBtn.setIcon(getDrawable(R.drawable.ic_favorite));
			}
		}
	}

	private void bookmark() {
		SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
		String links = sharedPreferences.getString(WEB_LINKS, null);
		if (links != null) {
			Gson gson = new Gson();
			ArrayList<String> linkList = gson.fromJson(links, new TypeToken<ArrayList<String>>() {
			}.getType());

			if (linkList.contains(showUrlDefault)) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					bookmarkBtn.setIcon(getDrawable(R.drawable.ic_favorite));
				}
			} else {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
					bookmarkBtn.setIcon(getDrawable(R.drawable.ic_favorite_f));
				}
			}
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				bookmarkBtn.setIcon(getDrawable(R.drawable.ic_favorite_f));
			}
		}
		String message;
		String jsonLink = sharedPreferences.getString(WEB_LINKS, null);
		String jsonTitle = sharedPreferences.getString(WEB_TITLE, null);
		if (jsonLink != null && jsonTitle != null) {
			Gson gson = new Gson();
			ArrayList<String> linkList = gson.fromJson(jsonLink, new TypeToken<ArrayList<String>>() {
			}.getType());
			ArrayList<String> titleList = gson.fromJson(jsonTitle, new TypeToken<ArrayList<String>>() {
			}.getType());
			if (linkList.contains(showUrlDefault)) {
				linkList.remove(showUrlDefault);
				titleList.remove(showTextDefault.toUpperCase().trim());
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString(WEB_LINKS, new Gson().toJson(linkList));
				editor.putString(WEB_TITLE, new Gson().toJson(titleList));
				editor.apply();
				message = "Bookmark Removed";
			} else {
				linkList.add(showUrlDefault);
				titleList.add(showTextDefault.toUpperCase().trim());
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString(WEB_LINKS, new Gson().toJson(linkList));
				editor.putString(WEB_TITLE, new Gson().toJson(titleList));
				editor.apply();
				message = "Bookmarked";
			}
		} else {
			ArrayList<String> linkList = new ArrayList<>();
			ArrayList<String> titleList = new ArrayList<>();
			linkList.add(showUrlDefault);
			titleList.add(showTextDefault.toUpperCase());
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString(WEB_LINKS, new Gson().toJson(linkList));
			editor.putString(WEB_TITLE, new Gson().toJson(titleList));
			editor.apply();
			message = "Bookmarked";
		}
		Snackbar snackbar3 = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
		snackbar3.show();
		invalidateOptionsMenu();
	}
}
//if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow(); // in Activity's onCreate() for instance
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }