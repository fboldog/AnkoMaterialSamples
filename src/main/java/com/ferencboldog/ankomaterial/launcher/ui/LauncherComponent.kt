package com.ferencboldog.ankomaterial.launcher.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ferencboldog.ankomaterial.extensions.FrameLayout.lparams
import com.ferencboldog.ankomaterial.launcher.LauncherActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

class LauncherComponent : AnkoComponent<LauncherActivity> {

    lateinit var list: RecyclerView

    override fun createView(ui: AnkoContext<LauncherActivity>): View = with(ui) {
        list = recyclerView {
            layoutManager = LinearLayoutManager(ctx)
        }.lparams(width = matchParent, height = matchParent)
        list
    }
}