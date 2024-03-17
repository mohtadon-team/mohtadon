package com.mohtdon.utilities

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import com.mohtdon.mohtdon.R
import com.mohtdon.ui.UiState
import me.tankery.lib.circularseekbar.CircularSeekBar

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



@BindingAdapter("app:showWhenLoading")
fun <T> showWhenLoading(view: View,isLoading :Boolean) {
    if (isLoading){
        view.visibility = View.VISIBLE
    }else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("app:hideWhenLoading")
fun <T> hideWhenLoading(view: View,isLoading :Boolean) {
    if (isLoading){
        view.visibility = View.GONE
    }else {
        view.visibility = View.VISIBLE
    }
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
        imageView.setImageResource(R.drawable.pause_ic)

    } else {
        imageView.setImageResource(R.drawable.play_ic)
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

@BindingAdapter("setMaxProgressForCircularSeekBar")
fun setMaxProgressForCircularSeekBar(seekBar: CircularSeekBar, maxProgress: Long) {
    seekBar.max = maxProgress.toFloat()
}

@BindingAdapter("setCurrentProgressForCircularSeekBar")
fun setCurrentProgressForCircularSeekBar(seekBar: CircularSeekBar, progress: Long) {
    seekBar.progress = progress.toFloat()
}

@BindingAdapter("setTimeMinutes")
fun setTimeMinutes(textView: TextView, progress: Long) {
    textView.text = convertLongDurationToTime(progress)
}


@BindingAdapter("app:isVisible")
fun isVisible(view: View, isVisible :Boolean) {
    view.isVisible = isVisible
}