package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.R.style.TextAppearance_AppCompat_Body1
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import org.jetbrains.anko.*

class ListItemComponent: AnkoComponent<ViewGroup> {

    companion object {
        val IDENTIFIER_ID = generateViewId()
        val CONTENT_ID = generateViewId()
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            orientation = HORIZONTAL

            textView {
                id = IDENTIFIER_ID
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(TextAppearance_AppCompat_Body1)
                } else {
                    setTextAppearance(this.context, TextAppearance_AppCompat_Body1)
                }
            }.lparams { margin = dimen(R.dimen.text_margin) }

            textView {
                id = CONTENT_ID
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(TextAppearance_AppCompat_Body1)
                } else {
                    setTextAppearance(this.context, TextAppearance_AppCompat_Body1)
                }
            }.lparams { margin = dimen(R.dimen.text_margin) }
        }
    }

}