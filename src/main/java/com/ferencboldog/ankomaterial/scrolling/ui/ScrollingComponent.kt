package com.ferencboldog.ankomaterial.scrolling.ui

import android.graphics.drawable.ColorDrawable
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.GravityCompat.END
import android.support.v7.widget.Toolbar
import android.view.Gravity.BOTTOM
import android.view.View
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.extensions.CollapsingToolbar.lparams
import com.ferencboldog.ankomaterial.extensions.collapseModePin
import com.ferencboldog.ankomaterial.extensions.colorAttr
import com.ferencboldog.ankomaterial.extensions.dimenAttr
import com.ferencboldog.ankomaterial.extensions.lParamsDefault
import com.ferencboldog.ankomaterial.extensions.lParamsWithScrollFlags
import com.ferencboldog.ankomaterial.scrolling.ScrollingActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.dimen
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.margin
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.nestedScrollView
import org.jetbrains.anko.textView

class ScrollingComponent : AnkoComponent<ScrollingActivity> {

    lateinit var toolbar: Toolbar
    lateinit var fab: FloatingActionButton

    override fun createView(ui: AnkoContext<ScrollingActivity>): View = with(ui) {
        coordinatorLayout {
            fitsSystemWindows = true
            setStatusBarBackgroundColor(colorAttr(R.attr.colorPrimaryDark))

            themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {
                id = R.id.scrolling_component_appbar
                fitsSystemWindows = true

                collapsingToolbarLayout {
                    fitsSystemWindows = true

                    contentScrim = ColorDrawable(colorAttr(R.attr.colorPrimary))

                    toolbar = toolbar {
                        popupTheme = R.style.AppTheme_PopupOverlay
                    }.lparams(width = matchParent, height = dimenAttr(R.attr.actionBarSize), init = collapseModePin())

                }.lparams(width = matchParent, height = matchParent, init = lParamsWithScrollFlags())

            }.lparams(width = matchParent, height = dimen(R.dimen.app_bar_height))

            nestedScrollView {
                textView(text = R.string.scrolling_large_text).lparams {
                    margin = dimen(R.dimen.text_margin)
                }

            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            fab = floatingActionButton {
                imageResource = android.R.drawable.ic_dialog_email

            }.lparams {
                anchorId = R.id.scrolling_component_appbar
                anchorGravity = BOTTOM or END
                margin = dimen(R.dimen.fab_margin)
            }

        }.lparams(width = matchParent, height = matchParent, init = lParamsDefault())
    }
}