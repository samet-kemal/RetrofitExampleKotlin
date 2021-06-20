package com.samet.retrofitexamplekotlin.model

data class CryptoModel(   // @SerializedName("currency")
    val currency:String,

    // @SerializedName("price")
    val price:String) {
}