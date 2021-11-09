package master.coroutines.multitasking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import master.coroutines.multitasking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

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