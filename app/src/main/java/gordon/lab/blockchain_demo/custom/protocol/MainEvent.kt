package gordon.lab.blockchain_demo.custom.protocol

import gordon.lab.blockchain_demo.core.uiEvent

sealed class MainEvent : uiEvent {
    object FetchList : MainEvent()
    object ObserverList : MainEvent()
}