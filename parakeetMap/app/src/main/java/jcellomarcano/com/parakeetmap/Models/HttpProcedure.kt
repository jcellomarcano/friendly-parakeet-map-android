package jcellomarcano.com.parakeetmap.Models

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class HttpProcedure{

    companion object{
        @JvmStatic
        fun getHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(35, TimeUnit.SECONDS)
                .writeTimeout(35, TimeUnit.SECONDS)
                .readTimeout(35, TimeUnit.SECONDS)
                .build()
        }
    }

}