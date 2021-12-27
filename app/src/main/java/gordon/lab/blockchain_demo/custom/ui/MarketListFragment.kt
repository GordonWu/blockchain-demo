package gordon.lab.blockchain_demo.custom.ui

import android.os.Bundle
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
import gordon.lab.blockchain_demo.custom.adapter.MarketAdapter
import gordon.lab.blockchain_demo.custom.protocol.MainEvent
import gordon.lab.blockchain_demo.custom.protocol.MainState
import gordon.lab.blockchain_demo.data.model.MarketPrices
import gordon.lab.blockchain_demo.databinding.FragmentMarketPricesBinding
import gordon.lab.blockchain_demo.viewmodel.MainViewModel
import kotlinx.coroutines.launch

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
            }
        }
    }

    private fun FragmentMarketPricesBinding.initUserRecycler() {
        marketList.adapter = priceAdapter
//        priceAdapter.setLoadMore(viewModel::fetchUserList)
//        userListAdapter.setOnItemClick(::onUserItemClick)
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
//                        userListAdapter.setDataModel(it.userListState.result.userList)
                        progressBar.isVisible =  it.mainState.isLoading
                    }
                    is MainState.ObserverData->{
//                        userListAdapter.setDataModel(it.userListState.result.userList)
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
}