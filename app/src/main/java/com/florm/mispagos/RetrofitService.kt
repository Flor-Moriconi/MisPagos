package com.florm.mispagos

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URI)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create<PaymentMethodsRepositoryApi>(PaymentMethodsRepositoryApi::class.java)
}