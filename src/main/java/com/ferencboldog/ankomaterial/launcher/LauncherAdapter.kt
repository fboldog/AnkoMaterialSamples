package com.ferencboldog.ankomaterial.launcher

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ferencboldog.ankomaterial.launcher.ui.LauncherItemComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.onClick

class LauncherAdapter(val items: List<Pair<String, Class<out Activity>>>) : RecyclerView.Adapter<LauncherViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LauncherViewHolder? {
        val ui = LauncherItemComponent()
        var adapterView: View? = null
        with(ui) {
            adapterView = createView(AnkoContext.create(viewGroup.context))
            button.onClick { view ->
                view!!.context.startActivity(Intent(view.context, view.tag as Class<*>))
            }
        }
        return LauncherViewHolder(adapterView!!, ui.button)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: LauncherViewHolder, position: Int) = with(viewHolder.button) {
        text = items[position].first
        tag = items[position].second
    }
}

class LauncherViewHolder(view: View, val button: Button) : RecyclerView.ViewHolder(view)