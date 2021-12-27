package gordon.lab.blockchain_demo.custom.protocol

import gordon.lab.blockchain_demo.core.uiState
import gordon.lab.blockchain_demo.data.model.MarketPrices

sealed class MainState : uiState {
    object Idle : MainState()
    data class  Loading(val isLoading:Boolean = true) : MainState()
    class FetchedData(val marketPrices: MarketPrices, val isLoading:Boolean = false): MainState()
    class ObserverData(val marketPrices: MarketPrices, val isLoading:Boolean = false): MainState()
    data class Error(val error : String?,val isLoading:Boolean = false): MainState()
}