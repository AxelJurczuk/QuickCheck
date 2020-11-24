package com.example.android.quickcheck.mainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.quickcheck.R
import com.example.android.quickcheck.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {

    private lateinit var binding: FragmentOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val parameters = arguments
        val group = parameters?.getString("groupName")

        binding.btnEditAbsents.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("group name", group)
            val fragment = ListFragment()
            fragment.arguments = bundle
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, fragment)
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }

        binding.btnSearchAbsents.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("group name", group)
            val fragment = QueryFragment()
            fragment.arguments = bundle
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, fragment)
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
    }

}