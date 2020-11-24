package com.example.android.quickcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.android.quickcheck.authentication.AuthenticationActivity
import com.example.android.quickcheck.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        auth = FirebaseAuth.getInstance()

        val date: LocalDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val currentDate = date.format(formatter).toString()
        binding.dateText.text = currentDate

        //listener to check if the user is logged in
        authStateListener = FirebaseAuth.AuthStateListener { auth ->
            val firebaseUser = auth.currentUser
            if (firebaseUser == null) {
                val intent = Intent(this, AuthenticationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.firstYearButton.setOnClickListener { openListFragment("Year1") }

        binding.secondYearButton.setOnClickListener { openListFragment("Year2") }

        binding.thirdYearButton.setOnClickListener { openListFragment("Year3") }

        binding.fourthYearButton.setOnClickListener { openListFragment("Year4") }

        binding.fifthYearButton.setOnClickListener { openListFragment("Year5") }

    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(this.authStateListener)

    }

    override fun onStop() {
        super.onStop()
        auth.removeAuthStateListener(this.authStateListener)

    }

    private fun openListFragment(groupName: String) {
        val intent = Intent(this, ActivityStudentLists::class.java)
        val group = Bundle()
        group.putString("groupName", groupName)
        intent.putExtras(group)
        startActivity(intent)
    }

    //Menu for Log out
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out -> {
                auth.signOut()
                onBackPressed()
                Toast.makeText(this, "Log out clicked", Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}