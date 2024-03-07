package com.example.graphstest.remote

import com.example.graphstest.model.LeadStatisticsResponse
import retrofit2.http.GET

interface LeadStatisticsService {
    @GET("/employee/getLeadStatistics")
    suspend fun getLeadStatistics(): LeadStatisticsResponse
}
