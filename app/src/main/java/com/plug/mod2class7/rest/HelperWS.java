package com.plug.mod2class7.rest;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by OSKAR on 24/07/2017.
 * Oskar Steven Conislla Contreras
 * oskarconislla20@gmail.com
 * 947446763
 */

public class HelperWS {
    public static Retrofit obtenerConfiguracion(){
        return new Retrofit.Builder()
                //Ruta base del web service
                .baseUrl("https://jsonplaceholder.typicode.com/")
                //http://api.eventful.com/rest
                //Especificamos en que está devolviendo la info el web service
                .addConverterFactory(GsonConverterFactory.create())
                //Construimos la configuracion de retrofit
                .build();
    }
}