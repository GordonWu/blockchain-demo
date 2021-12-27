package gordon.lab.blockchain_demo.data.repository

 import gordon.lab.blockchain_demo.core.BlockchainAppModule.BlockchainClient
 import gordon.lab.blockchain_demo.core.BlockchainAppModule.BlockchainRequest
 import gordon.lab.blockchain_demo.core.network.BlockchainApi
import gordon.lab.blockchain_demo.data.model.MarketPrices
import okhttp3.WebSocketListener
import javax.inject.Inject

class MainRepository @Inject constructor (private val api: BlockchainApi){
    suspend fun getMarketPrices(): MarketPrices {
        return api.getMarketPrices()
    }

    fun getMarketPricesObserver(wsListener: WebSocketListener) {
          BlockchainClient().newWebSocket(BlockchainRequest(), wsListener)
    }
}