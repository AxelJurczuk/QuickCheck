package com.example.android.quickcheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.quickcheck.databinding.ActivityStudentsListsBinding
import com.example.android.quickcheck.mainFragments.ListFragment

class ActivityStudentLists : AppCompatActivity() {

    lateinit var binding: ActivityStudentsListsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsListsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle:Bundle? = intent.extras

        val listFragment = ListFragment()
        listFragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.frame_layout, listFragment).commit()
    }
}