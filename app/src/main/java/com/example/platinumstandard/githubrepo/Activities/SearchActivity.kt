package com.example.platinumstandard.githubrepo.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.*
import android.support.v7.widget.PopupMenu
import android.widget.*
import com.example.platinumstandard.githubrepo.Services.GitHubApiClient

import com.example.platinumstandard.githubrepo.Models.Users
import com.example.platinumstandard.githubrepo.Models.username
import com.example.platinumstandard.githubrepo.R
import com.example.platinumstandard.githubrepo.Adapters.UserViewAdapter
import com.example.platinumstandard.githubrepo.Models.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class SearchActivity : AppCompatActivity() {



    companion object {

    private val clientId = "e62fa1438995a3a54540"
    private val clientSecret = "0e1aec1cb7448b246d47646dd513493988f30fd8"
    private val redirectUri = "githubrepo://callback"

}

    //variables
    lateinit var service: GitHubApiClient
    lateinit var recyclerView: RecyclerView
    val compositeDisposable = CompositeDisposable()
    lateinit var adapter: UserViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = GitHubApiClient.create()



        performSearch()

        initRecyclerView()

        showRepos()


    }

    private fun performSearch() {

        searh_button.setOnClickListener {
            val query = searchView.text.toString()
            if (query.isNotEmpty()) {
                showRepos(query)
                adapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this, "Search not found", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

    }




    private fun showRepos(query: String = username) {

        compositeDisposable.add(service.search("user:$query")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{results -> displayData(results.items)})

    }

    private fun displayData(users: ArrayList<Users>) {
        adapter = UserViewAdapter(this, users)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

}




