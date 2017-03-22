package com.ferencboldog.ankomaterial.navigationdrawer.ui

import android.support.design.widget.AppBarLayout
import android.support.v4.view.GravityCompat.END
import android.support.v4.view.GravityCompat.START
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.Gravity.BOTTOM
import android.view.View
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.R.style.AppTheme_PopupOverlay
import com.ferencboldog.ankomaterial.extensions.colorAttr
import com.ferencboldog.ankomaterial.extensions.dimenAttr
import com.ferencboldog.ankomaterial.navigationdrawer.NavDrawerActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.dimen
import org.jetbrains.anko.horizontalPadding
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.margin
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.onClick
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalPadding

class NavDrawerComponent : AnkoComponent<NavDrawerActivity>, AnkoLogger {

    lateinit var drawer: DrawerLayout
    lateinit var toolbar: Toolbar

    override fun createView(ui: AnkoContext<NavDrawerActivity>): View = with(ui) {
        drawer = drawerLayout {
            fitsSystemWindows = true

            coordinatorLayout {
                fitsSystemWindows = true

                themedAppBarLayout(R.style.AppTheme_AppBarOverlay) {
                    toolbar = toolbar {
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
                    onClick { view -> ui.owner.fabOnClick(view!!) }
                }.lparams {
                    margin = dimen(R.dimen.fab_margin)
                    gravity = BOTTOM or END
                }
            }.lparams(width = matchParent, height = matchParent)

            navigationView {
                fitsSystemWindows = true
                val headerContext = AnkoContext.create(ctx, this)
                val headerView = NavHeaderComponent()
                    .createView(headerContext)
                    //FIXME check this layout parameters, required or not
//                  .lparams(width = matchParent, height = dimen(R.dimen.nav_header_height))
                addHeaderView(headerView)
                inflateMenu(R.menu.activity_main_drawer)
                if (!isInEditMode) {
                    setNavigationItemSelectedListener(ui.owner)
                }
            }.lparams(height = matchParent) {
                gravity = START
            }

            if (isInEditMode) {
                openDrawer(START)
            }
        }
        drawer
    }
}
