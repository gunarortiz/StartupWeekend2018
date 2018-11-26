package com.gunar.startupweekend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gunar.startupweekend.adapter.RecyclerAdapter;
import com.gunar.startupweekend.model.HomeResponse;
import com.gunar.startupweekend.remote.ApiService;
import com.gunar.startupweekend.remote.ApiUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiService mAPIService;
    private RecyclerView soliRecycler;
    private RecyclerAdapter soliAdapter;
    private List<HomeResponse> soliList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiUtils.changeApiBaseUrl("");
        mAPIService = ApiUtils.getApiServices();

        soliRecycler = (RecyclerView) findViewById(R.id.recycler);
        soliRecycler.setLayoutFrozen(true);

        soliAdapter = new RecyclerAdapter(soliList);

        soliAdapter.setOnItemClickListener(new RecyclerAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("nombre", soliList.get(position).getNombre());
                intent.putExtra("ingrediente", soliList.get(position).getIngrediente());
                intent.putExtra("link", soliList.get(position).getLink());
                intent.putExtra("preparacion", soliList.get(position).getPreparacion());

                startActivity(intent);
            }

        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        soliRecycler.setLayoutManager(mLayoutManager);

        soliRecycler.setAdapter(soliAdapter);


//        final ArrayList<HomeResponse> datas = new ArrayList<HomeResponse>();



        Call<ArrayList<HomeResponse>> respuesta = mAPIService.llamarMarco("Charquican");

        respuesta.enqueue(new Callback<ArrayList<HomeResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeResponse>> call, Response<ArrayList<HomeResponse>> response) {
                if(response.isSuccessful()){
                    try {

                        soliList.addAll(response.body()) ;
//                        Log.v("resultato", "LOGS" + datas.size());
//
//                        for (int i = 0; i < datas.size(); i++) {
//                            HomeResponse x = new HomeResponse(datas.get(i).getNombre(), datas.get(i).getLink());
//                            soliList.add(x);
//                        }

                        soliAdapter.notifyDataSetChanged();


                    } catch (Exception e) {
                        Log.d("onResponse", "There is an error");
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<ArrayList<HomeResponse>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
