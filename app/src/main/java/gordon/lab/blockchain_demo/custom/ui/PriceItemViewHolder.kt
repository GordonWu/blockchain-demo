package gordon.lab.blockchain_demo.custom.ui

import androidx.recyclerview.widget.RecyclerView
import gordon.lab.blockchain_demo.data.model.MarketPricesItem
import gordon.lab.blockchain_demo.databinding.RowPriceItemBinding

class PriceItemViewHolder( private val itemBinding: RowPriceItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(item: MarketPricesItem) {
        itemBinding.tvTimestamp.text = item.timestamp.toString()
        itemBinding.tvPrice.text = item.price
        itemBinding.tvQuantity.text = item.quantity
    }
}