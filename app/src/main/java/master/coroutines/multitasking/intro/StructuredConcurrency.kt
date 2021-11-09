package master.coroutines.multitasking.intro

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

/*
 structured Concurrency
 avoid leaks and manage them productively  {Xx unstructured manner Xx}



   binding.btnCount.setOnClickListener {
            CoroutineScope(Main).launch {
                binding.mainTxt.text = UserDataManager2().getTotalUserCount().toString()
            }
        }


out : 120

* */

/*

  Recommended 100%
  1- start with Dispatchers.Main using CoroutineScope interface
  2- inside suspending function
  3- use coroutineScope function to provide childScope


  structured Concurrency
  guarantees to complete all the tasks started by coroutines
  within the child scope before the return* of the suspending function
  +
  this coroutineScope wait for the child coroutines to complete.
  and handle exception and cancel task we started



 ---------------------------
 * */

class UserDataManager2 {
    var count = 0
    private lateinit var deferred: Deferred<Int>

    suspend fun getTotalUserCount(): Int {
         // Coroutines function not interface
         coroutineScope {
            // to work in background thread
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50
            }

            /*
            *
            * */
            deferred = async(Dispatchers.IO) {
                delay(3000)
                return@async 70
            }

        }


        return count + deferred.await()
    }

}