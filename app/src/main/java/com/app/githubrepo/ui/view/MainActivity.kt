package com.app.githubrepo.ui.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.githubrepo.R
import com.app.githubrepo.data.model.TrendingRepoResponse
import com.app.githubrepo.ui.interfaces.FragmentCallbackListener
import com.app.githubrepo.ui.view.fragments.RepoDetailFragment
import com.app.githubrepo.ui.view.fragments.RepoListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FragmentCallbackListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openRepoListFragment()
    }


    /*
    *
    * method to open repository list fragment to show trending repositories
    *
    * */

    private fun openRepoListFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_container, RepoListFragment())
            .commit()
        addTitleToToolbar(getString(R.string.text_title_repo_list), false)
    }


    /*
  *
  * method to open repository detail fragment to show detail of a particular repository
  *
  * */

    override fun openRepoDetailFragment(items: TrendingRepoResponse.Items) {
        val repoDetailFragment = RepoDetailFragment.newInstance(items)
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_container, repoDetailFragment).addToBackStack(null).commit()
        addTitleToToolbar(getString(R.string.text_title_repo_detail), true)
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
            checkForTitle()
        } else {
            finish()
        }
    }

    /*
    *
    * check for loaded fragment and change toolbar title accordingly
    *
    * */

    private fun checkForTitle() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fl_container)
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

    override fun addTitleToToolbar(title: String, isHomeButtonEnabled: Boolean) {
        toolbar.title = title
        toolbar.setNavigationIcon(R.drawable.ic_back_white)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(isHomeButtonEnabled)
        supportActionBar?.setHomeButtonEnabled(isHomeButtonEnabled)
    }
}
