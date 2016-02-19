package com.ferencboldog.ankovskotlinx.extensions

import android.content.Context
import android.os.Build
import android.support.annotation.AttrRes
import android.support.annotation.StyleRes
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.view.ContextThemeWrapper
import android.util.TypedValue
import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7._Toolbar
import org.jetbrains.anko.design._AppBarLayout
import org.jetbrains.anko.design.`$$Anko$Factories$DesignViewGroup`
import org.jetbrains.anko.internals.AnkoInternals

fun Context.snackbar(view: View, text: CharSequence, length: Int = Snackbar.LENGTH_SHORT, snackbar: Snackbar.() -> Unit) = Snackbar.make(view, text, length).apply { snackbar() }.show()
fun View.snackbar(text:  CharSequence, length: Int = Snackbar.LENGTH_SHORT, snackbar: Snackbar.() -> Unit) = context.snackbar(this, text, length, snackbar)
fun Fragment.snackbar(view: View, text: CharSequence, length: Int = Snackbar.LENGTH_SHORT, snackbar: Snackbar.() -> Unit) = context.snackbar(view, text, length, snackbar)

fun Context.attr(@AttrRes attribute: Int): TypedValue {
        var typed = TypedValue()
        ctx.theme.resolveAttribute(attribute, typed, true)
        return typed
    }

//returns px
fun Context.dimenAttr(@AttrRes attribute: Int): Int = TypedValue.complexToDimensionPixelSize(attr(attribute).data, resources.displayMetrics)

//returns color
fun Context.colorAttr(@AttrRes attribute: Int): Int {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            resources.getColor(attr(attribute).resourceId, ctx.theme)
        } else {
            resources.getColor(attr(attribute).resourceId)
        }
}

fun AnkoContext<*>.dimenAttr(@AttrRes attribute: Int): Int = ctx.dimenAttr(attribute)
fun AnkoContext<*>.colorAttr(@AttrRes attribute: Int): Int = ctx.colorAttr(attribute)
fun AnkoContext<*>.attribute(@AttrRes attribute: Int): TypedValue = ctx.attr(attribute)

fun View.dimenAttr(@AttrRes attribute: Int): Int = context.dimenAttr(attribute)
fun View.colorAttr(@AttrRes attribute: Int): Int = context.colorAttr(attribute)
fun View.attr(@AttrRes attribute: Int): TypedValue = context.attr(attribute)

fun Fragment.dimenAttr(@AttrRes attribute: Int): Int = activity.dimenAttr(attribute)
fun Fragment.colorAttr(@AttrRes attribute: Int): Int = activity.colorAttr(attribute)
fun Fragment.attr(@AttrRes attribute: Int): TypedValue = activity.attr(attribute)

fun ViewManager.appBarLayout(@StyleRes theme: Int): AppBarLayout = appBarLayout(theme, {})
inline fun ViewManager.appBarLayout(@StyleRes theme: Int, init: _AppBarLayout.() -> Unit): AppBarLayout {
    return ankoView(theme, `$$Anko$Factories$DesignViewGroup`.APP_BAR_LAYOUT) { init() }
}

fun ViewManager.toolbar(@StyleRes theme: Int): _Toolbar = toolbar(theme, {})
inline fun ViewManager.toolbar(@StyleRes theme: Int, init: _Toolbar.() -> Unit): _Toolbar {
    return ankoView(theme, { ctx: Context -> _Toolbar(ctx) }) { init() }
}

fun ViewManager.linearLayout(@StyleRes theme: Int): LinearLayout = linearLayout(theme, {})
inline fun ViewManager.linearLayout(@StyleRes theme: Int, init: _LinearLayout.() -> Unit): LinearLayout {
    return ankoView(theme, `$$Anko$Factories$Sdk15ViewGroup`.LINEAR_LAYOUT) { init() }
}

inline fun <T : View> ViewManager.ankoView(@StyleRes theme: Int, factory: (ctx: Context) -> T, init: T.() -> Unit): T {
    var ctx = AnkoInternals.getContext(this)
    if (theme != 0 && (ctx !is ContextThemeWrapper || ctx.themeResId != theme)) {
        // If the context isn't a ContextThemeWrapper, or it is but does not have
        // the same theme as we need, wrap it in a new wrapper
        ctx = ContextThemeWrapper(ctx, theme)
    }

    val view = factory(ctx)
    view.init()
    AnkoInternals.addView(this, view)
    return view
}

