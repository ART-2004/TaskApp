package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import androidx.fragment.app.Fragment
import com.example.taskmanager.App
import com.example.taskmanager.Task
import com.example.taskmanager.databinding.FragmentHomeBinding

@Suppress("DEPRECATION", "UNCHECKED_CAST")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort_menu) {

            val items = arrayOf("Дата", "A-z", "z-A")
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Сортировать")
            builder.setItems(items) { _, which ->
                when (which) {
                    0 -> {
                        adapter.addTasks(
                            App.database.TaskDao()?.getAllTaskByDate() as List<Task>
                        )
                    }
                    1 -> {
                        adapter.addTasks(
                            App.database.TaskDao()?.getAllTaskByAlphabetAz() as List<Task>
                        )

                    }
                    2 -> {
                        adapter.addTasks(
                            App.database.TaskDao()?.getAllTaskByAlphabetZa() as List<Task>
                        )
                    }
                }
            }
            builder.show()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TaskAdapter(this::OnLongClick)
        setData()

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun OnLongClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Do you want to delete?")
        alertDialog.setNegativeButton("No",
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog?.cancel()
                }
            })
        alertDialog.setPositiveButton("Yes",
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    App.db.taskDao().delete(task)
                    setData()
                }
            })
        alertDialog.create().show()
    }

    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addTask(tasks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}