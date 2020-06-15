package com.example.gettingdatafrominternet

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gettingdatafrominternet.R
import com.example.gettingdatafrominternet.network.MarsProperty
import com.example.gettingdatafrominternet.overview.MarsApiStatus
import com.example.gettingdatafrominternet.overview.PhotoGridAdapter



@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsProperty>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}


@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView : ImageView, status : MarsApiStatus?){
    when(status){
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        MarsApiStatus.DONE ->{
            statusImageView.visibility = View.GONE
        }

        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.connection_error)
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView : ImageView, imageUrl : String?){

    imageUrl?.let {
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imageView.context).load(imgUri)
            .apply(RequestOptions().placeholder(R.drawable.loading_animation).error(R.drawable.image_broke))
            .into(imageView)
    }


}