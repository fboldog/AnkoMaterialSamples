package com.ferencboldog.ankovskotlinx.navigationdrawer

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.ferencboldog.ankovskotlinx.R
import com.ferencboldog.ankovskotlinx.navigationdrawer.ui.NavDrawerUI

import com.ferencboldog.ankovskotlinx.navigationdrawer.ui.NavDrawerUI.Companion.DRAWER_ID
import com.ferencboldog.ankovskotlinx.navigationdrawer.ui.NavDrawerUI.Companion.TOOLBAR_ID
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

open class NavDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger {

    lateinit var drawer: DrawerLayout
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavDrawerUI().setContentView(this)

        drawer = find<DrawerLayout>(DRAWER_ID)
        toolbar = find<Toolbar>(TOOLBAR_ID)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)

        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
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
        return if(item.itemId == R.id.action_settings) {
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
}