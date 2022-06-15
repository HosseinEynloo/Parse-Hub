package com.hossein.tamasha.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.hossein.parsehub.R
import com.hossein.parsehub.databinding.FragmentSplashBinding
import com.hossein.parsehub.utils.ConnectionCheck
import com.hossein.parsehub.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment : Fragment() {


    private  val connectionState:ConnectionCheck by lazy { ConnectionCheck(context) }

    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set delay
        lifecycle.coroutineScope.launchWhenCreated {
            delay(Constants.SPLASH_DELAY)

            if (connectionState.isConnected()){
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment2)
            }else{
                Toast.makeText(context,"Internet Not Connected",Toast.LENGTH_SHORT).show()
            }


        }

    }


}