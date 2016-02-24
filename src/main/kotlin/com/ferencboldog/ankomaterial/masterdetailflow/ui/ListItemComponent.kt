package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.extensions.AnkoViewCompat.generateViewId
import com.ferencboldog.ankomaterial.extensions.attr
import org.jetbrains.anko.*

class ListItemComponent: AnkoComponent<ViewGroup> {

    companion object {
        val IDENTIFIER_ID = generateViewId()
        val CONTENT_ID = generateViewId()
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            orientation = HORIZONTAL
            val textAppearanceRes = attr(R.attr.textAppearanceListItem).resourceId

            textView {
                id = IDENTIFIER_ID
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(textAppearanceRes)
                } else {
                    @Suppress("DEPRECATION")
                    setTextAppearance(this.context, textAppearanceRes)
                }
            }.lparams { margin = dimen(R.dimen.text_margin) }

            textView {
                id = CONTENT_ID
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(textAppearanceRes)
                } else {
                    @Suppress("DEPRECATION")
                    setTextAppearance(this.context, textAppearanceRes)
                }
            }.lparams { margin = dimen(R.dimen.text_margin) }
        }
    }

}