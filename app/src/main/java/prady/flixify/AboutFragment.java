package prady.flixify;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class AboutFragment extends Fragment {
    public AboutFragment() {
    }
    private LottieAnimationView animationView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_app, container, false);
        animationView = (LottieAnimationView) rootView.findViewById(R.id.animation_view);
        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationView.playAnimation();
            }
        });
        return rootView;
    }
	@Override
	public void onStart() {
		super.onStart();
		Button bt = (Button) getActivity().findViewById(R.id.bt);
		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_SENDTO);
				intent.setData(Uri.parse("mailto: pradyumnbhushan@hotmail.com")); // only email apps should handle this
				startActivity(intent);
			}
		});
	}
}
