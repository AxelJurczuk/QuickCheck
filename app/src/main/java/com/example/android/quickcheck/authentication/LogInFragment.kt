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
import com.example.android.quickcheck.databinding.FragmentLogInBinding
import com.example.android.quickcheck.mainFragments.QueryFragment
import com.google.firebase.auth.FirebaseAuth


class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()

        binding.logInButton.setOnClickListener {
            if (binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()) {
                auth.signInWithEmailAndPassword(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "Successfully Logged in",
                                Toast.LENGTH_LONG
                            ).show()
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Login Failed",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
            } else {
                Toast.makeText(requireContext(), "Complete the fields", Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding.btnSignUp.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, SignInFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        binding.btnForgotPassword.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, ForgotPasswordFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }

        return binding.root
    }

}