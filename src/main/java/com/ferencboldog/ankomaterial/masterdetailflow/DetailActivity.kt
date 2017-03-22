package com.ferencboldog.ankomaterial.masterdetailflow

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.extensions.snackbar
import com.ferencboldog.ankomaterial.masterdetailflow.ui.DetailComponent
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.onClick

import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.withArguments

class DetailActivity : AppCompatActivity() {

    lateinit var ui: DetailComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = DetailComponent()
        ui.setContentView(this)

        setSupportActionBar(ui.toolbar)

        ui.fab.onClick {
            view ->
            snackbar(view!!, "Replace with your own detail action", Snackbar.LENGTH_LONG) {
                setAction("Action", null).show()
            }
        }

        // Show the Up button in the action bar.
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = DetailFragment().withArguments(
                DetailFragment.ARG_ITEM_ID to intent.getStringExtra(DetailFragment.ARG_ITEM_ID)
            )
            supportFragmentManager.beginTransaction().add(R.id.detail_container, fragment).commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navigateUpTo(intentFor<MasterListActivity>())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}