package com.example.manager.miscontactos;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.manager.miscontactos.adapter.PageAdapter;
import com.example.manager.miscontactos.fragment.RecyclerViewFragment;
import com.example.manager.miscontactos.fragment.RecyclerViewFragmentTop;

import java.util.ArrayList;

public class MascotasOrdenadas extends AppCompatActivity
{
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //ImageView imgStar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenado);

        //Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar);
        //setSupportActionBar(miActionBar);

        //RecyclerView

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        /*toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/

        setupViewPager();

        if(toolbar != null)
        {
            setSupportActionBar(toolbar);
        }
    }

    private ArrayList<Fragment> agregarFragment()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragmentTop());
        //fragments.add(new PerfilFragment());

        return fragments;
    }

    private void setupViewPager()
    {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.star);
        //tabLayout.getTabAt(1).setIcon(R.drawable.circled_user_male_48);
    }


}
