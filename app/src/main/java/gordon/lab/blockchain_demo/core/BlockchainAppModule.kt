package gordon.lab.blockchain_demo.core

import android.util.Log
import com.tinder.scarlet.Scarlet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gordon.lab.blockchain_demo.Constants
import gordon.lab.blockchain_demo.Constants.baseURL
import gordon.lab.blockchain_demo.core.network.BlockchainApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocketListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object BlockchainAppModule {

    @Provides
    fun BlockchainApiProvides() :BlockchainApi{
        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request: Request = chain.request()
                val response = chain.proceed(request)

                when(response.code){
                    422->{
                        Log.d("API", "Unprocessable Entity")
                    }
                    503->{
                        Log.d("API", "Service Unavailable")
                    }
                }

                response
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BlockchainApi::class.java)

    }


    @Provides
    fun BlockchainRequest():Request{
        return  Request.Builder()
            .url(Constants.baseWSS)
            .build()
    }

    @Provides
    fun BlockchainClient() :OkHttpClient{
        // wss test
       return OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)
            //.sslSocketFactory()
            .build()
    }
}