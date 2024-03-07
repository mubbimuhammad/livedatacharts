package com.example.graphstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.graphstest.remote.AuthService
import com.example.graphstest.remote.DataRepository
import com.example.graphstest.remote.LeadStatisticsService
import com.example.graphstest.remote.SignInRequest
import com.example.graphstest.remote.TaskCountService
import com.example.graphstest.remote.retrofit
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val dataRepository = DataRepository(
        retrofit.create(TaskCountService::class.java),
        retrofit.create(LeadStatisticsService::class.java)
    )

    private val authService = retrofit.create(AuthService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login()
        setUpCharts()
    }

    private fun setUpCharts() {
        val barChart = findViewById<BarChart>(R.id.barChart)
        val lineChart = findViewById<LineChart>(R.id.lineChart)

        // Populate bar chart with data
        val barEntries = arrayListOf<BarEntry>()
        barEntries.add(BarEntry(1f, 50f)) // Example data
        barEntries.add(BarEntry(2f, 70f)) // Example data
        barEntries.add(BarEntry(3f, 90f)) // Example data
        // Add more data as needed

        val barDataSet = BarDataSet(barEntries, "Bar Data")
        barDataSet.color = ContextCompat.getColor(this, androidx.appcompat.R.color.material_blue_grey_800) // Set bar color

        val barData = BarData(barDataSet)
        barChart.data = barData

        // Additional customizations for the bar chart
        barChart.description.text = "Bar Chart Description"
        barChart.animateY(1500) // Animate chart

        // Populate line chart with data
        val lineEntries = arrayListOf<Entry>()
        lineEntries.add(Entry(1f, 20f)) // Example data
        lineEntries.add(Entry(2f, 40f)) // Example data
        lineEntries.add(Entry(3f, 60f)) // Example data
        // Add more data as needed

        val lineDataSet = LineDataSet(lineEntries, "Line Data")
        lineDataSet.color = ContextCompat.getColor(this, android.R.color.holo_blue_dark) // Set line color

        val lineData = LineData(lineDataSet)
        lineChart.data = lineData

        // Additional customizations for the line chart
        lineChart.description.text = "Line Chart Description"
        lineChart.animateX(1500) // Animate chart
    }

    private fun login() {
        val signInRequest = SignInRequest("mobileEmp@gmail.com", "Emp@123456")
        lifecycleScope.launch {
            try {
                val tokenResponse = authService.signIn(signInRequest)
                val token = tokenResponse.token

                if (token.isNotEmpty()) {
                    // Now you have the token, you can use it for further API requests
                    fetchData()
                } else {
                    // Handle empty or null token response
                }
            } catch (e: Exception) {
                // Handle login error
            }
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            try {
                // Fetching data without storing the response in a variable
                withContext(Dispatchers.IO) {
                    dataRepository.fetchTaskCount()
                }
                // Fetching lead statistics without storing the response in a variable
                withContext(Dispatchers.IO) {
                    dataRepository.fetchLeadStatistics()
                }

                // You can perform actions after fetching data here
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
