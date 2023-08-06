package com.example.graphql

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graphql.databinding.UserInfoItemBinding


class UserAdapter(val onClick: (String?) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<UserInfo, UserAdapter.UserViewHolder>(
        DiffUtilCallBack()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserInfoItemBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindData(item)
        holder.itemView.setOnClickListener {
            onClick(item.id)
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<UserInfo>() {
        override fun areItemsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserInfo, newItem: UserInfo): Boolean =
            oldItem == newItem
    }

    class UserViewHolder(
        private val binding: UserInfoItemBinding,
        val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: UserInfo) {
            binding.name.text = item.name
            binding.email.text = item.email
            binding.address.text = item.address
        }
    }

}
