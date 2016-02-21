package com.ferencboldog.ankomaterial.launcher

import android.app.Activity
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ferencboldog.ankomaterial.extensions.lparams
import com.ferencboldog.ankomaterial.launcher.ui.LauncherComponent
import com.ferencboldog.ankomaterial.launcher.ui.LauncherComponent.Companion.LIST_ID
import com.ferencboldog.ankomaterial.launcher.ui.LauncherItemComponent
import com.ferencboldog.ankomaterial.masterdetailflow.MasterListActivity
import com.ferencboldog.ankomaterial.navigationdrawer.NavDrawerActivity
import org.jetbrains.anko.*

class LauncherActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LauncherComponent().setContentView(this)

        val list = find<RecyclerView>(LIST_ID)

        list.adapter = Adapter(
                listOf(
                        "Navigation Drawer" to NavDrawerActivity::class.java,
                        "Master-Detail Flow" to MasterListActivity::class.java
                )
        )
    }

    inner class Adapter(val items: List<Pair<String, Class<out Activity>>>): RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder? {
            val view = LauncherItemComponent().createView(AnkoContext.create(viewGroup.context))
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.button.apply {
                text = items[position].first
                tag = items[position].second
            }
        }

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val button = view.find<Button>(LauncherItemComponent.BUTTON)

        init {
            button.onClick { view ->
                val cls = view?.tag as Class<out Activity>
                startActivity(Intent(view?.context, cls))
            }
        }
    }
}