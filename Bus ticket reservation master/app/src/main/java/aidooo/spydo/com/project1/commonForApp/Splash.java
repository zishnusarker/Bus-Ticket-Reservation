package aidooo.spydo.com.project1.commonForApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import aidooo.spydo.com.project1.Common.LoginSignUp.LoginSignUp;
import aidooo.spydo.com.project1.R;
public class Splash extends AppCompatActivity {

    private static int SPLASH_SCREEN = 1000;

    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView slogan,textLogo;
    SharedPreferences gettingStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo=findViewById(R.id.logo);
        textLogo=findViewById(R.id.textLogo);
        slogan=findViewById(R.id.slogan);

        logo.setAnimation(topAnim);
        textLogo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                gettingStarted = getSharedPreferences("getting_started",MODE_PRIVATE);
                boolean isFirstTime = gettingStarted.getBoolean("firstTime",true);

                if(isFirstTime){

                    SharedPreferences.Editor editor = gettingStarted.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    Intent intent = new Intent(Splash.this, Getting_started.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(Splash.this, LoginSignUp.class);
                    startActivity(intent);
                    finish();
                }

            }
        },SPLASH_SCREEN);

    }
}