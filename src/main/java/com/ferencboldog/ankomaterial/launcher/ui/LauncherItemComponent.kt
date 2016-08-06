package com.ferencboldog.ankomaterial.launcher.ui

import android.content.Context
import android.view.Gravity.*
import android.view.View
import android.widget.Button
import com.ferencboldog.ankomaterial.extensions.FrameLayout.lparams
import org.jetbrains.anko.*

class LauncherItemComponent: AnkoComponent<Context> {
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