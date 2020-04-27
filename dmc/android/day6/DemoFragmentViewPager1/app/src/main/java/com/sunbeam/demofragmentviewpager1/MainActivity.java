package com.sunbeam.demofragmentviewpager1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(R.color.color1);
        colors.add(R.color.color2);
        colors.add(R.color.color3);
        colors.add(R.color.color4);
        colors.add(R.color.color5);

        ViewPager pager = findViewById(R.id.vp_slider);

        SliderAdapter adapter = new SliderAdapter(getSupportFragmentManager(),
                                                    FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                                                    colors);

        pager.setAdapter(adapter);

    }

    class SliderAdapter extends FragmentPagerAdapter {

        private ArrayList<Integer> mColors;

        public SliderAdapter(@NonNull FragmentManager fm, int behavior, ArrayList<Integer> colors) {
            super(fm, behavior);
            mColors = colors;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            FragmentColors colorFrag = FragmentColors.newInstance(mColors.get(position));
            return colorFrag;
        }

        @Override
        public int getCount() {
            return mColors.size();
        }
    }
}
