package com.example.platinumstandard.githubrepo.Activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.example.platinumstandard.githubrepo.R
import com.example.platinumstandard.githubrepo.Adapters.UserViewAdapter

class RepoDetailActivity : AppCompatActivity() {

    lateinit var topBar: String
    lateinit var loginName: TextView
    lateinit var repoName: TextView
    lateinit var repoDescripton: TextView
    lateinit var languageName: TextView
    lateinit var starCount: TextView
    lateinit var hyperTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)

        topBar = "Details"
        supportActionBar?.title = topBar

        val incomingIntent = intent
        val repoTitle: String = incomingIntent.getStringExtra(UserViewAdapter.CustomViewHolder.REPO_TITLE)
        repoName = findViewById(R.id.repo_name)
        repoName.text = repoTitle


        val language: String? = incomingIntent.getStringExtra(UserViewAdapter.CustomViewHolder.LANGUAGE_NAME)
        languageName = findViewById(R.id.language_name)
        languageName.text = language


        val login: String? = incomingIntent.getStringExtra(UserViewAdapter.CustomViewHolder.LOGIN_NAME)
        loginName = findViewById(R.id.login_name)
        loginName.text = login

        val repoDescript: String? = incomingIntent.getStringExtra(UserViewAdapter.CustomViewHolder.REPOS_DESCRIPTION)
        repoDescripton = findViewById(R.id.repo_description)
        repoDescripton.text = repoDescript

        val starsCount: String? = incomingIntent.getStringExtra(UserViewAdapter.CustomViewHolder.STARS_COUNT)
        starCount = findViewById(R.id.star_count)
        starCount.text = starsCount

        if (language.isNullOrEmpty()){
            languageName.text = "N/A"
        }

        if (repoDescript.isNullOrEmpty()){
            repoName.text = "Description is N/A"
        }

        hyperTextView = findViewById(R.id.link)


        var hyperlink = "View full repositories here"

        val ss = SpannableString(hyperlink)

        val clickableSpan: ClickableSpan = object : ClickableSpan(){
            override fun onClick(p0: View?) {
                val browserIntent = Intent(Intent.ACTION_VIEW)
                browserIntent.data = Uri.parse(incomingIntent.getStringExtra(UserViewAdapter.CustomViewHolder.HTML_URL))
                startActivity(browserIntent)
            }

            override fun updateDrawState(ds: TextPaint?) {
                super.updateDrawState(ds)
                ds!!.color = Color.BLUE
                ds.isUnderlineText = false
            }

        }


        ss.setSpan(clickableSpan,23, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        hyperTextView.text = ss
        hyperTextView.movementMethod = LinkMovementMethod.getInstance()

    }
}
