package com.wesleyerick.nytimesreviews.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wesleyerick.nytimesreviews.R
import com.wesleyerick.nytimesreviews.presenter.Api
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerConfig()
        Api.cacheConfig(this)
        Api.getRamdomMovies()

        btnSearch.setOnClickListener {
            basicStartFragment(SearchFragment(), "SEARCH_FRAGMENT")
            it.visibility = View.INVISIBLE
        }
    }

    fun recyclerConfig(){
        val layoutInflater = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerMovies.layoutManager = layoutInflater
        recyclerMovies.adapter = listAdapter
    }

    fun basicStartFragment(startProcedureFragment: Fragment, TAG : String) {

        container.visibility = View.VISIBLE
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            com.google.android.material.R.anim.abc_slide_in_top,
            com.google.android.material.R.anim.abc_slide_out_top,
            com.google.android.material.R.anim.abc_slide_in_top,
            com.google.android.material.R.anim.abc_slide_out_top
        )
        transaction.replace(R.id.container, startProcedureFragment)
        transaction.addToBackStack(TAG)
        transaction.commit()
    }
}