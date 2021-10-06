package pt.tiagocarvalho.omni.news

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search")
    suspend fun search(@Query("query") query: String): SearchResponse
}
