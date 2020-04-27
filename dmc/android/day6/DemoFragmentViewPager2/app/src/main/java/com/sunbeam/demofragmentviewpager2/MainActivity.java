package com.sunbeam.demofragmentviewpager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager vpSlider = findViewById(R.id.vp_slider);

        final SliderAdapter adapter = new SliderAdapter(getSupportFragmentManager(),
                                    FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        vpSlider.setAdapter(adapter);


        Button btnPrev = findViewById(R.id.btn_prev);
        Button btnNext = findViewById(R.id.btn_next);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currentIndex = vpSlider.getCurrentItem();

                int numberOfItems = adapter.getCount();

                if(view.getId() == R.id.btn_prev) {
                    if(currentIndex == 0) {
                        vpSlider.setCurrentItem(numberOfItems - 1);
                    } else {
                        vpSlider.setCurrentItem(currentIndex - 1);
                    }
                } else {
                    if(currentIndex == numberOfItems - 1) {
                        vpSlider.setCurrentItem(0);
                    } else {
                        vpSlider.setCurrentItem(currentIndex + 1);
                    }
                }
            }
        };

        btnPrev.setOnClickListener(listener);
        btnNext.setOnClickListener(listener);

    }

    class SliderAdapter extends FragmentPagerAdapter {

        public SliderAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            FragmentCounter fragment = FragmentCounter.newInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
