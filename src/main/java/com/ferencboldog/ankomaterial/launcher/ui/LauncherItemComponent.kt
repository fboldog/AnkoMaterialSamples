package com.ferencboldog.ankomaterial.launcher.ui

import android.content.Context
import android.view.Gravity.CENTER
import android.view.View
import android.widget.Button
import com.ferencboldog.ankomaterial.extensions.FrameLayout.lparams
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.button
import org.jetbrains.anko.dip
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalMargin

class LauncherItemComponent : AnkoComponent<Context> {
    lateinit var button: Button

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        frameLayout {
            button = button().lparams {
                gravity = CENTER
                verticalMargin = dip(5)
            }
        }.lparams { width = matchParent }
    }
}