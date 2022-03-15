package raghav.sharma.mediumclone.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(uri: String){
    Glide.with(this).load(uri).circleCrop().into(this)
}