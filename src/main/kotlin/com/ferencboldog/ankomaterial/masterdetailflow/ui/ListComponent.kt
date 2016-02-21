package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.VERTICAL
import android.view.Gravity.BOTTOM
import android.view.Gravity.END
import android.view.View
import android.widget.LinearLayout.*
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.R.style.AppTheme_AppBarOverlay
import com.ferencboldog.ankomaterial.R.style.AppTheme_PopupOverlay
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import com.ferencboldog.ankomaterial.extensions.appBarLayout
import com.ferencboldog.ankomaterial.extensions.dimenAttr
import com.ferencboldog.ankomaterial.masterdetailflow.MasterListActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ListComponent: AnkoComponent<MasterListActivity>, AnkoLogger {

    companion object {
        val APP_BAR = generateViewId()
        val TOOLBAR = generateViewId()
        val FRAME_LAYOUT = generateViewId()
        val FAB = generateViewId()
        val LIST = generateViewId()
    }

    override fun createView(ui: AnkoContext<MasterListActivity>): View = with(ui) {
        coordinatorLayout {
            fitsSystemWindows = true

            appBarLayout(AppTheme_AppBarOverlay) {
                id = APP_BAR
                toolbar {
                    id = TOOLBAR
                    popupTheme = AppTheme_PopupOverlay
                }.lparams(width = matchParent, height = dimenAttr(R.attr.actionBarSize))
            }.lparams(width = matchParent)

            frameLayout {
                id = FRAME_LAYOUT

                val config = ctx.resources?.configuration
                debug("config.sw in dp: ${config?.screenWidthDp}")
                if(config != null && config.screenWidthDp >= 900){
                    ui.owner.twoPane = true
                    debug("using two pane")

                    linearLayout {
                        isBaselineAligned = false
                        orientation = HORIZONTAL
                        showDividers = SHOW_DIVIDER_MIDDLE

                        recyclerView {
                            id = LIST
                            layoutManager = LinearLayoutManager(ctx, VERTICAL, false)
                        }.lparams(width = dimen(R.dimen.item_width), height = matchParent){
                            horizontalMargin = dip(16)
                        }

                        frameLayout {
                            id = DetailComponent.DETAIL_CONTAINER
                        }.lparams(width = dip(0), height = matchParent){
                            weight = 3f
                        }

                    }.lparams(width = matchParent, height = matchParent){
                        horizontalMargin = dip(16)
                    }
                } else {
                    recyclerView {
                        id = LIST
                        layoutManager = LinearLayoutManager(ctx, VERTICAL, false)
                    }.lparams(width = dimen(R.dimen.item_width), height = matchParent){
                        horizontalMargin = dip(16)
                    }
                }

            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            floatingActionButton {
                id = FAB
                setImageResource(android.R.drawable.ic_dialog_email)
            }.lparams {
                margin = dimen(R.dimen.fab_margin)
                gravity = BOTTOM or END
            }
        }
    }

}