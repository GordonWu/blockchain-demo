package gordon.lab.blockchain_demo.core.network

import gordon.lab.blockchain_demo.data.model.MarketPrices
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockchainApi {

    @GET("/api/v1/aggTrades")
    suspend fun getMarketPrices(
        @Query("symbol") symbol:String? = "BTCUSDT",
        @Query("limit") limit:Int = 40) : MarketPrices

}