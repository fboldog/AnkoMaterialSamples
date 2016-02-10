package com.ferencboldog.ankovskotlinx.extensions

import android.content.Context
import android.os.Build
import android.support.annotation.AttrRes
import android.support.annotation.StyleRes
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v7.view.ContextThemeWrapper
import android.util.TypedValue
import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.`$$Anko$Factories$Sdk15ViewGroup`
import org.jetbrains.anko.design._AppBarLayout
import org.jetbrains.anko.design.`$$Anko$Factories$DesignViewGroup`
import org.jetbrains.anko.internals.AnkoInternals
import org.jetbrains.anko.resources


fun View.snackbar(text: CharSequence, length: Int = Snackbar.LENGTH_LONG, snackbar: Snackbar.() -> Unit) = Snackbar.make(this, text, length).apply { snackbar() }.show()

fun AnkoContext<*>.attribute(@AttrRes attr : Int) : TypedValue {
    var ret = TypedValue()
    ctx.theme.resolveAttribute(attr, ret, true)
    return ret
}

fun AnkoContext<*>.dimenAttr(@AttrRes attr : Int) : Int    {
    return TypedValue.complexToDimensionPixelSize(attribute(attr).data, resources.displayMetrics)
}

fun AnkoContext<*>.colorAttr(@AttrRes attr: Int): Int {
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        resources.getColor(attribute(attr).resourceId, ctx.theme)
    } else {
        resources.getColor(attribute(attr).resourceId)
    }
}

inline fun ViewManager.appBarLayout(@StyleRes theme: Int): AppBarLayout = appBarLayout(theme, {})
inline fun ViewManager.appBarLayout(@StyleRes theme: Int, init: _AppBarLayout.() -> Unit): AppBarLayout {
    return ankoView(theme, `$$Anko$Factories$DesignViewGroup`.APP_BAR_LAYOUT) { init() }
}
inline fun ViewManager.linearLayout(@StyleRes theme: Int): LinearLayout = linearLayout(theme, {})
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
