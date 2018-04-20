package com.ferencboldog.ankomaterial.masterdetailflow

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.extensions.snackbar
import com.ferencboldog.ankomaterial.masterdetailflow.ui.ListComponent
import com.ferencboldog.ankomaterial.masterdetailflow.ui.ListItemComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.withArguments

/**
 * An activity representing a list of Object Kind Plural. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [DetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MasterListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    var twoPane: Boolean = false

    val ui = ListComponent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)

        setSupportActionBar(ui.toolbar)
        ui.toolbar.title = title

        ui.fab.onClick {
            view ->
            snackbar(view!!, "Replace with your own action", Snackbar.LENGTH_LONG) {
                setAction("Action", null)
            }
        }

        setupRecyclerView(ui.recyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(DummyContent.ITEMS)
    }

    inner class SimpleItemRecyclerViewAdapter(private val mValues: List<DummyContent.DummyItem>) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val ankoContext = AnkoContext.create(parent.context, parent)
            val ui = ListItemComponent()
            val view = ui.createView(ankoContext)
            return ViewHolder(view, ui)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder) {
                item = mValues[position]
                idView.text = mValues[position].id
                contentView.text = mValues[position].content

                view.setOnClickListener { v ->
                    if (twoPane) {
                        val fragment = DetailFragment().withArguments(
                            DetailFragment.ARG_ITEM_ID to item!!.id
                        )
                        supportFragmentManager.beginTransaction().replace(R.id.detail_container, fragment).commit()
                    } else {
                        startActivity(intentFor<DetailActivity>(DetailFragment.ARG_ITEM_ID to holder.item!!.id))
                    }
                }
            }
        }

        override fun getItemCount(): Int = mValues.size

        inner class ViewHolder(val view: View, val ui: ListItemComponent) : RecyclerView.ViewHolder(view) {
            val idView = ui.identifier
            val contentView = ui.content
            var item: DummyContent.DummyItem? = null

            override fun toString(): String = "${super.toString()} '${contentView.text}'"
        }
    }
}