package ru.neosvet.reddit.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.neosvet.reddit.databinding.ItemPostBinding

class PostsAdapter(
    private val onClick: (String) -> Unit
) : PagingDataAdapter<Post, PostItem>(PassengersComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostItem {
        return PostItem(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: PostItem, position: Int) {
        val item = getItem(position)
        item?.let { holder.setData(it) }
    }

    object PassengersComparator : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.link == newItem.link
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }
}