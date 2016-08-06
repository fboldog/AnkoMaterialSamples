package com.ferencboldog.ankomaterial.scrolling.ui

import android.graphics.drawable.ColorDrawable
import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.GravityCompat.END
import android.support.v7.widget.Toolbar
import android.view.Gravity.BOTTOM
import android.view.View
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.extensions.CollapsingToolbar.lparams
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import com.ferencboldog.ankomaterial.extensions.collapseModePin
import com.ferencboldog.ankomaterial.extensions.colorAttr
import com.ferencboldog.ankomaterial.extensions.dimenAttr
import com.ferencboldog.ankomaterial.scrolling.ScrollingActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.*
import org.jetbrains.anko.support.v4.nestedScrollView

class ScrollingComponent: AnkoComponent<ScrollingActivity> {

    companion object {
        val APP_BAR_ID = generateViewId()
        val TOOLBAR_LAYOUT_ID = generateViewId()
        val TOOLBAR_ID = generateViewId()
        val FAB_ID = generateViewId()
    }

    lateinit var toolbar: Toolbar
    lateinit var fab: FloatingActionButton

    override fun createView(ui: AnkoContext<ScrollingActivity>): View  = with(ui) {
        coordinatorLayout {
            fitsSystemWindows = true
            setStatusBarBackgroundColor(colorAttr(R.attr.colorPrimaryDark))

//            setOnApplyWindowInsetsListener({view, insets ->
//                if(view is CoordinatorLayout){
//                    view.apply {
//                        setStatusBarBackgroundColor(colorAttr(R.attr.colorPrimaryDark))
//                        systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        val params = layoutParams as FrameLayout.LayoutParams
//                        params.topMargin = insets.systemWindowInsetTop
//                        layoutParams = params
//                        setWillNotDraw(true)
//                        invalidate()
//                        forceLayout()
//                    }
//                }
//
//                return@setOnApplyWindowInsetsListener insets.consumeSystemWindowInsets()
//            })
//

            appBarLayout(R.style.AppTheme_AppBarOverlay) {
                id = APP_BAR_ID
                fitsSystemWindows = true

                collapsingToolbarLayout {
                    id = TOOLBAR_LAYOUT_ID
                    fitsSystemWindows = true

                    contentScrim = ColorDrawable(colorAttr(R.attr.colorPrimary))

                    toolbar = toolbar {
                        id = TOOLBAR_ID
                        popupTheme = R.style.AppTheme_PopupOverlay
                    }.lparams(width = matchParent, height = dimenAttr(R.attr.actionBarSize), init = collapseModePin())

                }.lparams(width = matchParent, height = matchParent) {
                    scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }

            }.lparams(width = matchParent, height = dimen(R.dimen.app_bar_height))

            nestedScrollView {
                textView(text = R.string.scrolling_large_text).lparams {
                    margin = dimen(R.dimen.text_margin)
                }

            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            fab = floatingActionButton {
                id = FAB_ID
                imageResource = android.R.drawable.ic_dialog_email

            }.lparams {
                anchorId = APP_BAR_ID
                anchorGravity = BOTTOM or END
                margin = dimen(R.dimen.fab_margin)
            }

        }.lparams(width = matchParent, height = matchParent)
    }
}