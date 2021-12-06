package com.aakash.databindingtest.swipeViewpager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.aakash.databindingtest.R;

import java.util.ArrayList;
import java.util.List;

public class SwipeTestActivity extends AppCompatActivity {
    ViewPager viewPager;
    FragmentAdapter adapter;
    Button prev, next;
    List<String> modelStrings = new ArrayList<>();
    TextView counting;

    int itemPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_test);
        viewPager = findViewById(R.id.viewpager);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        counting = findViewById(R.id.counting);
        for (int i = 1; i <= 6; i++) {
            modelStrings.add("Hey this is Chapter: " + i);
        }
        adapter = new FragmentAdapter(getSupportFragmentManager(), modelStrings);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        loadCountingOfTheQuestions(itemPosition+1);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPosition = viewPager.getCurrentItem();
                ++itemPosition;

                if (itemPosition < modelStrings.size()) {
                    viewPager.setCurrentItem(itemPosition);
                    loadCountingOfTheQuestions(itemPosition + 1);
                } else {
                    viewPager.setCurrentItem(itemPosition);
                    loadCountingOfTheQuestions(itemPosition);
                }
            }
        });

        prev.setOnClickListener(v -> {
            itemPosition = viewPager.getCurrentItem();
            itemPosition = itemPosition - 1;
            if (itemPosition > 0) {
                viewPager.setCurrentItem(itemPosition);
                loadCountingOfTheQuestions(itemPosition + 1);

            } else if (itemPosition == 0) {
                loadCountingOfTheQuestions(itemPosition + 1);
                viewPager.setCurrentItem(itemPosition);

            } else {
                itemPosition = 1;
                loadCountingOfTheQuestions(itemPosition);
                Toast.makeText(getApplicationContext(), "No Previous chapter found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCountingOfTheQuestions(int itemPosition) {
        counting.setText("" + itemPosition + "/" + modelStrings.size());
    }


}