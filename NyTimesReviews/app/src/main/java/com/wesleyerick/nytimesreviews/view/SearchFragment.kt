package com.wesleyerick.nytimesreviews.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wesleyerick.nytimesreviews.R
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        config(view)
        return view
    }

    private fun config(view: View) {
        view.btnSearchClose.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}