package com.app.githubrepo.ui.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.githubrepo.R
import com.app.githubrepo.data.model.TrendingRepoResponse
import com.app.githubrepo.utils.Constants
import com.app.githubrepo.utils.Util
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_repo_detail.*


/**
 * A class to show details of repositories.
 */
class RepoDetailFragment : Fragment() {

    private var repoDetail=null as TrendingRepoResponse.Items?


    /*
    *
    * get fragment instance and set arguments
    *
    * */

    companion object {
        fun newInstance(repoDetail: TrendingRepoResponse.Items): RepoDetailFragment {
            val bundle = Bundle()
            bundle.putParcelable(Constants.KEY_REPO_DETAIL, repoDetail)
            val fragment = RepoDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoDetail = arguments?.getParcelable(Constants.KEY_REPO_DETAIL) as? TrendingRepoResponse.Items
        repoDetail?.let {
            setDataToViews()
        } ?: let {
            ll_detail.visibility = View.GONE
            tv_no_detail.visibility = View.VISIBLE
        }
    }

    /*
    *
    * set repository detail to views
    *
    * */

    private fun setDataToViews() {

        //set Name of Repo
        repoDetail?.name?.let {
            tv_name.text = repoDetail?.name
        } ?: let {
            ll_name.visibility = View.GONE
        }


        //set Description of Repo
        repoDetail?.description?.let {
            tv_description.text = repoDetail?.description
        } ?: let {
            ll_description.visibility = View.GONE
        }

        //set Author of Repo
        repoDetail?.html_url?.let {
            tv_project_link.text = repoDetail?.html_url
        } ?: let {
            ll_project_link.visibility = View.GONE
        }

        //set Language of Repo
        repoDetail?.language?.let {
            tv_language.text = repoDetail?.language
        } ?: let {
            ll_language.visibility = View.GONE
        }

        //set Stars of Repo
        repoDetail?.score?.let {
            tv_score.text = repoDetail?.score
        } ?: let {
            ll_score.visibility = View.GONE
        }

        //set Forks of Repo
        repoDetail?.forks?.let {
            tv_forks.text = repoDetail?.forks
        } ?: let {
            ll_forks.visibility = View.GONE
        }

        //set owner details of Repo
        repoDetail?.owner?.let {
            tv_owner_name.text = repoDetail?.owner?.login
            Glide.with(activity!!).load(repoDetail?.owner?.avatar_url).circleCrop().into(iv_owner_image)

            tv_owner_name.setOnClickListener {
                Util.openUrlToWebView(repoDetail?.owner?.html_url,activity!!)
            }
        } ?: let {
            ll_forks.visibility = View.GONE
        }


    }




}
