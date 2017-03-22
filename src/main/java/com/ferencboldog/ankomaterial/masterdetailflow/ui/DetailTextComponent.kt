package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.view.View
import android.widget.TextView
import com.ferencboldog.ankomaterial.extensions.FrameLayout.lparams
import com.ferencboldog.ankomaterial.masterdetailflow.DetailFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.padding
import org.jetbrains.anko.textView

class DetailTextComponent : AnkoComponent<DetailFragment> {

    lateinit var text: TextView

    override fun createView(ui: AnkoContext<DetailFragment>): View = with(ui) {
        text = textView {
            padding = dip(16)
            setTextIsSelectable(true)
        }.lparams(width = matchParent, height = matchParent)
        text
    }

}