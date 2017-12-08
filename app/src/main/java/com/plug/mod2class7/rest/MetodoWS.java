package com.plug.mod2class7.rest;


import com.plug.mod2class7.modelos.Post;
import com.plug.mod2class7.modelos.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by OSKAR on 24/07/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

public interface MetodoWS {
    //Usamos interface para que solo declaremos los metodos, la implementacion de los metodos los haremos en una clase diferente
    /*
    GET     ->Obtener datos
     POST   ->Enviar Datos
     PUT    ->Actualizar datos(Enviar datos)
     DELETE ->Eliminar datos(Enviar datos)
    **/
    //la palabra posts sale del webservice https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    Call<ArrayList<Post>> obtenerPost();
    //La palabra users sale del web service https://jsonplaceholder.typicode.com/users
    @GET("users")
    Call<ArrayList<Usuario>> obtenerUsuario();
}
