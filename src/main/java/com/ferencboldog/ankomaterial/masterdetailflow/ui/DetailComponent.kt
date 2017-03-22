package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.graphics.drawable.ColorDrawable
import android.support.design.widget.AppBarLayout
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
import android.support.design.widget.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.GravityCompat.START
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.Gravity.CENTER_VERTICAL
import android.view.View
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.R.style.ThemeOverlay_AppCompat_Dark_ActionBar
import com.ferencboldog.ankomaterial.R.style.ThemeOverlay_AppCompat_Light
import com.ferencboldog.ankomaterial.extensions.CollapsingToolbar.lparams
import com.ferencboldog.ankomaterial.extensions.collapseModePin
import com.ferencboldog.ankomaterial.extensions.colorAttr
import com.ferencboldog.ankomaterial.extensions.dimenAttr
import com.ferencboldog.ankomaterial.masterdetailflow.DetailActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.dimen
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.margin
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.nestedScrollView

class DetailComponent : AnkoComponent<DetailActivity>, AnkoLogger {

    lateinit var toolbar: Toolbar
    lateinit var fab: FloatingActionButton

    override fun createView(ui: AnkoContext<DetailActivity>): View = with(ui) {
        coordinatorLayout {
            fitsSystemWindows = true

            themedAppBarLayout(ThemeOverlay_AppCompat_Dark_ActionBar) {
                fitsSystemWindows = true

                collapsingToolbarLayout {
                    id = R.id.detail_collapsing_toolbar
                    fitsSystemWindows = true

                    contentScrim = ColorDrawable(colorAttr(R.attr.colorPrimary))

                    toolbar = themedToolbar(ThemeOverlay_AppCompat_Dark_ActionBar) {
                        popupTheme = ThemeOverlay_AppCompat_Light
                    }.lparams(width = matchParent, height = dimenAttr(R.attr.actionBarSize), init = collapseModePin())

                }.lparams(width = matchParent, height = matchParent) {
                    scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                }
            }.lparams(width = matchParent, height = dimen(R.dimen.app_bar_height))

            nestedScrollView {
                id = R.id.detail_container
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            fab = floatingActionButton {
                imageResource = android.R.drawable.stat_notify_chat
            }.lparams {
                gravity = CENTER_VERTICAL or START
                margin = dimen(R.dimen.fab_margin)
                anchorId = R.id.detail_container
                anchorGravity = Gravity.TOP or Gravity.END
            }
        }
    }

}