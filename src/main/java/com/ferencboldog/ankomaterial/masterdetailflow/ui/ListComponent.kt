package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearLayoutManager.VERTICAL
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Gravity.BOTTOM
import android.view.Gravity.END
import android.view.View
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.SHOW_DIVIDER_MIDDLE
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.R.style.AppTheme_AppBarOverlay
import com.ferencboldog.ankomaterial.R.style.AppTheme_PopupOverlay
import com.ferencboldog.ankomaterial.extensions.dimenAttr
import com.ferencboldog.ankomaterial.masterdetailflow.MasterListActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.debug
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.themedAppBarLayout
import org.jetbrains.anko.dimen
import org.jetbrains.anko.dip
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.horizontalMargin
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.margin
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ListComponent : AnkoComponent<MasterListActivity>, AnkoLogger {

    lateinit var toolbar: Toolbar
    lateinit var fab: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    override fun createView(ui: AnkoContext<MasterListActivity>): View = with(ui) {
        coordinatorLayout {
            fitsSystemWindows = true

            themedAppBarLayout(AppTheme_AppBarOverlay) {
                toolbar = toolbar {
                    popupTheme = AppTheme_PopupOverlay
                }.lparams(width = matchParent, height = dimenAttr(R.attr.actionBarSize))
            }.lparams(width = matchParent)

            frameLayout {
                val config = ctx.resources?.configuration
                debug("config.sw in dp: ${config?.screenWidthDp}")
                if (config != null && config.screenWidthDp >= 900) {
                    ui.owner.twoPane = true
                    debug("using two pane")

                    linearLayout {
                        isBaselineAligned = false
                        orientation = HORIZONTAL
                        showDividers = SHOW_DIVIDER_MIDDLE

                        recyclerView = recyclerView {
                            layoutManager = LinearLayoutManager(ctx, VERTICAL, false)
                        }.lparams(width = dimen(R.dimen.item_width), height = matchParent) {
                            horizontalMargin = dip(16)
                        }

                        frameLayout {
                            id = R.id.detail_container
                        }.lparams(width = dip(0), height = matchParent) {
                            weight = 3f
                        }

                    }.lparams(width = matchParent, height = matchParent) {
                        horizontalMargin = dip(16)
                    }
                } else {
                    recyclerView = recyclerView {
                        layoutManager = LinearLayoutManager(ctx, VERTICAL, false)
                    }.lparams(width = dimen(R.dimen.item_width), height = matchParent) {
                        horizontalMargin = dip(16)
                    }
                }

            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

            fab = floatingActionButton {
                imageResource = android.R.drawable.ic_dialog_email
            }.lparams {
                margin = dimen(R.dimen.fab_margin)
                gravity = BOTTOM or END
            }
        }
    }

}