package com.gurtam.task.utils.binding.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.gurtam.task.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@BindingAdapter("publishedDate")
fun publishedDate(view: TextView, dateTime: LocalDateTime?) {
    dateTime?.let {
        view.text = dateTime.format(DateTimeFormatter.ISO_DATE_TIME)
    }
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?){
    imageUrl?.let {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(view)
    }
}