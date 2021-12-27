package gordon.lab.blockchain_demo.core.network

import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import gordon.lab.blockchain_demo.data.model.MarketPrices
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainApiSocket {

    @Receive
    fun observeWebSocketEvent(): Flowable<WebSocket.Event>
//    @Send
//    fun sendSubscribe(subscribe: Subscribe)
//    @Receive
//    fun observeTicker(): Flowable<Ticker>

}