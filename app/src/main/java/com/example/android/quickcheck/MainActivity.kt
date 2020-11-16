package com.example.android.quickcheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.android.quickcheck.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.firstYearButton.setOnClickListener { openListFragment("firstYear") }

        binding.secondYearButton.setOnClickListener { openListFragment("secondYear") }

        binding.thirdYearButton.setOnClickListener { openListFragment("thirdYear") }

        binding.fourthYearButton.setOnClickListener { openListFragment("fourthYear") }

        binding.fifthYearButton.setOnClickListener { openListFragment("fifthYear") }

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
            R.id.log_out -> Toast.makeText(this, "Log out clicked", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}