package com.example.myapp;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView counter;
    ImageButton cart;
    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.parasol, "Parasol", "Beate licentia non Simonides ubi reputantium ac hic patriam ad ac enim ubi ante perfecta."));
        models.add(new Model(R.drawable.transate, "Transate", "Beate licentia non Simonides ubi reputantium ac hic patriam ad ac enim ubi ante perfecta."));
        models.add(new Model(R.drawable.parasol, "Parasol", "Beate licentia non Simonides ubi reputantium ac hic patriam ad ac enim ubi ante perfecta."));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temps = {
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
        };

        colors = colors_temps;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int i, float v, int i1) {

                if(i < (adapter.getCount() - 1) && i<(colors.length - 1)) {
                    viewPager.setBackgroundColor((Integer)argbEvaluator.evaluate(v, colors[i], colors[i + 1]));
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        this.cart = findViewById(R.id.cart);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartActivity = new Intent(getApplicationContext(), MyCart.class);
                startActivity(cartActivity);
            }
        });

    }

}
