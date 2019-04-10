package prady.flixify;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavFragment extends Fragment {
    public FavFragment() {
    }
    private CoordinatorLayout coordinatorLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fav_layout, container, false);
        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.favCoordinator);
        Snackbar snackbar2 = Snackbar.make(coordinatorLayout, "Your Favourites Appear Here !!", Snackbar.LENGTH_LONG);
        snackbar2.show();
        return rootView;
    }
}
