package com.samet.retrofitexamplekotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samet.retrofitexamplekotlin.R
import com.samet.retrofitexamplekotlin.model.CryptoModel
import com.samet.retrofitexamplekotlin.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val baseurl = "https://api.nomics.com/v1/"
    private var cryptoModels: ArrayList<CryptoModel>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://api.nomics.com/v1/prices?key=024f6481805453104b95c4de4e7f5e37778b637d
        //024f6481805453104b95c4de4e7f5e37778b637d

        loadData()

    }
    private  fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels=ArrayList(it)
                        for (cryptoModel:CryptoModel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {

                t.printStackTrace()
            }

        })
    }
    }
