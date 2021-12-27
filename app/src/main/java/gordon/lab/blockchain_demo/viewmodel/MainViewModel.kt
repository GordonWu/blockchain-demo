package gordon.lab.blockchain_demo.viewmodel

import gordon.lab.blockchain_demo.core.AsyncDelegate
import gordon.lab.blockchain_demo.core.uiState
import gordon.lab.blockchain_demo.custom.protocol.MainEvent
import gordon.lab.blockchain_demo.custom.protocol.MainState
import gordon.lab.blockchain_demo.data.repository.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repo: MainRepository,
    private val asyncDelegate: AsyncDelegate):
    BaseViewModel<MainEvent, MainViewModel.State>(asyncDelegate) {

    data class State(
        val mainState: MainState
    ) : uiState

    override fun onInitState(): State {
       return State(MainState.Idle)
    }

    override fun onHandleEvent(event: MainEvent) {
        when (event) {

            is MainEvent.FetchList -> {
                fetchMarketPrices()
            }
        }
    }
    private fun fetchMarketPrices(){
        asyncDelegate.ioJob {
            setState { copy(mainState = MainState.Loading()) }
            try{
                val result = repo.getMarketPrices()
                setState { copy(mainState = MainState.FetchedData(result)) }
            }catch (e:Exception){
                setState { copy(mainState = MainState.Error(e.localizedMessage)) }
            }
        }
    }
}