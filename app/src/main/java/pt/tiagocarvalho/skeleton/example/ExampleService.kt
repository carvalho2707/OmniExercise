package pt.tiagocarvalho.skeleton.example

import retrofit2.http.GET

interface ExampleService {

    @GET("in.json")
    suspend fun fetchExample(): ExampleGroup
}
