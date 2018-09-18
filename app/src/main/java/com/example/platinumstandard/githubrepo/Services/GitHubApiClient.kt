package com.example.platinumstandard.githubrepo.Services

import com.example.platinumstandard.githubrepo.Models.Result
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiClient {



    @GET("/search/repositories")
    fun search(
            @Query("q") query: String ="octocat?",
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 20)
            : Observable<Result>


    companion object useRetrofit {
        fun create(): GitHubApiClient {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(GitHubApiClient::class.java)
        }


    }
}
