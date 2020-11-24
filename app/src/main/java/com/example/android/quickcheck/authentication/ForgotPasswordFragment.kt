package com.example.android.quickcheck.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.android.quickcheck.R
import com.example.android.quickcheck.databinding.FragmentForgotPasswordBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater)


        auth = FirebaseAuth.getInstance()
        resetPassword()

        return binding.root
    }

    private fun resetPassword() {
        binding.btnResetPassword.setOnClickListener {
            val email = binding.etResetPassword.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor ingrese su email", Toast.LENGTH_LONG)
                    .show()
            } else {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "Reset link sent to your email",
                                Toast.LENGTH_LONG
                            )
                                .show()
                            val intent =
                                Intent(requireContext(), AuthenticationActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Unable to send reset mail",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
            }
        }
    }
}
