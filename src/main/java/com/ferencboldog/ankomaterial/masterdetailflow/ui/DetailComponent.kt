package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.graphics.drawable.ColorDrawable
import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
import android.support.v4.view.GravityCompat.*
import android.view.Gravity
import android.view.Gravity.CENTER_VERTICAL
import android.view.View
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.R.style.*
import com.ferencboldog.ankomaterial.extensions.CollapsingToolbar.lparams
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import com.ferencboldog.ankomaterial.extensions.collapseModePin
import com.ferencboldog.ankomaterial.extensions.colorAttr
import com.ferencboldog.ankomaterial.extensions.dimenAttr
import com.ferencboldog.ankomaterial.masterdetailflow.DetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.support.v4.nestedScrollView

class DetailComponent: AnkoComponent<DetailActivity>, AnkoLogger {

    companion object {
        val TOOLBAR_LAYOUT_ID = generateViewId()
        val TOOLBAR_ID = generateViewId()
        val DETAIL_CONTAINER_ID = generateViewId()
        val FAB_ID = generateViewId()
    }

    override fun createView(ui: AnkoContext<DetailActivity>): View = with(ui) {
        coordinatorLayout {
            fitsSystemWindows = true

            appBarLayout(ThemeOverlay_AppCompat_Dark_ActionBar) {
                fitsSystemWindows = true

                collapsingToolbarLayout {
                    id = TOOLBAR_LAYOUT_ID
                    fitsSystemWindows = true

                    contentScrim = ColorDrawable(colorAttr(R.attr.colorPrimary))

                    toolbar(ThemeOverlay_AppCompat_Dark_ActionBar) {
                        id = TOOLBAR_ID
                        popupTheme = ThemeOverlay_AppCompat_Light
                    }.lparams(width = matchParent, height = dimenAttr(R.attr.actionBarSize), init = collapseModePin())

                }.lparams(width = matchParent, height = matchParent) {
                    scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED

                }
            }.lparams(width = matchParent, height = dimen(R.dimen.app_bar_height))

            nestedScrollView {
                id = DETAIL_CONTAINER_ID
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            floatingActionButton {
                id = FAB_ID
                imageResource = android.R.drawable.stat_notify_chat
            }.lparams {
                gravity = CENTER_VERTICAL or START
                margin = dimen(R.dimen.fab_margin)
                anchorId = DETAIL_CONTAINER_ID
                anchorGravity = Gravity.TOP or Gravity.END
            }
        }
    }

}