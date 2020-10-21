package com.jmrootkit.sociedadandroideretrofitgson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Interface.ApiMeli;
import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Interface.JsonPlaceHolderApi;
import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Model.Item;
import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Model.Posts;

import java.util.List;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;
    private TextView mJsonTxtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);

        getPosts();

    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiMeli apiMeli = retrofit.create(ApiMeli.class);

      //  JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Item>> itemcall = apiMeli.getItem();

     //   Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();


        itemcall.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo de error: " + response.code());
                    return;
                }

                List<Item> itemsList = response.body();
                for(Item item : itemsList ){
                    String content = "";
                    content += "Id: "+ item.getId() + "\n";
                    content += "title: "+ item.getTitle() + "\n";
                    content += "price: "+ item.getPrice() + "\n \n";

                    mJsonTxtView.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
                mJsonTxtView.setText(t.getMessage());

            }
        });


        /*

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {

                Log.e(TAG, "onResponse: paso por aqui 1" );
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: " +response.code());
                    Log.e(TAG, "onResponse: q no es suucesfulll" );
                    return;
                }
                List<Posts> postsList = response.body();
                for(Posts post: postsList){
                    String content = "";
                    content += "userId: "+ post.getUserId() + "\n";
                    content += "id: "+ post.getId() + "\n";
                    content += "title: "+ post.getTitle() + "\n";
                    content += "body: "+ post.getBody() + "\n\n";

                    mJsonTxtView.append(content);

                    Toast.makeText(MainActivity.this, "hello " + content, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());

            }
        });

        */

    }


}