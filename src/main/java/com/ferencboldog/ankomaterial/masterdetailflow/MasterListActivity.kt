package com.ferencboldog.ankomaterial.masterdetailflow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ferencboldog.ankomaterial.extensions.snackbar
import com.ferencboldog.ankomaterial.masterdetailflow.ui.DetailComponent
import com.ferencboldog.ankomaterial.masterdetailflow.ui.ListComponent
import com.ferencboldog.ankomaterial.masterdetailflow.ui.ListItemComponent
import org.jetbrains.anko.*

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

    lateinit var toolbar: Toolbar
    lateinit var fab: FloatingActionButton
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ListComponent().setContentView(this)

        toolbar = find<Toolbar>(ListComponent.TOOLBAR_ID)
        setSupportActionBar(toolbar)
        toolbar.title = title

        fab = find<FloatingActionButton>(ListComponent.FAB_ID)

        fab.onClick {
            view -> snackbar(view!!, "Replace with your own action", Snackbar.LENGTH_LONG) {
                setAction("Action", null)
            }
        }

        recyclerView = find<RecyclerView>(ListComponent.LIST_ID)
        setupRecyclerView(recyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(DummyContent.ITEMS)
    }

    inner class SimpleItemRecyclerViewAdapter(private val mValues: List<DummyContent.DummyItem>) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val ankoContext = AnkoContext.create(parent.context, parent);
            val view = ListItemComponent().createView(ankoContext)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mItem = mValues[position]
            holder.mIdView.text = mValues[position].id
            holder.mContentView.text = mValues[position].content

            holder.mView.setOnClickListener { v ->
                if (twoPane) {
                    val fragment = DetailFragment().withArguments(
                            DetailFragment.ARG_ITEM_ID to holder.mItem!!.id
                    )
                    supportFragmentManager.beginTransaction().replace(DetailComponent.DETAIL_CONTAINER_ID, fragment).commit()
                } else {
                    startActivity(intentFor<DetailActivity>(DetailFragment.ARG_ITEM_ID to holder.mItem!!.id))
                }
            }
        }

        override fun getItemCount(): Int =  mValues.size

        inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView
            val mContentView: TextView
            var mItem: DummyContent.DummyItem? = null

            init {
                mIdView = mView.find<TextView>(ListItemComponent.IDENTIFIER_ID)
                mContentView =  mView.find<TextView>(ListItemComponent.CONTENT_ID)
            }

            override fun toString(): String = "${super.toString()} '${mContentView.text}'"
        }
    }
}