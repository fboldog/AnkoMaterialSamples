package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.view.View
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import com.ferencboldog.ankomaterial.extensions.FrameLayout.lparams
import com.ferencboldog.ankomaterial.masterdetailflow.DetailFragment
import org.jetbrains.anko.*

class DetailTextComponent: AnkoComponent<DetailFragment> {
    companion object {
        val TEXT_ID = generateViewId()
    }
    override fun createView(ui: AnkoContext<DetailFragment>): View = with(ui){
        textView {
            id = TEXT_ID
            padding = dip(16)
            setTextIsSelectable(true)
        }.lparams(width = matchParent, height = matchParent)
    }

}