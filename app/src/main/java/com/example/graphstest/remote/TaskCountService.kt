package com.example.graphstest.remote

import com.example.graphstest.model.TaskCountResponse
import retrofit2.http.GET

interface TaskCountService {
    @GET("/employee/getTaskCount")
    suspend fun getTaskCount(): TaskCountResponse
}
