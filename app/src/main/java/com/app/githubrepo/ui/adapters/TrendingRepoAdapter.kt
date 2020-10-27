package com.app.githubrepo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.githubrepo.R
import com.app.githubrepo.data.model.TrendingRepoResponse
import com.app.githubrepo.ui.interfaces.RecyclerViewItemClickListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_repo_list.view.*


/*
*
* Adapter class to show list of trending repositories
*
* */

class TrendingRepoAdapter(
    private val mContext: Context, private val repoList: ArrayList<TrendingRepoResponse.Items>,
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener?
) :
    RecyclerView.Adapter<TrendingRepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_repo_list, parent, false))


    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repoList[position]
        Glide.with(mContext).load(repo.owner.avatar_url).circleCrop().into(holder.ivRepoImage)
        holder.tvName.text = repo.name
        holder.tvScoreFork.text =
            mContext.getString(R.string.text_score_fork, repo.score, repo.forks)
        holder.itemView.setOnClickListener {
            recyclerViewItemClickListener?.onItemClickListener(
                position
            )
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivRepoImage: ImageView = itemView.iv_repo_image
        var tvName: TextView = itemView.tv_name
        var tvScoreFork: TextView = itemView.tv_score_fork

    }
}