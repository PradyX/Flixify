package prady.flixify;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar toolbar;
    private Switch mySwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.AppTheme);
        }
        else setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mySwitch = (Switch) findViewById(R.id.app_bar_switch);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle =  new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.f1Layout, new HomeFragment()).addToBackStack(null).commit();
            nvDrawer.setCheckedItem(R.id.home);
        }
    }

    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        switch (item.getItemId())
        {
            case R.id.report:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/FlixifySupport "));
                startActivity(intent);
//            Intent intent = new Intent(Intent.ACTION_SENDTO);
//            intent.setData(Uri.parse("mailto: pradyumnbhushan@hotmail.com"));
//            startActivity(intent);
            break;

            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out this awesome app at: https://pradyumnbhushan.blogspot.com/2018/11/index-of-android-app.html");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            case R.id.settings:
                Intent intent1= new Intent(this, Settings.class);
                startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectItemDrawer(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_switch:
	            Toast.makeText(this, "Sorry, developer was too lazy to implement it.", Toast.LENGTH_LONG).show();
                break;
            case R.id.home:
                getSupportActionBar().setTitle("Home");
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.down_to_up,R.anim.slide_out_left,R.anim.up_to_down,R.anim.slide_out_to_right)
                        .replace(R.id.f1Layout, new HomeFragment()).addToBackStack(null).commit();
                break;
            case R.id.searchDw:
                getSupportActionBar().setTitle("Search");
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.down_to_up,R.anim.slide_out_left,R.anim.up_to_down,R.anim.slide_out_to_right)
                        .replace(R.id.f1Layout, new TvSearchFragment()).addToBackStack(null).commit();
                break;
            case R.id.sources:
                getSupportActionBar().setTitle("Sources");
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.down_to_up,R.anim.slide_out_left,R.anim.up_to_down,R.anim.slide_out_to_right)
                        .replace(R.id.f1Layout, new SourcesFragment()).addToBackStack(null).commit();
                break;

            case R.id.fav:
                Intent i = new Intent(this, Bookmarks.class);
                startActivity(i);
//                getSupportActionBar().setTitle("Favorites");
//                getSupportFragmentManager().beginTransaction()
//                        .setCustomAnimations(R.anim.down_to_up,R.anim.slide_out_left,R.anim.up_to_down,R.anim.slide_out_to_right)
//                        .replace(R.id.f1Layout, new FavFragment()).addToBackStack(null).commit();
                break;

            case R.id.about:
                getSupportActionBar().setTitle("About App");
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.down_to_up,R.anim.slide_out_left,R.anim.up_to_down,R.anim.slide_out_to_right)
                        .replace(R.id.f1Layout, new AboutFragment()).addToBackStack(null).commit();
                break;

            default:
                getSupportActionBar().setTitle("Home");
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.down_to_up,R.anim.slide_out_left,R.anim.up_to_down,R.anim.slide_out_to_right)
                        .replace(R.id.f1Layout, new HomeFragment()).addToBackStack(null).commit();
        }
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
    }

    private void darkmode() {
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES)
        {
            mySwitch.setChecked(true);
        }
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });
    }
    private void restartApp() {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

  
    @Override
    public void onBackPressed() {
        FragmentManager mgr = getSupportFragmentManager();
        if (mgr.getBackStackEntryCount() == 1) {
            // No backstack to pop, so calling super
           backActionDialog();
        } else {
            mgr.popBackStack();
        }
    }

    public void backActionDialog(){
        new MaterialStyledDialog.Builder(this)
                .setTitle("Alert!")
                .setDescription("Are you sure you want to exit App?")
                .setCancelable(false)
                .setHeaderColor(R.color.red)
                .setStyle(Style.HEADER_WITH_ICON).setIcon(R.drawable.ic_mood_bad)
                .withIconAnimation(true)
                .withDialogAnimation(true)
                .setPositiveText("Yes")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finishAffinity();
                    }})
                .setNegativeText("No")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }}).show();
     }
}
