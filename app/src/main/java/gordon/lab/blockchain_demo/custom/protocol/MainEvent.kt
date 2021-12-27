package gordon.lab.blockchain_demo.custom.protocol

import gordon.lab.blockchain_demo.core.uiEvent
import okhttp3.WebSocketListener

sealed class MainEvent : uiEvent {
    object FetchList : MainEvent()
    data class ObserverList(val wsListener: WebSocketListener) : MainEvent()
}