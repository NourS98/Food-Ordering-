package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.foodorderapp.model.Allmenu;
import com.example.foodorderapp.model.FoodData;
import com.example.foodorderapp.model.Popular;
import com.example.foodorderapp.model.Recommended;
import com.example.foodorderapp.retrofit.ApiInterface;
import com.example.foodorderapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private RecyclerView popularRecycleView;
    private PopularAdapter popularAdapter;

    RecyclerView  recommendedRecyclerView, allMenuRecyclerView;
    RecommendAdapter recommendedAdapter;
    AllMenuAdapter allMenuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface= RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<FoodData>> call = apiInterface.getAllData();
        call.enqueue(new Callback<List<FoodData>>() {
            @Override
            public void onResponse(Call<List<FoodData>> call, Response<List<FoodData>> response) {
                List<FoodData> foodData = response.body();
                getPopularData(foodData.get(0).getPopular());
                getRecommendedData(foodData.get(0).getRecommended());
                getAllMenu(foodData.get(0).getAllmenu());
            }

            @Override
            public void onFailure(Call<List<FoodData>> call, Throwable t) {
                    Toast.makeText(MainActivity.this , "Server not Responsing" , Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void getPopularData(List<Popular> popular){
        popularRecycleView = findViewById(R.id.popular_recycle);
        popularAdapter = new PopularAdapter(this, popular);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        popularRecycleView.setLayoutManager(layoutManager);
        popularRecycleView.setAdapter(popularAdapter);

    }
    private void  getRecommendedData(List<Recommended> recommendedList){

        recommendedRecyclerView = findViewById(R.id.recommended_recycle);
        recommendedAdapter = new RecommendAdapter(this, recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdapter);

    }
    private void  getAllMenu(List<Allmenu> allmenuList){

        allMenuRecyclerView = findViewById(R.id.all_menu_recycle);
        allMenuAdapter = new AllMenuAdapter(this, allmenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        allMenuRecyclerView.setLayoutManager(layoutManager);
        allMenuRecyclerView.setAdapter(allMenuAdapter);
        allMenuAdapter.notifyDataSetChanged();

    }

}