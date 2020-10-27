package com.app.githubrepo.ui.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.githubrepo.R
import com.app.githubrepo.data.model.ResponseStatus
import com.app.githubrepo.data.model.TrendingRepoResponse
import com.app.githubrepo.ui.adapters.TrendingRepoAdapter
import com.app.githubrepo.ui.interfaces.FragmentCallbackListener
import com.app.githubrepo.ui.interfaces.RecyclerViewItemClickListener
import com.app.githubrepo.ui.viewmodel.MainViewModel
import com.app.githubrepo.utils.Constants
import com.app.githubrepo.utils.Util
import kotlinx.android.synthetic.main.fragment_repo_list.*

/**
 * A class to show list of trending repositories.
 */
class RepoListFragment : Fragment() {
    internal var callBackListener: FragmentCallbackListener? = null
    private lateinit var viewModel: MainViewModel
    private lateinit var repoList: ArrayList<TrendingRepoResponse.Items>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var mAdapter: TrendingRepoAdapter
    private var pageNumber = 1
    private var isPagination = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        repoList = ArrayList()
        getTrendingRepo()
        setAdapterToRecyclerView()
        setSwipeRefreshListener()
    }

    private fun setSwipeRefreshListener() {
        srl_refresh.setOnRefreshListener {
            pageNumber = 1
            isPagination = false
            getTrendingRepo()
        }
    }

    private fun setAdapterToRecyclerView() {
        layoutManager = LinearLayoutManager(activity!!)
        rv_repo_list.layoutManager = layoutManager
        mAdapter =
            TrendingRepoAdapter(activity!!, repoList, object : RecyclerViewItemClickListener {
                override fun onItemClickListener(position: Int) {
                    callBackListener?.openRepoDetailFragment(repoList[position])
                }

            })
        rv_repo_list.adapter = mAdapter

        rv_repo_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount: Int = layoutManager.childCount
                    val totalItemCount: Int = layoutManager.itemCount
                    val firstVisibleItemPosition: Int =
                        layoutManager.findFirstVisibleItemPosition()
                    if (!isPagination && repoList.size < 1000 && !srl_refresh.isRefreshing) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                            isPagination = true
                            pageNumber++
                            getTrendingRepo()
                        }
                    }
                }
            }
        })

    }

    /*
    *
    *
    * method to get trending repo list from api
    *
    * */

    private fun getTrendingRepo() {
        viewModel.getTrendingRepo(Constants.TRENDING_REPO, pageNumber)
            .observe(activity!!, Observer {
                try {
                    when (it) {
                        is ResponseStatus.Success -> {

                            if (!isPagination) {
                                repoList.clear()
                            }
                            repoList.addAll(it.value.items)
                            mAdapter.notifyDataSetChanged()
                            if (pageNumber == 1) {
                                checkListSize()
                            }
                        }
                        is ResponseStatus.Error -> {
                            Util.showToast(activity!!, it.value.errorResponse.message)
                        }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
                if (srl_refresh.isRefreshing) {
                    srl_refresh.isRefreshing = false
                }
                isPagination = false
            })
    }

    /*
    *
    * method to check list size for page 1 only to show empty view
    *
    * */


    private fun checkListSize() {
        if (repoList.size > 0) {
            tv_no_list.visibility = View.VISIBLE
            rv_repo_list.visibility = View.GONE

        } else {
            tv_no_list.visibility = View.VISIBLE
            rv_repo_list.visibility = View.GONE
        }
    }

    fun setCallbackListener(callBackListener: FragmentCallbackListener) {
        this.callBackListener = callBackListener
    }


}
