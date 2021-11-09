package master.coroutines.multitasking.intro

/*

 Sequential Decomposition vs Parallel Decomposition ♥

=========================================================
sequential


       CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "onStart .. ")
            val stock1 = getStock1()
            val stock2 = getStock2()

            val total = stock1 + stock2
            Log.d(TAG, "onStart total= $total")
        }

out :
2021-11-09 09:37:36 25128-27928 onStart ..
2021-11-09 09:37:46 25128-27928 getStock1: returned
2021-11-09 09:37:54 25128-27928 getStock2: returned
2021-11-09 09:37:54 25128-27928 onStart: 90000



=========================================================
Parallel
   - Async coroutine builder to each process
    to get the return value we need to
    invoke await function of each async builder

        CoroutineScope(Dispatchers.IO).launch {
            Log.d(TAG, "onStart .. ")
            val stock1 = async { getStock1() }
            val stock2 = async { getStock2() }

            val total = stock1.await() + stock2.await()
            Log.d(TAG, "onStart total= $total")
        }


out:
2021-11-09 09:43:25 onStart ..
2021-11-09 09:43:33 getStock2: returned
2021-11-09 09:43:35 getStock1: returned
2021-11-09 09:43:35 onStart total= 90000



==================================================
    private suspend fun getStock1(): Int {
        delay(10000)
        Log.d(TAG, "getStock1: returned ")
        return 55000
    }

    private suspend fun getStock2(): Int {
        delay(8000)
        Log.d(TAG, "getStock2: returned ")
        return 35000
    }
=========================================================


    Main & background threads

        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, "onStart .. ")

            only this parallel event will
            happen in the background

            val stock1 = async(IO) { getStock1() }
            val stock2 = async(IO) { getStock2() }

            val total = stock1.await() + stock2.await()
            Log.d(TAG, "onStart total= $total")
        }

*/