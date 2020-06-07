package com.bigsteptech.wynknewsapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bigsteptech.wynknewsapp.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_news_detail.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity : AppCompatActivity() {

    lateinit var title: String

    lateinit var description: String
    lateinit var url: String
    lateinit var imageUrl: String
    lateinit var author: String
    lateinit var publishedDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        intent?.let {

            title = it.getStringExtra("title")
            description = it.getStringExtra("description")
            url = it.getStringExtra("url")
            imageUrl = it.getStringExtra("urlToImage")
            author = it.getStringExtra("author")
            publishedDate = it.getStringExtra("publishedAt")

        }

        setData()

        launch_news_outside.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    private fun setData() {
        Glide.with(this)
            .load(imageUrl).into(news_image)

        news_name.text = title
        news_description.text = description
        news_source.text = author
        news_date.text = getDate(publishedDate)


    }


    fun getDate(dateString: String): String {

        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date: Date =
            dateFormat.parse(dateString)

        val formatter: DateFormat =
            SimpleDateFormat("h:mm a, MMM d")

        return formatter.format(date)
    }
}