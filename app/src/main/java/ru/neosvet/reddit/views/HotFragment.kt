package ru.neosvet.reddit.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import ru.neosvet.reddit.R
import ru.neosvet.reddit.databinding.FragmentHotBinding
import ru.neosvet.reddit.list.Post
import ru.neosvet.reddit.list.PostsAdapter

class HotFragment : Fragment() {
    private var binding: FragmentHotBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHotBinding.inflate(inflater, container, false).also {
        setHasOptionsMenu(true)
        binding = it
    }.root

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val item = menu.add(R.string.refresh)
        item.setIcon(R.drawable.ic_refresh)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //TODO refresh
        return super.onOptionsItemSelected(item)
    }

    private fun initList() = binding?.run {
        val adapter = PostsAdapter(
            listOf(
                Post("text1", 1, 2),
                Post("text2", 1, 2),
                Post("text3", 1, 2),
                Post("text4", 1, 2),
                Post("text5", 1, 2),
                Post("text6", 1, 2),
                Post("text7", 1, 2),
                Post("text8", 1, 2),
                Post("text9", 1, 2),
                Post("text10", 1, 2)
            )
        )
        rvPosts.adapter = adapter
    }
}