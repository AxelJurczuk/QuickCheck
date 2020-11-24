package com.example.android.quickcheck.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.quickcheck.MainActivity
import com.example.android.quickcheck.R
import com.example.android.quickcheck.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        auth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {

            if (binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "Successfully Registered",
                                Toast.LENGTH_LONG
                            ).show()
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        } else {
                            Toast.makeText(requireContext(),
                                "Registration Failed",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            } else {
                Toast.makeText(requireContext(), "Complete the fields", Toast.LENGTH_LONG)
                    .show()
            }
        }
        return binding.root
    }
}