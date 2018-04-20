package com.ferencboldog.ankomaterial.extensions

import android.content.Context
import android.os.Build
import android.support.annotation.AttrRes
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.ctx
import org.jetbrains.anko.wrapContent

fun collapseModePin(): android.support.design.widget.CollapsingToolbarLayout.LayoutParams.() -> Unit = { collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN }
fun lParamsWithScrollFlags(): android.support.design.widget.AppBarLayout.LayoutParams.() -> Unit = { scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED }
fun lParamsDefault(): android.support.design.widget.CollapsingToolbarLayout.LayoutParams.() -> Unit = {}

fun Context.snackbar(view: View, text: CharSequence, length: Int = Snackbar.LENGTH_SHORT, snackbar: Snackbar.() -> Unit) = Snackbar.make(view, text, length).apply { snackbar() }.show()
fun View.snackbar(text: CharSequence, length: Int = Snackbar.LENGTH_SHORT, snackbar: Snackbar.() -> Unit) = context.snackbar(this, text, length, snackbar)
fun Fragment.snackbar(view: View, text: CharSequence, length: Int = Snackbar.LENGTH_SHORT, snackbar: Snackbar.() -> Unit) = requireActivity().snackbar(view, text, length, snackbar)

fun Context.attr(@AttrRes attribute: Int): TypedValue {
    val typed = TypedValue()
    ctx.theme.resolveAttribute(attribute, typed, true)
    return typed
}

//returns px
fun Context.dimenAttr(@AttrRes attribute: Int): Int = TypedValue.complexToDimensionPixelSize(attr(attribute).data, resources.displayMetrics)

//returns color
fun Context.colorAttr(@AttrRes attribute: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(attr(attribute).resourceId, ctx.theme)
    } else {
        @Suppress("DEPRECATION")
        resources.getColor(attr(attribute).resourceId)
    }
}

fun AnkoContext<*>.dimenAttr(@AttrRes attribute: Int): Int = ctx.dimenAttr(attribute)
fun AnkoContext<*>.colorAttr(@AttrRes attribute: Int): Int = ctx.colorAttr(attribute)
fun AnkoContext<*>.attribute(@AttrRes attribute: Int): TypedValue = ctx.attr(attribute)

fun View.dimenAttr(@AttrRes attribute: Int): Int = context.dimenAttr(attribute)
fun View.colorAttr(@AttrRes attribute: Int): Int = context.colorAttr(attribute)
fun View.attr(@AttrRes attribute: Int): TypedValue = context.attr(attribute)

fun Fragment.dimenAttr(@AttrRes attribute: Int): Int = requireActivity().dimenAttr(attribute)
fun Fragment.colorAttr(@AttrRes attribute: Int): Int = requireActivity().colorAttr(attribute)
fun Fragment.attr(@AttrRes attribute: Int): TypedValue = requireActivity().attr(attribute)

object FrameLayout {
    fun <T : View> T.lparams(
        width: kotlin.Int = wrapContent, height: kotlin.Int = wrapContent,
        init: FrameLayout.LayoutParams.() -> kotlin.Unit = {}): T {
        val layoutParams = FrameLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }
}

object CollapsingToolbar {
    fun <T : View> T.lparams(
        width: kotlin.Int = wrapContent, height: kotlin.Int = wrapContent,
        init: CollapsingToolbarLayout.LayoutParams.() -> kotlin.Unit = {}): T {
        val layoutParams = CollapsingToolbarLayout.LayoutParams(width, height)
        layoutParams.init()
        this@lparams.layoutParams = layoutParams
        return this
    }
}
