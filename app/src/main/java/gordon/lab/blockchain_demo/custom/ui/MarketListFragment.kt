package gordon.lab.blockchain_demo.custom.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import gordon.lab.blockchain_demo.Constants.NORMAL_CLOSURE_STATUS
import gordon.lab.blockchain_demo.custom.adapter.MarketAdapter
import gordon.lab.blockchain_demo.custom.protocol.MainEvent
import gordon.lab.blockchain_demo.custom.protocol.MainState
import gordon.lab.blockchain_demo.data.model.MarketPrices
import gordon.lab.blockchain_demo.data.model.MarketPricesItem
import gordon.lab.blockchain_demo.databinding.FragmentMarketPricesBinding
import gordon.lab.blockchain_demo.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class MarketListFragment:Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var binding: FragmentMarketPricesBinding ?= null
    private var priceAdapter = MarketAdapter()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketPricesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.run {
            initUserRecycler()
            initViewModelObserve()
            lifecycleScope.launch {
                viewModel.setEvent(MainEvent.FetchList)

                viewModel.setEvent(MainEvent.ObserverList(echoWebSocketListener))
            }
        }
    }

    private fun FragmentMarketPricesBinding.initUserRecycler() {
        marketList.adapter = priceAdapter
        marketList.layoutManager = LinearLayoutManager(context)
        marketList.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
    }

    private fun FragmentMarketPricesBinding.initViewModelObserve(){
        lifecycleScope.launch {
            viewModel.viewState.collect {
                when(it.mainState){
                    is MainState.Idle->{
                        //do nothing~
                    }
                    is MainState.Loading->{
                        progressBar.isVisible = it.mainState.isLoading
                    }
                    is MainState.FetchedData->{
                        priceAdapter.setDataModel(it.mainState.marketPrices)
                        progressBar.isVisible =  it.mainState.isLoading
                    }
                    is MainState.ObserverData->{
                        priceAdapter.setDataModel(it.mainState.marketPrices)
                        progressBar.isVisible =  it.mainState.isLoading
                    }
                    is MainState.Error->{
                        Toast.makeText(context, it.mainState.error, Toast.LENGTH_LONG).show()
                        progressBar.isVisible =  it.mainState.isLoading
                    }
                }
            }
        }
    }

    private val echoWebSocketListener = object: WebSocketListener() {
        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null)
            output("Closing : $code / $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            output("Error : " + t.message)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            val data = Gson().fromJson(text, MarketPricesItem::class.java)
            lifecycleScope.launch {
                priceAdapter.updateNewModelItem(data)
            }
        }

        private fun output(txt: String) {
            Log.v("gw", txt)
        }
    }
}