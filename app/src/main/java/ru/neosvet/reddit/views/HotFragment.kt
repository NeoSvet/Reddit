package ru.neosvet.reddit.views

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.neosvet.reddit.R
import ru.neosvet.reddit.databinding.FragmentHotBinding
import ru.neosvet.reddit.list.Post
import ru.neosvet.reddit.list.PostsAdapter
import ru.neosvet.reddit.viewmodel.HotState
import ru.neosvet.reddit.viewmodel.HotViewModel

class HotFragment : Fragment() {
    private val model: HotViewModel by lazy {
        ViewModelProvider(this).get(HotViewModel::class.java)
    }
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
        model.loadHotPosts()
        model.state.observe(requireActivity(), this::changeModelState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val item = menu.add(R.string.refresh)
        item.setIcon(R.drawable.ic_refresh)
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        model.loadHotPosts()
        return super.onOptionsItemSelected(item)
    }

    private fun changeModelState(state: HotState) {
        when (state) {
            is HotState.Success -> initList(state.posts)
            is HotState.Error -> showError(state.throwable)
        }
    }

    private fun initList(posts: List<Post>) = binding?.run {
        val adapter = PostsAdapter(posts)
        rvPosts.adapter = adapter
    }

    private fun showError(throwable: Throwable) {
        binding?.run {
            val msg = String.format(
                getString(R.string.error_format),
                throwable.localizedMessage
            )
            Snackbar.make(rvPosts, msg, Snackbar.LENGTH_LONG)
        }
    }
}