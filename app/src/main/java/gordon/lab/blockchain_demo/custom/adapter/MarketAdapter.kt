package gordon.lab.blockchain_demo.custom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gordon.lab.blockchain_demo.custom.ui.PriceItemViewHolder
import gordon.lab.blockchain_demo.data.model.MarketPricesItem
import gordon.lab.blockchain_demo.databinding.RowPriceItemBinding
import kotlin.reflect.KFunction1

class MarketAdapter: RecyclerView.Adapter<PriceItemViewHolder>() {

    var dataModel = arrayListOf<MarketPricesItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var onLoadMoreCall: (() -> Unit)? = null
    private var onItemClick: KFunction1<String, Unit>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceItemViewHolder {
        val itemBinding = RowPriceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PriceItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PriceItemViewHolder, position: Int) {
        holder.bind(dataModel[position])
    }

    override fun getItemCount(): Int {
        return dataModel.size
    }

    fun setDataModel(mUserItems: List<MarketPricesItem>) {
        dataModel.addAll(mUserItems)
        notifyDataSetChanged()
    }

    fun setLoadMore( setLoadMoreCall: (() -> Unit)? = null) {
        onLoadMoreCall = setLoadMoreCall
    }

    fun setOnItemClick(setItemLick: KFunction1<String, Unit>? = null) {
        onItemClick = setItemLick
    }
}