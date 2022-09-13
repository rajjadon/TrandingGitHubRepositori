package com.raj.jadon.network.networkService

import com.raj.jadon.network.QUERY_PARAMETER_LANGUAGE
import com.raj.jadon.network.QUERY_PARAMETER_SINCE
import com.raj.jadon.network.QUERY_PARAMETER_SPOKEN_LANGUAGE_CODE
import com.raj.jadon.network.TRENDING_API_END_POINT
import com.raj.jadon.network.model.TrendingRepo
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET(TRENDING_API_END_POINT)
    suspend fun getTrendingRepo(
        @Query(QUERY_PARAMETER_LANGUAGE) language: String,
        @Query(QUERY_PARAMETER_SINCE) since: String,
        @Query(QUERY_PARAMETER_SPOKEN_LANGUAGE_CODE) spokenLanguageCode: String
    ): List<TrendingRepo>

}
