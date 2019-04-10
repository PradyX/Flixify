package prady.flixify;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class SourcesFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private frag1Fragment frag1Fragment;
    private frag2Fragment frag2Fragment;
    private frag3Fragment frag3Fragment;
    public SourcesFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sources_fragment, container, false);
        frag1Fragment = new frag1Fragment();
        frag2Fragment = new frag2Fragment();
        frag3Fragment = new frag3Fragment();
        frameLayout = (FrameLayout) rootView.findViewById(R.id.sourcesContent);
        bottomNavigationView = (BottomNavigationView) rootView.findViewById(R.id.bottomNav);
        setFragment(frag1Fragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.moviesBttom:
//                        bottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
//                        bottomNavigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.white)));
//                        bottomNavigationView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
                        setFragment(frag1Fragment);
                        return true;

                    case R.id.tvBttom:
                        setFragment(frag2Fragment);
                        return true;

                    case R.id.animeBttom:
                        setFragment(frag3Fragment);
                        return true;

                        default:
                            setFragment(frag1Fragment);
                }
                return false;
            }
        });
        return rootView;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.down_to_up,R.anim.slide_out_left,R.anim.up_to_down,R.anim.slide_out_to_right);
        transaction.replace(R.id.sourcesContent,fragment); // give your fragment container id in first parameter
        transaction.commit();
    }
}
