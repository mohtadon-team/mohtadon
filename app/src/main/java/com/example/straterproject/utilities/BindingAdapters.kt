package com.example.straterproject.utilities

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import com.example.straterproject.R
import com.example.straterproject.ui.UiState

//@BindingAdapter("app:setItemList")
//fun <T> setItemList(view: RecyclerView,  list :List<Music>?) {
//    if (list !=null ) {
//        (view.adapter as MusicAdapter).setData(list)
//    }else{
//        (view.adapter as MusicAdapter).setData( emptyList())
//    }
//}


@BindingAdapter("showVisibleWhenError")
fun <T> showVisibleWhenError(view: View, state: UiState<T>?) {
    view.isVisible = state is UiState.Error
}

@BindingAdapter("showVisibleWhenLoading")
fun <T> showVisibleWhenLoading(view: View, state: UiState<T>?) {
    view.isVisible = state is UiState.Loading
}

@BindingAdapter("makeTextScrollable")
fun makeTextScrollable(textView: TextView, scrollable: Boolean) {
    if (scrollable) {
        textView.isSelected = true
        textView.setSingleLine()
        textView.ellipsize = TextUtils.TruncateAt.MARQUEE
    }
}

@BindingAdapter("setImageIsLoading")
fun setImageIsLoading(imageView: ImageView, isByffering: Boolean) {
    if (isByffering) {
        imageView.setImageResource(R.drawable.loading)
    }
}

@BindingAdapter("setImagePlayOrStop")
fun setImagePlayOrStop(imageView: ImageView, isPlay: Boolean) {

    if (isPlay) {
        imageView.setImageResource(R.drawable.pause)
    } else {
        imageView.setImageResource(R.drawable.play)
    }
}

@BindingAdapter("loadImageResource")
fun loadImage(imageView: ImageView, source: String?) {
    imageView.load(source)
}


@BindingAdapter("isRepeatClickBackground")
fun isRepeatClickBackground(imageView: ImageView, isRepeat: Boolean) {

    if (isRepeat) imageView.setBackgroundResource(R.drawable.corner_image)
    else imageView.background = null

}


@BindingAdapter("isShuffleClickedBackground")
fun isShuffleClickedBackground(imageView: ImageView, isShuffled: Boolean) {
    if (isShuffled) imageView.setBackgroundResource(R.drawable.corner_image)
    else imageView.background = null
}

@BindingAdapter("setMaxProgressForSeekBar")
fun setMaxProgressForSeekBar(seekBar: SeekBar, maxProgress: Long) {
    seekBar.max = maxProgress.toInt()
}

@BindingAdapter("setCurrentProgressForSeekBar")
fun setCurrentProgressForSeekBar(seekBar: SeekBar, progress: Long) {
    seekBar.progress = progress.toInt()
}

@BindingAdapter("setTimeMinutes")
fun setTimeMinutes(textView: TextView, progress: Long) {
    textView.text = convertLongDurationToTime(progress)
}
