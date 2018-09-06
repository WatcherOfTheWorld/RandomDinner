package xyz.codem.randomdinner;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainViewPager extends AppCompatActivity {
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        setContentView(R.layout.activity_viewpager);

        viewPager = findViewById(R.id.view_pager);

    }
}
