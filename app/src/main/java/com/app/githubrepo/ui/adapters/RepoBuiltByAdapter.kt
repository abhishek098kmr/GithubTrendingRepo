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
import kotlinx.android.synthetic.main.row_built_by.view.*

class RepoBuiltByAdapter(
    private val mContext: Context,
    private val builtByList: List<TrendingRepoResponse.BuiltBy>,
    private val recyclerViewItemClickListener: RecyclerViewItemClickListener?
) :
    RecyclerView.Adapter<RepoBuiltByAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_built_by, parent, false))


    override fun getItemCount(): Int = builtByList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val builtBy = builtByList[position]
        holder.tvName.text = builtBy.username
        Glide.with(mContext).load(builtBy.avatar).into(holder.ivImage)
        holder.itemView.setOnClickListener {
            recyclerViewItemClickListener?.onItemClickListener(position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivImage: ImageView = itemView.iv_image
        var tvName: TextView = itemView.tv_name
    }
}