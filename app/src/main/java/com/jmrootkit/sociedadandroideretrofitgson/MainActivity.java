package com.jmrootkit.sociedadandroideretrofitgson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Interface.ARMOapi;
import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Interface.ApiMeli;
import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Interface.JsonPlaceHolderApi;
import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Model.Item;
import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Model.Posts;
import com.jmrootkit.sociedadandroideretrofitgson.retrofit.Model.Puestos;

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
                .baseUrl("http://200.73.146.250:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ARMOapi armOapi = retrofit.create(ARMOapi.class);

      //  ApiMeli apiMeli = retrofit.create(ApiMeli.class);

      //  JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Puestos>> call = armOapi.getPuestos();


     //   Call<List<Item>> itemcall = apiMeli.getItem();

     //   Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();


        call.enqueue(new Callback<List<Puestos>>() {
            @Override
            public void onResponse(Call<List<Puestos>> call, Response<List<Puestos>> response) {

                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo de error: " + response.code());
                    return;
                }


                List<Puestos> puestosList = response.body();
                for(Puestos puestos : puestosList ){
                    String content = "";
                    content += "puesto: "+ puestos.getPuesto() + "\n";
                    content += "descripcion_puesto: "+ puestos.getDescripcion_puesto() + "\n";
                    content += "area: "+ puestos.getArea() + "\n";
                    content += "ID: "+ puestos.getId_puesto() + "\n \n";

                    mJsonTxtView.append(content);
                }


            }

            @Override
            public void onFailure(Call<List<Puestos>> call, Throwable t) {
                mJsonTxtView.setText(t.getMessage());

            }
        });







/*
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





public class repo_url {
    protected final static String serverURL = "http://190.17.14.237:81/VIVEXWS%20-%20copia"; // "200.73.146.250:81";// "http://vivexserver.ga/"; //  "181.164.137.203:81";

    public final static String url_VivexGetAlumnos = serverURL + "/VivexGetAlumnos/index.php"; // vivexgetAlumnos
    public final static String url_VivexInsertAdministrador = serverURL + "/VivexInsertAdministrador/index.php";
    public final static String url_VivexGetAdmin = serverURL + "/VivexGetAdmin/index.php"; //vivexGetAdmin
    public final static String url_VivexAlumnosSalud = serverURL + "/VivexAlumnosSalud/index.php";
    public final static String url_VivexLogin = serverURL + "/VivexLogin/index.php";
    public final static String url_VivexCheckPay = serverURL + "/VivexCheckPay/index.php";

}






        */

    }


}