package zqx.rj.com.mvvm.common

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import zqx.rj.com.mvvm.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * author：  han7jia
 * created： 2018/10/11 16:33
 * desc：    扩展方法
 */

fun TextView.str(): String {
    return this.text.toString()
}

// 关闭软键盘
fun View.hideKeyboard() {
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

// 显示软键盘
fun View.showKeyboard() {
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

// 将 html 代码转化 为 string
// Android N（API level 24.）废弃了Html.fromHtml(String)
fun String.toHtml(): String {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, FROM_HTML_MODE_COMPACT).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}

fun ImageView.loadUrl(context: Context, url: String) {

    val options = RequestOptions()
            .placeholder(R.drawable.ic_logo)
            .error(R.drawable.ic_logo)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .override(150, 200)

    Glide.with(context)
            .load(url)
            .apply(options)
            .into(this)
}

// 统一处理  (IO -> Main)
fun <T> Observable<T>.execute(observer: Observer<T>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)
}

fun Date.format(pattern: String = "yyyy-MM-dd"): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}

// R.color.xxxx  -> @ColorInt
fun Int.toColor(context: Context): Int{
    return ContextCompat.getColor(context, this)
}


