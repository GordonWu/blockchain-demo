package gordon.lab.blockchain_demo.custom.ui

import androidx.recyclerview.widget.RecyclerView
import gordon.lab.blockchain_demo.data.model.MarketPricesItem
import gordon.lab.blockchain_demo.databinding.RowPriceItemBinding
import gordon.lab.blockchain_demo.util.Tool.PriceTrim
import gordon.lab.blockchain_demo.util.Tool.getDateTime
import gordon.lab.blockchain_demo.util.Tool.priceTrim

class PriceItemViewHolder( private val itemBinding: RowPriceItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(item: MarketPricesItem) {
        itemBinding.tvTimestamp.text = getDateTime(item.timestamp.toString())
        itemBinding.tvPrice.text = priceTrim(item.price)
        itemBinding.tvQuantity.text = item.quantity
    }
}