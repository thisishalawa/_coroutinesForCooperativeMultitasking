package master.coroutines.multitasking.intro

import kotlinx.coroutines.*

/*

  UnStructuredConcurrency
  wrong ways! to do things xx

  binding.btnCount.setOnClickListener {
            CoroutineScope(Main).launch {
                binding.mainTxt.text = UserDataManager().getTotalUserCount().toString()
            }
   }


  solution structured Concurrency
  with async builder and await function call for
  the return value

  =========================

* */

class UserDataManager {

    suspend fun getTotalUserCount(): Int {
        var count = 0
        /*
          UnStructured Concurrency
        *  Issue return -> 0
        *  return count before enter CoroutineScope
        *
        * */
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            count = 50
        }

        /*
         UnStructuredConcurrency with async builder !

        *
        * async builder and await
        * deferred
        * ==
        * return 70 after 3 second delay
        *
        * ==
        * and it's not recommended to use
        * why ?
        * error catching exceptions.
        * */

        val deferred = CoroutineScope(Dispatchers.IO).async {
            delay(3000)
            return@async 70
        }

        return count + deferred.await()
    }
}
