package prady.flixify;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.tomer.fadingtextview.FadingTextView;


public class Splash_Screen extends AppCompatActivity {

    Animation animation;
    ProgressBar progressBar;
    FadingTextView fadingTextView;
    LottieAnimationView lottieAnimationView, lottieAnimationView2;
    private static int SPLASH_TIME_OUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fadingTextView = (FadingTextView) findViewById(R.id.loadingText);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view);
        lottieAnimationView2 = (LottieAnimationView) findViewById(R.id.animation_view2);

        animation = AnimationUtils.loadAnimation(this,R.anim.splah_slide_up);
        fadingTextView.startAnimation(animation);
        lottieAnimationView.startAnimation(animation);
        lottieAnimationView2.startAnimation(animation);
//        textView.startAnimation(animation);

//        final Intent intent = new Intent(this, MainActivity.class);
//        Thread timer = new Thread(){
//            public void run()
//            {
//                try {
//                    sleep(3500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                finally {
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        };
//        timer.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash_Screen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


//        int i = 0;
//
//        new CountDownTimer(10000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                i++;
//                if(i == 1){
//                    imageview.setImageResource(R.drawable.image1);
//                }
//                else if(i == 2){
//                    imageview.setImageResource(R.drawable.image2);
//                }
//                else if(i == 3){
//                    imageview.setImageResource(R.drawable.image3);
//                }
//                //and so on..........................
//            }
//
//            public void onFinish() {
//                //finish your splash screen activity
//                Splash_Screen.this.finish();
//            }
//
//        }.start();

}
