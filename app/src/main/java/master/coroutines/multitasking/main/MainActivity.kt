package master.coroutines.multitasking.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import master.coroutines.multitasking.R
import master.coroutines.multitasking.databinding.ActivityMainBinding
import master.coroutines.multitasking.intro.TAG
import master.coroutines.multitasking.main.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    // viewModel
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // setNavHost
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
                    as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)


    }

    private fun initViewModel() {
        // init ViewModel
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getUserData()
        // observe liveData
        viewModel.users.observe(this, {
            Log.d(TAG, "users observe : $it")
        })
        // or user the new liveData builder
        viewModel.newUsers.observe(this, {
            Log.d(TAG, "users observe : $it")
        })
    }
}
/*
===================================================================
     Two type of multitasking methods
     to  Manage multiple concurrent processes
      -  operating system
      -  operating multitasking


        Asynchronous programming!
        we should implement long running tasks
        asynchronously in a separate threads

=============================================================
       Coroutine are

      - software component that creates sub routines
      for cooperative multitasking.

      - sequence of well managed sub tasks
        as a light weight thread.

      - switch between threads
      can suspend from one thread and resume from another
      thread.

      - write asynchronous codes in sequential manner
=============================================================
        !!
        smartPhone has a refresh of at least 60hz.
        for 1 second or 1000 milliseconds
        means -> app refreshes 60 times.
        !!
     ANR -application not responding errors

* */