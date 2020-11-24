package com.example.android.quickcheck.mainFragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.quickcheck.R
import com.example.android.quickcheck.adapter.ItemAdapter
import com.example.android.quickcheck.data.DataSource
import com.example.android.quickcheck.databinding.FragmentQueryBinding
import com.example.android.quickcheck.model.Student
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class QueryFragment : Fragment(), ItemAdapter.OnItemClick {
    private lateinit var binding: FragmentQueryBinding
    private val db = FirebaseFirestore.getInstance()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQueryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ItemAdapter(requireContext(), this@QueryFragment)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_absents)

        if (recyclerView != null) {
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
        }

        binding.pickDateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        binding.queryListButton.setOnClickListener {

            val bundle = arguments
            val group: String? = bundle?.getString("group name")
            val date: String =
                binding.pickDateEditText.text.toString()//escribe sin el 0 delante del dia 4-11-2020

            DataSource().loadAbsentStudents(
                date,
                group.toString(),
                object : DataSource.DataFetched {
                    override fun onFetched(studentList: List<Student>) {
                        adapter.studentItemsList = studentList
                        adapter.notifyDataSetChanged()
                        Log.d("students", studentList.toString())
                    }
                })
        }
    }

    private fun showDatePickerDialog() {
        val newFragment =
            DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                // +1 because January is zero
                val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val selectedDate = LocalDate.of(year, month + 1, day).format(formatter)

                binding.pickDateEditText.setText(selectedDate)
                binding.queryListButton.isEnabled = true
            })
        newFragment.show(childFragmentManager, "datePicker")
    }

    override fun onItemClickListener(position: Int) {

    }

    override fun onItemCheckedChanged(position: Int, checked: Boolean) {

    }

}