package aidooo.spydo.com.project1.commonForApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import aidooo.spydo.com.project1.Common.LoginSignUp.LoginSignUp;
import aidooo.spydo.com.project1.HelperClasses.SliderAdapter;
import aidooo.spydo.com.project1.R;

public class Getting_started extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dots;
    SliderAdapter sliderAdapter;
    TextView[] dotts;
    Button letsGo;
    Animation animation;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_getting_started);

        viewPager = findViewById(R.id.slider);
        dots = findViewById(R.id.dots);
        letsGo = findViewById(R.id.letsGo_btn);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDotts(0);
        viewPager.addOnPageChangeListener(changeListener);

    }

    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), LoginSignUp.class));
        finish();
    }
    public void DashBoardPage(View view){
        startActivity(new Intent(getApplicationContext(), LoginSignUp.class));
        finish();
    }

    public void next(View view){
        viewPager.setCurrentItem(currentPos+1);

    }

    private void addDotts(int position){
         dotts = new TextView[2];
         dots.removeAllViews();

         for(int i = 0; i<dotts.length;i++){

             dotts[i] = new TextView(this);
             dotts[i].setText(Html.fromHtml("&#8226"));
             dotts[i].setTextSize(35);

             dots.addView(dotts[i]);
         }
        if (dotts.length > 0) {
            dotts[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotts(position);
            currentPos=position;

            if(position == 0){
                letsGo.setVisibility(View.INVISIBLE);
            }
            else {
                animation = AnimationUtils.loadAnimation(Getting_started.this,R.anim.side_animation);
                letsGo.setAnimation(animation);
                letsGo.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}