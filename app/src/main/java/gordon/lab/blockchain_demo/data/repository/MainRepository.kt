package gordon.lab.blockchain_demo.data.repository

import gordon.lab.blockchain_demo.core.network.BlockchainApi
import gordon.lab.blockchain_demo.data.model.MarketPrices
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocketListener
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainRepository @Inject constructor (private val api: BlockchainApi){
    suspend fun getMarketPrices(): MarketPrices {
        return api.getMarketPrices()
    }

    fun getMarketPricesObserver(wslistener: WebSocketListener) {
        // wss test
        val client = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS)
            //.sslSocketFactory() - ? нужно ли его указывать дополнительно
            .build()
        val request = Request.Builder()
            .url("wss://stream.yshyqxx.com/ws/btcusdt@aggTrade") // 'wss' - для защищенного канала
            .build()
        client.newWebSocket(request, wslistener) // this provide to make 'Open ws connection'
    }
}