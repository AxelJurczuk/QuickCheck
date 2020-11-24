package com.example.android.quickcheck.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.quickcheck.MainActivity
import com.example.android.quickcheck.R
import com.example.android.quickcheck.databinding.ActivityAuthenticationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = binding.toolbar2
        setSupportActionBar(toolbar)

        auth = FirebaseAuth.getInstance()

        //listener to check if the user is logged in
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            if (firebaseUser != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frame_layout, LogInFragment())
            .commit()
    }
    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(this.authStateListener)

    }

    override fun onStop() {
        super.onStop()
        auth.removeAuthStateListener(this.authStateListener)

    }

}