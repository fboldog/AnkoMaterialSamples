package com.ferencboldog.ankomaterial.launcher.ui

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import com.ferencboldog.ankomaterial.extensions.lparams
import com.ferencboldog.ankomaterial.launcher.LauncherActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class LauncherComponent: AnkoComponent<LauncherActivity> {

    companion object {
        val LIST_ID = generateViewId()
    }

    override fun createView(ui: AnkoContext<LauncherActivity>): View = with(ui) {
            recyclerView {
                visibility = View.GONE
                id = LIST_ID
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(width = matchParent, height = matchParent)
    }

}