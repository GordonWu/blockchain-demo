package gordon.lab.blockchain_demo.data.repository

import gordon.lab.blockchain_demo.core.network.BlockchainApi
import gordon.lab.blockchain_demo.data.model.MarketPrices
import javax.inject.Inject

class MainRepository @Inject constructor (private val api: BlockchainApi){
    suspend fun getMarketPrices(): MarketPrices {
        return api.getMarketPrices()
    }
}