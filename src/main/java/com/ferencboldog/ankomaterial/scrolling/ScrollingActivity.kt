package com.ferencboldog.ankomaterial.scrolling

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.extensions.snackbar
import com.ferencboldog.ankomaterial.scrolling.ui.ScrollingComponent
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.setContentView

class ScrollingActivity : AppCompatActivity() {
    val ui = ScrollingComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)

        setSupportActionBar(ui.toolbar)

        ui.fab.onClick {
            snackbar(view = it!!, text = "Replace with your own action") {
                setAction("Action", null)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}