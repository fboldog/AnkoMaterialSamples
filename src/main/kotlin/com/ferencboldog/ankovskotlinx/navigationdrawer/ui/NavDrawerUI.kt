package com.ferencboldog.ankovskotlinx.navigationdrawer.ui

import android.R
import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.view.Gravity
import android.view.View
import android.view.WindowInsets
import com.ferencboldog.ankovskotlinx.*
import com.ferencboldog.ankovskotlinx.extensions.*
import com.ferencboldog.ankovskotlinx.navigationdrawer.NavDrawerActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.*
import org.jetbrains.anko.support.v4.drawerLayout


class NavDrawerUI : AnkoComponent<NavDrawerActivity>, AnkoLogger {

    companion object {
        val TOOLBAR_ID = AnkoViewCompat.compatGenerateViewId()
        val DRAWER_ID = AnkoViewCompat.compatGenerateViewId()
    }

    override fun createView(ui: AnkoContext<NavDrawerActivity>): View = with(ui) {
        drawerLayout {
            id = DRAWER_ID
            fitsSystemWindows = true
            setStatusBarBackgroundColor(colorAttr(com.ferencboldog.ankovskotlinx.R.attr.colorPrimaryDark))

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setOnApplyWindowInsetsListener({ view: View, insets: WindowInsets ->
                    debug("insets: ${insets.systemWindowInsetTop}")
                    this.setChildInsets(insets, insets.systemWindowInsetTop > 0)
                    return@setOnApplyWindowInsetsListener insets.consumeSystemWindowInsets()
                })
            }

            coordinatorLayout {
                fitsSystemWindows = true

                appBarLayout(com.ferencboldog.ankovskotlinx.R.style.AppTheme_AppBarOverlay) {
                    toolbar {
                        id = TOOLBAR_ID
                        backgroundColor = colorAttr(R.attr.colorPrimary)
                        popupTheme = com.ferencboldog.ankovskotlinx.R.style.AppTheme_PopupOverlay
                    }.lparams(width = matchParent, height = dimenAttr(R.attr.actionBarSize))
                }.lparams(width = matchParent)

                relativeLayout {
                    val verticalMargin = dimen(com.ferencboldog.ankovskotlinx.R.dimen.activity_vertical_margin)
                    val horizontalMargin = dimen(com.ferencboldog.ankovskotlinx.R.dimen.activity_horizontal_margin)
                    topPadding = verticalMargin
                    rightPadding = horizontalMargin
                    bottomPadding = verticalMargin
                    leftPadding = horizontalMargin

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
                val headerView = NavHeader()
                        .createView(headerContext)
                        .lparams(width = matchParent, height = dimen(com.ferencboldog.ankovskotlinx.R.dimen.nav_header_height))
                addHeaderView(headerView)
                inflateMenu(com.ferencboldog.ankovskotlinx.R.menu.activity_main_drawer)
                if(!isInEditMode) {
                    setNavigationItemSelectedListener(ui.owner)
                }
            }.lparams(height = matchParent) {
                gravity = GravityCompat.START
            }

            if(isInEditMode){
                openDrawer(GravityCompat.START)
            }
        }
    }
}
