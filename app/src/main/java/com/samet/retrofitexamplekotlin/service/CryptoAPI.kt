package com.samet.retrofitexamplekotlin.service

import android.database.Observable
import com.samet.retrofitexamplekotlin.model.CryptoModel
import retrofit2.http.GET

interface CryptoAPI {
    //GET,POST ,UPDATE,DELETE
    //https://api.nomics.com/v1/
    // prices?key=024f6481805453104b95c4de4e7f5e37778b637d
    //024f6481805453104b95c4de4e7f5e37778b637d
    @GET("prices?key=024f6481805453104b95c4de4e7f5e37778b637d")
    fun getData(): Observable<List<CryptoModel>>

    //fun getData(): Call<List<CryptoModel>>
}