package com.example.myapp;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomSheetDialog bottomSheetDialog;
    ImageButton menu;
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


        this.cart = findViewById(R.id.cart);
        this.menu = findViewById(R.id.menu);

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
                    menu.setBackgroundColor((Integer)argbEvaluator.evaluate(v, colors[i], colors[i + 1]));
                    /*cart.setBackgroundColor((Integer)argbEvaluator.evaluate(v, colors[i], colors[i + 1]));*/
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                    menu.setBackgroundColor(colors[colors.length - 1]);
                    /*cart.setBackgroundColor(colors[colors.length - 1]);*/
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartActivity = new Intent(getApplicationContext(), MyCart.class);
                startActivity(cartActivity);
            }
        });

        //ouverture du bottom Sheet menu
        bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);

        View shareview = bottomSheetDialogView.findViewById(R.id.share);
        View getLinkview = bottomSheetDialogView.findViewById(R.id.get_link);
        View editNameview = bottomSheetDialogView.findViewById(R.id.edit);
        View deletview = bottomSheetDialogView.findViewById(R.id.delet);

        shareview.setOnClickListener(this);
        getLinkview.setOnClickListener(this);
        editNameview.setOnClickListener(this);
        deletview.setOnClickListener(this);

        ImageButton button = findViewById(R.id.menu);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.menu:
                bottomSheetDialog.show();
                break;

            case R.id.share:
                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.get_link:
                Toast.makeText(MainActivity.this, "Get Link", Toast.LENGTH_SHORT).show();
                break;

            case R.id.edit:
                Toast.makeText(MainActivity.this, "Edit Name", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delet:
                Toast.makeText(MainActivity.this, "Delete collection", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
