package com.ferencboldog.ankovskotlinx.ui

import android.R
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowInsets
import com.ferencboldog.ankovskotlinx.*
import com.ferencboldog.ankovskotlinx.extensions.appBarLayout
import com.ferencboldog.ankovskotlinx.extensions.colorAttr
import com.ferencboldog.ankovskotlinx.extensions.dimenAttr
import com.ferencboldog.ankovskotlinx.extensions.snackbar
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.*
import org.jetbrains.anko.support.v4.drawerLayout


class MainAnkoUI: AnkoComponent<AnkoActivity> {

    companion object {
        val TOOLBAR_ID = View.generateViewId()
        val DRAWER_ID = View.generateViewId()
    }

    override fun createView(ui: AnkoContext<AnkoActivity>): View = with(ui) {
        drawerLayout {
            id = DRAWER_ID
            fitsSystemWindows = true
            setStatusBarBackgroundColor(colorAttr(com.ferencboldog.ankovskotlinx.R.attr.colorPrimaryDark))
            setOnApplyWindowInsetsListener({ view: View, insets: WindowInsets ->
                Log.d("DRAWER", "insets: ${insets.systemWindowInsetTop}")
                this.setChildInsets(insets, insets.systemWindowInsetTop > 0)
                return@setOnApplyWindowInsetsListener insets.consumeSystemWindowInsets()
            })

            coordinatorLayout {
                fitsSystemWindows = true

                appBarLayout(com.ferencboldog.ankovskotlinx.R.style.AppTheme_AppBarOverlay) {
                    toolbar {
                        id = TOOLBAR_ID
                        backgroundColor = colorAttr(R.attr.colorPrimary)
                        popupTheme = com.ferencboldog.ankovskotlinx.R.style.AppTheme_PopupOverlay
                    }.lparams(width = matchParent, height = ui.dimenAttr(R.attr.actionBarSize))
                }.lparams(width = matchParent)

                relativeLayout {
                    topPadding = dimen(com.ferencboldog.ankovskotlinx.R.dimen.activity_vertical_margin)
                    bottomPadding = dimen(com.ferencboldog.ankovskotlinx.R.dimen.activity_vertical_margin)
                    leftPadding = dimen(com.ferencboldog.ankovskotlinx.R.dimen.activity_horizontal_margin)
                    rightPadding = dimen(com.ferencboldog.ankovskotlinx.R.dimen.activity_horizontal_margin)
                    textView("Hello World!")
                }.lparams(width = matchParent, height = matchParent) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }

                floatingActionButton {
                    imageResource = R.drawable.ic_dialog_email
                    onClick {
                        snackbar("Replace with your own action", Snackbar.LENGTH_LONG) {
                            setAction("Action") { toast("Clicked Snack") }
                        }
                    }
                }.lparams {
                    margin = dimen(com.ferencboldog.ankovskotlinx.R.dimen.fab_margin)
                    gravity = Gravity.BOTTOM or GravityCompat.END
                }
            }.lparams(width = matchParent, height = matchParent)

            navigationView {
                fitsSystemWindows = true
                val headerContext = AnkoContext.create(ctx, this);
                val headerView = NavigationHeader().createView(headerContext).lparams(width = matchParent, height = dimen(com.ferencboldog.ankovskotlinx.R.dimen.nav_header_height))
                addHeaderView(headerView)
                inflateMenu(com.ferencboldog.ankovskotlinx.R.menu.activity_main_drawer)
                setNavigationItemSelectedListener(ui.owner)
            }.lparams(height = matchParent) {
                gravity = GravityCompat.START
            }
        }
    }
}
