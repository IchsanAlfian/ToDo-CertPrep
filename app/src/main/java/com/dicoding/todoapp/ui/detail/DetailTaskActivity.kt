package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)

        val taskId = intent.getIntExtra(TASK_ID, -1)
        detailTaskViewModel.setTaskId(taskId)
        detailTaskViewModel.task.observe(this, { task ->
            val titleTextView: TextView = findViewById(R.id.detail_ed_title)
            val descriptionTextView: TextView = findViewById(R.id.detail_ed_description)
            val dueDateTextView: TextView = findViewById(R.id.detail_ed_due_date)

            titleTextView.text = task?.title
            descriptionTextView.text = task?.description
            dueDateTextView.text = task?.dueDateMillis?.let { DateConverter.convertMillisToString(it.toLong()) }
        })
        val btnDelete: Button = findViewById(R.id.btn_delete_task)
        btnDelete.setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }

    }
}