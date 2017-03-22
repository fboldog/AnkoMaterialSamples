package com.ferencboldog.ankomaterial.masterdetailflow.ui

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.extensions.attr
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.dimen
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.margin
import org.jetbrains.anko.textView

class ListItemComponent : AnkoComponent<ViewGroup> {

    lateinit var identifier: TextView
    lateinit var content: TextView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            orientation = HORIZONTAL
            val textAppearanceRes = attr(R.attr.textAppearanceListItem).resourceId

            identifier = textView {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(textAppearanceRes)
                } else {
                    @Suppress("DEPRECATION")
                    setTextAppearance(this.context, textAppearanceRes)
                }
            }.lparams { margin = dimen(R.dimen.text_margin) }

            content = textView {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(textAppearanceRes)
                } else {
                    @Suppress("DEPRECATION")
                    setTextAppearance(this.context, textAppearanceRes)
                }
            }.lparams { margin = dimen(R.dimen.text_margin) }
        }
    }
}