package gordon.lab.blockchain_demo.data.model

import com.google.gson.annotations.SerializedName

data class MarketPricesItem(
    @SerializedName("M") val bestMatch: Boolean = false,
    @SerializedName("T")  val timestamp: Long = 0,
    @SerializedName("a")  val clusterID: Int = 0,
    @SerializedName("f") val firstClusterID: Long = 0,
    @SerializedName("l") val lastClusterID: Long = 0,
    @SerializedName("m") val isSelfMatch: Boolean= false,
    @SerializedName("p")val price: String = "",
    @SerializedName("q")val quantity: String = ""
)