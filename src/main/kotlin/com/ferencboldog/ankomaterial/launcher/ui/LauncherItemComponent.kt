package com.ferencboldog.ankomaterial.launcher.ui

import android.content.Context
import android.view.Gravity.*
import android.view.View
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import com.ferencboldog.ankomaterial.extensions.lparams
import org.jetbrains.anko.*

class LauncherItemComponent: AnkoComponent<Context> {
    companion object {
        val BUTTON = generateViewId()
    }
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        frameLayout {
            button {
                id = BUTTON
            }.lparams {
                gravity = CENTER
                verticalMargin = dip(5)
            }
        }.lparams { width = matchParent }
    }

}