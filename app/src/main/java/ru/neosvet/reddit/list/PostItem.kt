package ru.neosvet.reddit.list

import androidx.recyclerview.widget.RecyclerView
import ru.neosvet.reddit.databinding.ItemPostBinding

class PostItem(
    private val binding: ItemPostBinding,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun setData(post: Post) {
        with(binding) {
            tvPost.text = post.text
            tvStar.text = post.stars.toString()
            tvComment.text = post.comments.toString()
            root.setOnClickListener {
                onClick.invoke(post.link)
            }
        }
    }
}