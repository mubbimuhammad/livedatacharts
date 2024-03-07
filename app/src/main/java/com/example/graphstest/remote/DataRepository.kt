package com.example.graphstest.remote

import com.example.graphstest.model.LeadStatisticsResponse
import com.example.graphstest.model.TaskCountResponse

class DataRepository(
    private val taskCountService: TaskCountService,
    private val leadStatisticsService: LeadStatisticsService
) {

    suspend fun fetchTaskCount(): TaskCountResponse {
        return taskCountService.getTaskCount()
    }

    suspend fun fetchLeadStatistics(): LeadStatisticsResponse {
        return leadStatisticsService.getLeadStatistics()
    }
}
