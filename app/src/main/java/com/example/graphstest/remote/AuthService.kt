// AuthService.kt
package com.example.graphstest.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/signin")
    suspend fun signIn(@Body request: SignInRequest): TokenResponse
}
