package gordon.lab.blockchain_demo.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class AsyncDelegate @Inject constructor() {

    open fun ioJob(func: suspend () -> Unit) {
       CoroutineScope(Dispatchers.IO).launch {
           func()
       }
   }

   open suspend fun uiJob(func: () -> Unit) = withContext(Dispatchers.Main) {
       func()
   }
}