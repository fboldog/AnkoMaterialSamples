package com.ferencboldog.ankomaterial.navigationdrawer

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.extensions.snackbar
import com.ferencboldog.ankomaterial.navigationdrawer.ui.NavDrawerComponent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

open class NavDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger {

    val ui = NavDrawerComponent()
    lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)

        drawer = ui.drawer

        setSupportActionBar(ui.toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer, ui.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> debug("-> camera")
            R.id.nav_gallery -> debug("-> gallery")
            R.id.nav_slideshow -> debug("-> slideshow")
            R.id.nav_manage -> debug("-> manage")
            R.id.nav_share -> debug("-> share")
            R.id.nav_send -> debug("-> send")
        }

        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun fabOnClick(view: View) {
        snackbar(view, "Replace with your own action", Snackbar.LENGTH_LONG) {
            setAction("Action") {
                toast("Clicked Snack")
            }
        }
    }
}