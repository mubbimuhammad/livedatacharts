package com.example.graphstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.graphstest.remote.DataRepository
import com.example.graphstest.remote.LeadStatisticsService
import com.example.graphstest.remote.TaskCountService
import com.example.graphstest.remote.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val dataRepository = DataRepository(
        retrofit.create(TaskCountService::class.java),
        retrofit.create(LeadStatisticsService::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()
    }

    private fun fetchData() {
        lifecycleScope.launch {
            try {
                val taskCountResponse = withContext(Dispatchers.IO) {
                    dataRepository.fetchTaskCount()
                }
                val leadStatisticsResponse = withContext(Dispatchers.IO) {
                    dataRepository.fetchLeadStatistics()
                }

                // Do something with taskCountResponse and leadStatisticsResponse
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
