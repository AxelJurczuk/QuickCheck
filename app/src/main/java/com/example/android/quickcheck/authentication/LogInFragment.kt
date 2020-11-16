package com.example.android.quickcheck.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.android.quickcheck.MainActivity
import com.example.android.quickcheck.R
import com.example.android.quickcheck.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLogInBinding.inflate(layoutInflater)

        binding.logInButton.setOnClickListener {
            view?.findNavController()?.navigate (R.id.action_logInFragment_to_signInFragment) }

        binding.forgotPasswordButton.setOnClickListener {
            view?.findNavController()?.navigate (R.id.action_logInFragment_to_forgotPasswordFragment) }

        binding.skipButton.setOnClickListener {
            val intent = Intent (activity, MainActivity::class.java)
            activity?.startActivity(intent)
        }

        return binding.root
    }

}