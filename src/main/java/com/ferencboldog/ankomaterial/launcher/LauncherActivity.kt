package com.ferencboldog.ankomaterial.launcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ferencboldog.ankomaterial.launcher.ui.LauncherComponent
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

    val ui = LauncherComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = LauncherComponent()
        ui.setContentView(this@LauncherActivity)
        ui.list.adapter = adapter
    }
}