package com.ferencboldog.ankomaterial.navigationdrawer.ui

import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat.*
import android.view.Gravity.BOTTOM
import android.view.View
import android.view.WindowInsets
import com.ferencboldog.ankomaterial.*
import com.ferencboldog.ankomaterial.R.style.AppTheme_PopupOverlay
import com.ferencboldog.ankomaterial.extensions.*
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import com.ferencboldog.ankomaterial.navigationdrawer.NavDrawerActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.*
import org.jetbrains.anko.support.v4.drawerLayout


class NavDrawerComponent : AnkoComponent<NavDrawerActivity>, AnkoLogger {

    companion object {
        val TOOLBAR_ID = generateViewId()
        val DRAWER_ID = generateViewId()
    }

    override fun createView(ui: AnkoContext<NavDrawerActivity>): View = with(ui) {
        drawerLayout {
            id = DRAWER_ID
            fitsSystemWindows = true
            setStatusBarBackgroundColor(colorAttr(R.attr.colorPrimaryDark))

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setOnApplyWindowInsetsListener({ view: View, insets: WindowInsets ->
                    debug("insets: ${insets.systemWindowInsetTop}")
                    this.setChildInsets(insets, insets.systemWindowInsetTop > 0)
                    return@setOnApplyWindowInsetsListener insets.consumeSystemWindowInsets()
                })
            }

            coordinatorLayout {
                fitsSystemWindows = true

                appBarLayout(R.style.AppTheme_AppBarOverlay) {
                    toolbar {
                        id = TOOLBAR_ID
                        backgroundColor = colorAttr(R.attr.colorPrimary)
                        popupTheme = AppTheme_PopupOverlay
                    }.lparams(width = matchParent, height = dimenAttr(R.attr.actionBarSize))
                }.lparams(width = matchParent)

                relativeLayout {
                    horizontalPadding = dimen(R.dimen.activity_horizontal_margin)
                    verticalPadding = dimen(R.dimen.activity_vertical_margin)

                    textView("Hello World!")
                }.lparams(width = matchParent, height = matchParent) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }

                floatingActionButton {
                    imageResource = android.R.drawable.ic_dialog_email
                    onClick {
                        snackbar("Replace with your own action", Snackbar.LENGTH_LONG) {
                            setAction("Action") { toast("Clicked Snack") }
                        }
                    }
                }.lparams {
                    margin = dimen(R.dimen.fab_margin)
                    gravity = BOTTOM or END
                }
            }.lparams(width = matchParent, height = matchParent)

            navigationView {
                fitsSystemWindows = true
                val headerContext = AnkoContext.create(ctx, this);
                val headerView = NavHeaderComponent()
                        .createView(headerContext)
                        .lparams(width = matchParent, height = dimen(R.dimen.nav_header_height))
                addHeaderView(headerView)
                inflateMenu(R.menu.activity_main_drawer)
                if(!isInEditMode) {
                    setNavigationItemSelectedListener(ui.owner)
                }
            }.lparams(height = matchParent) {
                gravity = START
            }

            if(isInEditMode){
                openDrawer(START)
            }
        }
    }
}
