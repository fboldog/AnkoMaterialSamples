package com.ferencboldog.ankomaterial.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.ferencboldog.ankomaterial.launcher.ui.LauncherComponent
import com.ferencboldog.ankomaterial.launcher.ui.LauncherComponent.Companion.LIST_ID
import com.ferencboldog.ankomaterial.masterdetailflow.MasterListActivity
import com.ferencboldog.ankomaterial.navigationdrawer.NavDrawerActivity
import com.ferencboldog.ankomaterial.scrolling.ScrollingActivity
import org.jetbrains.anko.*

class LauncherActivity: AppCompatActivity() {
    val adapter = LauncherAdapter(
            listOf(
                    "Navigation Drawer" to NavDrawerActivity::class.java,
                    "Master-Detail Flow" to MasterListActivity::class.java,
                    "Scrolling" to ScrollingActivity::class.java
            )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LauncherComponent().setContentView(this)

        val list = find<RecyclerView>(LIST_ID)
        list.adapter = adapter
    }
}