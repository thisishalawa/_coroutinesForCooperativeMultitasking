package master.coroutines.multitasking.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import master.coroutines.multitasking.main.model.User
import master.coroutines.multitasking.main.repostiory.UserRepository


class MainActivityViewModel : ViewModel() {
    private val userRepository = UserRepository()
    var users: MutableLiveData<List<User>> = MutableLiveData()

     //  using new liveDataBuilder
    //  implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"

    val newUsers = liveData(Dispatchers.IO) {
        val result = userRepository.getUsers()
        emit(result)
    }

    fun getUserData() {
        viewModelScope.launch {
            var result: List<User>? = null
            withContext(Dispatchers.IO) {
                result = userRepository.getUsers()
            }

            users.value = result
        }
    }


/*
    Manually Canceling ..
    private val myJob = Job()
    // in order to run a coroutine -> coroutine scope
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    override fun onCleared() {
        */
    /*   Manually ->
    cancel the coroutines! avoid leaking'
    stop the job
    myJob.cancel()

      Auto ->
      viewModelScope
      implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
'
   * *//*

        super.onCleared()
        myJob.cancel()
    }
*/


}