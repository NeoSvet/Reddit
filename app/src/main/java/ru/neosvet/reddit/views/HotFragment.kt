package ru.neosvet.reddit.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.neosvet.reddit.R
import ru.neosvet.reddit.databinding.FragmentHotBinding
import ru.neosvet.reddit.list.Post
import ru.neosvet.reddit.list.PostsAdapter
import ru.neosvet.reddit.viewmodel.HotState
import ru.neosvet.reddit.viewmodel.HotViewModel

class HotFragment : Fragment() {
    private val model: HotViewModel by viewModel()
    private var binding: FragmentHotBinding? = null
    private val clickOnLink: (String) -> Unit = { link ->
        try {
            val myIntent = Intent(Intent.ACTION_VIEW)
            myIntent.data = Uri.parse(link)
            requireActivity().startActivity(myIntent)
        } catch (e: Exception) {
        }
    }

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
        model.openHotPosts()
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
        val adapter = PostsAdapter(posts, clickOnLink)
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