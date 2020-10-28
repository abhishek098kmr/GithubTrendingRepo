package com.app.githubrepo.ui.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.githubrepo.R
import com.app.githubrepo.ui.interfaces.FragmentCallbackListener
import com.app.githubrepo.ui.view.fragments.RepoDetailFragment
import com.app.githubrepo.ui.view.fragments.RepoListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragmentCallbackListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragment(RepoListFragment())
    }


    /*
  *
  * method to open fragment to show detail of a particular repository
  *
  * */

    override fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, fragment).addToBackStack(null).commit()
        checkForTitle(fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is RepoListFragment) {
            fragment.setCallbackListener(this)
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fl_container)
        if (fragment is RepoDetailFragment) {
            supportFragmentManager.popBackStackImmediate()
            checkForTitle(supportFragmentManager.findFragmentById(R.id.fl_container)!!)
        } else {
            finish()
        }
    }

    /*
    *
    * check for loaded fragment and change toolbar title accordingly
    *
    * */

    private fun checkForTitle(fragment: Fragment) {
        if (fragment is RepoListFragment) {
            addTitleToToolbar(getString(R.string.text_title_repo_list), false)
        } else if (fragment is RepoDetailFragment) {
            addTitleToToolbar(getString(R.string.text_title_repo_detail), true)
        }
    }


    /*
   *
   * add title to toolbar
   *
   * */

    private fun addTitleToToolbar(title: String, isHomeButtonEnabled: Boolean) {
        toolbar.title = title
        toolbar.setNavigationIcon(R.drawable.ic_back_white)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(isHomeButtonEnabled)
        supportActionBar?.setHomeButtonEnabled(isHomeButtonEnabled)
    }
}
