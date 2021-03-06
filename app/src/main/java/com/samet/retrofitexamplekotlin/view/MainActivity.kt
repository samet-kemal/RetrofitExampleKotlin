package com.samet.retrofitexamplekotlin.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samet.retrofitexamplekotlin.R
import com.samet.retrofitexamplekotlin.adapter.RecyclerViewAdapter
import com.samet.retrofitexamplekotlin.model.CryptoModel
import com.samet.retrofitexamplekotlin.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowHolder.Listener {

    private val baseurl = "https://api.nomics.com/v1/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    //Disposable
    private var compositDesposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        compositDesposable = CompositeDisposable()
        //RecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        loadData()

    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositDesposable?.add(
            retrofit.getData().subscribeOn(Schedulers.io())
                .observeOn(
                    AndroidSchedulers
                        .mainThread()
                ).subscribe(this::handleResponse)


        )

        /**
         *  val service = retrofit.create(CryptoAPI::class.java)
         *  val call = service.getData()
        call.enqueue(object: Callback<List<CryptoModel>> {
        override fun onResponse(
        call: Call<List<CryptoModel>>,
        response: Response<List<CryptoModel>>
        ) {
        if (response.isSuccessful){
        response.body()?.let {
        cryptoModels = ArrayList(it)

        cryptoModels?.let {
        recyclerViewAdapter = RecyclerViewAdapter(it, this@MainActivity)

        recyclerView.adapter = recyclerViewAdapter
        }

        }
        }
        }

        override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {

        t.printStackTrace()
        }

        })
        }**/
    }

    private fun handleResponse(cryptoList: List<CryptoModel>) {

        cryptoModels = ArrayList(cryptoList)

        cryptoModels?.let {
            recyclerViewAdapter = RecyclerViewAdapter(it, this@MainActivity)

            recyclerView.adapter = recyclerViewAdapter
        }
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this, "Clicked:${cryptoModel.currency}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositDesposable?.clear()
    }
}


