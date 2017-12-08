package com.plug.mod2class7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.plug.mod2class7.adapters.PostAdapter;
import com.plug.mod2class7.adapters.UserAdapter;
import com.plug.mod2class7.modelos.Post;
import com.plug.mod2class7.modelos.Usuario;
import com.plug.mod2class7.rest.HelperWS;
import com.plug.mod2class7.rest.MetodoWS;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    /*
    URL: Web serivce
    []->Lista->ArrayList<Cliente>
    {}->Objteto->Cliente
    public class Cliente(){
        private int userId;
        private int id;

    }

        WEB SERVICE: URL que estamos exponiendo, nos devuelve información. En este caso sera de tipo json:
        JSON: existe json array, (corchetes)- son como listados, que poseen diferentes objetos
        json object(llaves) – Objetos o clases java.
        Dentro de cada objeto existen atributos

    */
    private Button btnPresionar;
    private Button btnObtenerUsuario;
    private Button btnObtenerLocacion;
    private RecyclerView rvDatos;
    private TextView tvLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPresionar=(Button)findViewById(R.id.btnPresionar);
        btnObtenerUsuario=(Button)findViewById(R.id.btnObtenerUsuario);
        rvDatos=(RecyclerView)findViewById(R.id.rvDatos);
        btnObtenerLocacion=(Button)findViewById(R.id.btnObtenerLocacion);
        tvLatLng=(TextView)findViewById(R.id.tvLating);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnObtenerLocacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GPSTracker gpsTracker=new GPSTracker(MainActivity.this);
                if(gpsTracker.canGetLocation()){
                    Double latitud=gpsTracker.getLatitude();
                    Double longitud=gpsTracker.getLongitude();
                    tvLatLng.setText("Latitud: "+latitud+" - Longitud: "+longitud);
                }
                else{
                    gpsTracker.showSettingsAlert();
                }
            }
        });
        btnPresionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Declaramos todos los metodos guardados previamente
                MetodoWS metodoWS= HelperWS.obtenerConfiguracion().create(MetodoWS.class);
                //Llamamos a algun metodo
                Call<ArrayList<Post>> call=metodoWS.obtenerPost();
                call.enqueue(new Callback<ArrayList<Post>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                        //Intenamos cargarla a un listado
                        ArrayList<Post> posts=response.body();

                        PostAdapter postAdapter=new PostAdapter(MainActivity.this,posts);
                        rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        rvDatos.setAdapter(postAdapter);
                        //ArrayList a un adapter y de alli a un ListView o un recyclerView
                        Log.d("TAG","ok");
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                            Log.d("TAG","fail");
                    }
                });
            }
        });
        btnObtenerUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MetodoWS metodoWS=HelperWS.obtenerConfiguracion().create(MetodoWS.class);
                Call<ArrayList<Usuario>> call=metodoWS.obtenerUsuario();
                call.enqueue(new Callback<ArrayList<Usuario>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                        ArrayList<Usuario> usuarios=response.body();
                        UserAdapter userAdapter=new UserAdapter(MainActivity.this,usuarios);
                        rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        rvDatos.setAdapter(userAdapter);
                        Log.d("TAG","ok");
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {
                        Log.d("TAG","fail");
                    }
                });

            }
        });



    }
}
