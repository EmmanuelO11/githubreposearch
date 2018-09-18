package com.example.platinumstandard.githubrepo.Models


data class Result (val total_count: Int,
                   val incomplete_results: Boolean,
                   val items: ArrayList<Users>)

data class Users(val name: String,
            val id: Int,
            val language: String,
            val stargazers_count: Int,
            val description: String,
            val html_url: String,
            var owner: Owners)


data class Owners(val login: String,
             val avatar_url: String,
             val id: Int)






