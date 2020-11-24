package com.example.android.quickcheck.mainFragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.android.quickcheck.R
import com.example.android.quickcheck.adapter.ItemAdapter
import com.example.android.quickcheck.data.DataSource
import com.example.android.quickcheck.databinding.FragmentListBinding
import com.example.android.quickcheck.model.Student
import com.google.firebase.firestore.FirebaseFirestore
import java.time.Instant.now
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class ListFragment : Fragment(), ItemAdapter.OnItemClick {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ItemAdapter

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        return binding.root
    }

    // callback cuando la vista ya fue creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val parameters = arguments

        //activity?.title = parameters?.getString("groupName").toString()

        adapter = ItemAdapter(requireContext(), this@ListFragment)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        if (recyclerView != null) {
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
        }

        when (parameters?.getString("groupName")) {
            "Year1" -> {
                DataSource().loadFirstStudents(object : DataSource.DataFetched {
                    override fun onFetched(studentList: List<Student>) {
                        adapter.studentItemsList = studentList
                        adapter.notifyDataSetChanged()
                        binding.saveButton.isEnabled = true
                    }
                })
            }
            "Year2" -> {
                DataSource().loadSecondStudents(object : DataSource.DataFetched {
                    override fun onFetched(studentList: List<Student>) {
                        adapter.studentItemsList = studentList
                        adapter.notifyDataSetChanged()
                        binding.saveButton.isEnabled = true
                    }
                })
            }
            "Year3" -> {
                DataSource().loadThirdStudents(object : DataSource.DataFetched {
                    override fun onFetched(studentList: List<Student>) {
                        adapter.studentItemsList = studentList
                        adapter.notifyDataSetChanged()
                        binding.saveButton.isEnabled = true
                    }
                })
            }
            "Year4" -> {
                DataSource().loadFourthStudents(object : DataSource.DataFetched {
                    override fun onFetched(studentList: List<Student>) {
                        adapter.studentItemsList = studentList
                        adapter.notifyDataSetChanged()
                        binding.saveButton.isEnabled = true
                    }
                })
            }
            "Year5" -> {
                DataSource().loadFifthStudents(object : DataSource.DataFetched {
                    override fun onFetched(studentList: List<Student>) {
                        adapter.studentItemsList = studentList
                        adapter.notifyDataSetChanged()
                        binding.saveButton.isEnabled = true
                    }
                })
            }
        }

        val date: LocalDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val parsedDate = date.format(formatter).toString()
        Log.d("date", parsedDate)
        //reference for current group
        val group = parameters?.getString("groupName")

        binding.saveButton.setOnClickListener {

            val studentsList = adapter.studentItemsList
            val absentList = studentsList.filter { it.status == false }
            val mutableStudentsMap = mutableMapOf<String, Any>()
            var docReference = 0

            absentList.forEach { student ->
                mutableStudentsMap.putAll(convertToMap(student))
                docReference++
                db.collection("ausentes")
                    .document(parsedDate)
                    .collection(group.toString())
                    .document(docReference.toString())
                    .set(mutableStudentsMap)
                    .addOnSuccessListener {
                        Toast.makeText(
                            requireContext(),
                            "Absent list saved!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            requireContext(),
                            "Error saving document",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
        /*
       TODO :
       que pasa si es 1era vez y no hay conexion
    */
    }

    private fun convertToMap(student: Student): Map<String, Any> {
        return mapOf(
            "lastName" to student.lastName,
            "name" to student.name,
            "status" to student.status
        )
    }

    override fun onItemCheckedChanged(position: Int, checked: Boolean) {
        adapter.studentItemsList[position].status = checked
    }

    override fun onItemClickListener(position: Int) {
        //Toast.makeText(requireContext(),"${adapter.studentItemsList[position].name}", Toast.LENGTH_SHORT).show()
    }
}