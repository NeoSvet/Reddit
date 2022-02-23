package ru.neosvet.reddit.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.neosvet.reddit.databinding.ItemPostBinding

class PostsAdapter(
    private val posts: List<Post>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<PostItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItem =
        PostItem(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )

    override fun onBindViewHolder(holder: PostItem, position: Int) {
        holder.setData(posts[position])
    }

    override fun getItemCount() = posts.size
}