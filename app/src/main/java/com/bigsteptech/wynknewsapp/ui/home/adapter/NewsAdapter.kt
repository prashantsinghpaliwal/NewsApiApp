package com.bigsteptech.wynknewsapp.ui.home.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bigsteptech.wynknewsapp.R
import com.bigsteptech.wynknewsapp.data.remote.response.Articles
import com.bumptech.glide.Glide
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter(
    private val context: Context,
    private var newsList: List<Articles>,
    private val isVerticalList: Boolean,
    val listItemClickListener: ListItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_VERTICAL = 0
    private val TYPE_HORIZONTAL = 1

    override fun getItemViewType(position: Int): Int {
        if (isVerticalList)
            return TYPE_VERTICAL
        else return TYPE_HORIZONTAL
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_VERTICAL -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.vertical_news_item, parent, false)
                return VerticalNewsViewHolder(view)

            }
            TYPE_HORIZONTAL -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.horizontal_news_item, parent, false)
                return HorizontalNewsViewHolder(view)

            }

            else -> throw IllegalArgumentException("Invalid view type")

        }

    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = newsList.get(position)

        if (getItemViewType(position) == TYPE_VERTICAL) {
            val viewHolder = holder as VerticalNewsViewHolder
            Glide.with(context)
                .load(news.urlToImage).into(viewHolder.imageView)
            viewHolder.sourceTextView.text = news.source.name
            val bgShape = viewHolder.sourceTextView.getBackground() as GradientDrawable
            bgShape.setColor(generateRandomColor())
            viewHolder.date.text = getDate(news.publishedAt)

            viewHolder.newsName.text = news.title
            viewHolder.titleView.setBackgroundColor(context.resources.getColor(R.color.shadow_color))
        } else if (getItemViewType(position) == TYPE_HORIZONTAL) {
            val viewHolder = holder as HorizontalNewsViewHolder
            Glide.with(context)
                .load(news.urlToImage).into(viewHolder.imageView)
            viewHolder.sourceTextView.text = news.source.name
            val bgShape = viewHolder.sourceTextView.getBackground() as GradientDrawable
            bgShape.setColor(generateRandomColor())
            viewHolder.date.text = getDate(news.publishedAt)

            viewHolder.newsName.text = news.title
            viewHolder.titleView.setBackgroundColor(context.resources.getColor(R.color.shadow_color))
        }

        holder.itemView.setOnClickListener {
            listItemClickListener.onItemClick(news)
        }
    }

    fun getDate(dateString: String): String {

        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date: Date =
            dateFormat.parse(dateString)

        val formatter: DateFormat =
            SimpleDateFormat("h:mm a, MMM d")

        return formatter.format(date)
    }

    fun generateRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }


    class VerticalNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.vertical_news_image)
        val sourceTextView = itemView.findViewById<TextView>(R.id.vertical_news_source)
        val date = itemView.findViewById<TextView>(R.id.vertical_news_date)
        val newsName = itemView.findViewById<TextView>(R.id.vertical_news_name)

        val titleView=itemView.findViewById<ConstraintLayout>(R.id.title_view)
    }

    class HorizontalNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.findViewById<ImageView>(R.id.horizontal_news_image)
        val sourceTextView = itemView.findViewById<TextView>(R.id.horizontal_news_source)
        val date = itemView.findViewById<TextView>(R.id.horizontal_news_date)
        val newsName = itemView.findViewById<TextView>(R.id.horizontal_news_name)
        val titleView=itemView.findViewById<ConstraintLayout>(R.id.title_view)
    }

    fun updateList(newnewsList: List<Articles>) {
        newsList = newnewsList
        this.notifyDataSetChanged()
    }

    interface ListItemClickListener {
        fun onItemClick(articles: Articles)
    }
}