package raghav.sharma.mediumclone.extensions

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.widget.TextView
import java.util.*

@SuppressLint("SimpleDateFormat")
fun TextView.formatDate(timestamp: String){
    val isoFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val simpleDateFormatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

    text = simpleDateFormatter.format(isoFormatter.parse(timestamp))
}