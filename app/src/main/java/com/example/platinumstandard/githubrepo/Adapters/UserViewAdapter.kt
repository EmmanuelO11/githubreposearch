package com.example.platinumstandard.githubrepo.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.platinumstandard.githubrepo.Activities.RepoDetailActivity
import com.example.platinumstandard.githubrepo.Models.Users
import com.example.platinumstandard.githubrepo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_repositories_list.view.*

class UserViewAdapter(val context: Context, val userList: ArrayList<Users>) : RecyclerView.Adapter<UserViewAdapter.CustomViewHolder>() {

    var userListFiltered: ArrayList<Users> = arrayListOf()

    fun add(users: ArrayList<Users>){
        userListFiltered.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.user_repositories_list, parent, false)

        return CustomViewHolder(cell)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        return holder.load(userList[position])

    }


    class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            val REPO_TITLE = "REPO_TITLE"
            val REPOS_DESCRIPTION = "REPOS_DESCRIPTION"
            val STARS_COUNT = "STAR_COUNT"
            val HTML_URL = "HTML_URL"
            val LOGIN_NAME = "LOGIN_NAME"
            val LANGUAGE_NAME = "LANGUAGE_NAME"
        }

        fun load(users: Users){
            val owner = users.owner
            with(view){
                login_name.text = owner.login
                star_count.text = users.stargazers_count.toString()
                repo_name.text = users.name
                language_textview.text = ("Language: " + users.language)
                val userImage = user_imageView
                Picasso.with(context).load("https://avatars2.githubusercontent.com/u/${owner.id}?v=4").into(userImage)

                if (users.language.isNullOrEmpty()){
                    language_textview.text = "Language: N/A"
                }

                this.setOnClickListener{
                    val intent = Intent(view.context, RepoDetailActivity::class.java)

                    intent.putExtra(REPO_TITLE, users.name)
                    intent.putExtra(REPOS_DESCRIPTION, users.description)
                    intent.putExtra(STARS_COUNT, users.stargazers_count.toString())
                    intent.putExtra(HTML_URL, users.html_url)
                    intent.putExtra(LANGUAGE_NAME, users.language)
                    intent.putExtra(LOGIN_NAME, owner.login)

                    view.context.startActivity(intent)

                }

            }
        }

    }
}