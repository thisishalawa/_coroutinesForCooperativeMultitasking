package master.coroutines.multitasking.intro

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import master.coroutines.multitasking.databinding.FragmentIntroBinding

const val TAG = "TAG_DEBUG"

@SuppressLint("SetTextI18n")
class IntroFragment : Fragment() {

    // binding
    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onStart() {
        super.onStart()




    }

    private fun switchCoroutineBetweenThreads(){

        binding.btnCount.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                // background thread
                downloadUserData()
            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..2000) {
            withContext(Dispatchers.Main) {
                // main thread
                binding.mainTxt.text =
                    "Downloading user in $i in ${Thread.currentThread().name}"
            }
        }
    }
}
