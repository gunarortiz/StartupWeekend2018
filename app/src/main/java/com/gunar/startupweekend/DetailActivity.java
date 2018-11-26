package com.gunar.startupweekend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.gunar.startupweekend.adapter.TabAdapter;
import com.gunar.startupweekend.fragments.Comprar;
import com.gunar.startupweekend.fragments.Preparar;
import com.gunar.startupweekend.fragments.Ver;

public class DetailActivity extends AppCompatActivity {

    TextView nombreT;

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private String nombre="", ingrediente="", link="", preparacion="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                nombre = extras.getString("nombre");
                ingrediente = extras.getString("ingrediente");
                link = extras.getString("link");
                preparacion = extras.getString("preparacion");
            }
        }

        nombreT = (TextView)findViewById(R.id.nombre);
        nombreT.setText(nombre);

        ImageView i = (ImageView) findViewById(R.id.link);
        byte[] decodedString = Base64.decode(link, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        i.setImageBitmap(decodedByte);


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Preparar(), "Preparar", nombre, ingrediente, preparacion);
        adapter.addFragment(new Ver(), "Informacion", nombre, ingrediente, preparacion);
        adapter.addFragment(new Comprar(), "Comprar",  nombre, ingrediente, preparacion);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1, false);

    }
}
