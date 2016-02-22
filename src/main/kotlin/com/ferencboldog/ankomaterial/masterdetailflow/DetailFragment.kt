package com.ferencboldog.ankomaterial.masterdetailflow

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ferencboldog.ankomaterial.R
import com.ferencboldog.ankomaterial.masterdetailflow.ui.DetailComponent
import com.ferencboldog.ankomaterial.masterdetailflow.ui.DetailTextComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class DetailFragment : Fragment() {

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        val ARG_ITEM_ID = "item_id"
    }

    lateinit var mItem: DummyContent.DummyItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments.containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP[arguments.getString(ARG_ITEM_ID)]!!

            val appBarLayout: CollapsingToolbarLayout? = activity.find<CollapsingToolbarLayout>(DetailComponent.TOOLBAR_LAYOUT_ID)
            if (appBarLayout != null) {
                appBarLayout.title = mItem.content
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = DetailTextComponent().createView(AnkoContext.create(context, this)) as TextView

        // Show the dummy content as text in a TextView.
        view.text = mItem.details

        return view
    }
}