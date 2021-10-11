package com.samet.retrofitexamplekotlin.service

import android.database.Observable
import com.samet.retrofitexamplekotlin.model.CryptoModel
import retrofit2.http.GET

interface CryptoAPI {
    //GET,POST ,UPDATE,DELETE
    //https://api.nomics.com/v1/
    // prices?key=YOUR_KEY
    @GET("nomics API KEY")
    fun getData(): Observable<List<CryptoModel>>

    //fun getData(): Call<List<CryptoModel>>
}
