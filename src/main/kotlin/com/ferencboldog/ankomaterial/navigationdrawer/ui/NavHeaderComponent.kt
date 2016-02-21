package com.ferencboldog.ankomaterial.navigationdrawer.ui

import android.os.Build
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.extensions.linearLayout
import org.jetbrains.anko.*

class NavHeaderComponent : AnkoComponent<NavigationView> {
    override fun createView(ui: AnkoContext<NavigationView>): View = with(ui) {
        linearLayout(R.style.ThemeOverlay_AppCompat_Dark) {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.BOTTOM

            topPadding = dimen(R.dimen.activity_vertical_margin)
            bottomPadding = dimen(R.dimen.activity_vertical_margin)
            leftPadding = dimen(R.dimen.activity_horizontal_margin)
            rightPadding = dimen(R.dimen.activity_horizontal_margin)

            backgroundResource = R.drawable.side_nav_bar
            imageView(android.R.drawable.sym_def_app_icon) {
                topPadding = dimen(R.dimen.nav_header_vertical_spacing)
            }.lparams {
                gravity = GravityCompat.START
            }

            textView("Android Studio"){
                topPadding = dimen(R.dimen.nav_header_vertical_spacing)
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(R.style.TextAppearance_AppCompat_Body1)
                } else {
                    setTextAppearance(this.context, R.style.TextAppearance_AppCompat_Body1)
                }
            }

            textView("android.studio@android.com")
        }
    }
}
