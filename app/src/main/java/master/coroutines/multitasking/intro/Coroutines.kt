package master.coroutines.multitasking.intro

/*
=======================================================================
*     Dispatchers.ui
*     - local database.communicate with network and work with files
*
*     Dispatchers.default
*     - CPU intensive tasks such as
*     - sorting list with 10k list items.parsing a huge json
*
*     Dispatchers.Unconfined
*     - dispatchers used globalScope. coroutine will run on
*     - a current thread but
*
*     if it suspend and resumed it will run on whichever thread
*     that the suspending function is running on.
*     not recommended! with android
=======================================================================

 Coroutine Builder are extensions functions of coroutine
 to launch a coroutine

 - launch
 - async
 - produce
 - runBlocking

=======================================================================

 - launch
 builder launch a new without looking the current thread
 used for coroutines that does not have any result as the return value

 - async
 if we want get such result value
 launch coroutine in parallel
 launch a new without looking the current thread


 - produce
 coroutine which produces a steam of elements
 returns an instance of receive Channel

 - runBlocking
 for testing
 will block the thread
 return a result of Type T

=======================================================================
 - Structured Concurrency!
 avoid leaks and manage them productively  {Xx unstructured manner Xx}
========================================================================
      example
      CoroutineScope(Dispatchers.IO).launch {

        * interface provide the scope of coroutine
        * we in to run downloadData() task in a
        * background thread

                downloadData()
      }

       fun downloadData(){
        for (i in 1..2000) {
        log.d("Downloading user in $i in ${Thread.currentThread().name}"}}
      }

      * its always recommended to start coroutines using main thread
      * and then switch to background threads to launch.

        Dispatchers.Main -> main threads

        * we also use main.dispatchers for small.light weight
        * tasks ->
        *  call to ui functions
        *  call to a suspending
        *  get updates from liveData
        *
        *  Again!
        *  its always recommended to start coroutines using main thread
        *  and then switch to background threads to launch.
* */

/*
=========================================================================
  Switch coroutine between threads


        binding.btnCount.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                // background thread
                downloadUserData()
            }
        }

       private fun downloadUserData(){
        for (i in 1..2000) {
        log.d("Downloading user in $i in ${Thread.currentThread().name}"}}

      private suspend fun downloadUserData() {
        for (i in 1..2000) {
            withContext(Dispatchers.Main) {
                // main thread
                binding.mainTxt.text =
                    "Downloading user in $i in ${Thread.currentThread().name}"
            }
        }
    }
==============================================================
    Suspending Functions ?
    suspend modifier

    -withContext -withTimeout -withTimeoutOrNull -join
    -delay -await  and more ..

    - whenever coroutine is suspended !
    current stack frame of the function is copied and saved in memory
    when fun resumes after completing its task the stake frame is copied
    back from where its was saved and start running again



==============================================================



==============================================================
*/

