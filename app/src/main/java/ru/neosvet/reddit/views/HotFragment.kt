package ru.neosvet.reddit.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.neosvet.reddit.R
import ru.neosvet.reddit.databinding.FragmentHotBinding
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
    private lateinit var adapter: PostsAdapter
    private val mDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHotBinding.inflate(inflater, container, false).also {
        setHasOptionsMenu(true)
        binding = it
    }.root

    override fun onDestroyView() {
        mDisposable.dispose()
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        model.preparing()
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
        binding?.pLoad?.visibility = View.GONE
        when (state) {
            HotState.Success -> setupList()
            HotState.Loading -> binding?.pLoad?.visibility = View.VISIBLE
            is HotState.Error -> showError(state.throwable)
        }
    }

    private fun setupList() {
        adapter = PostsAdapter(clickOnLink)
        binding?.rvPosts?.adapter = adapter
        mDisposable.add(model.paging().subscribe {
            adapter.submitData(lifecycle, it)
        })
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