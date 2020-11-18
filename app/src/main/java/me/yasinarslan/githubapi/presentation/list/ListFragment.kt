package me.yasinarslan.githubapi.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.yasinarslan.githubapi.R
import me.yasinarslan.githubapi.databinding.FragmentListBinding
import me.yasinarslan.githubapi.presentation.MainActivity
import me.yasinarslan.githubapi.presentation.MainViewModel
import me.yasinarslan.githubapi.presentation.MainViewModelFactory
import me.yasinarslan.githubapi.presentation.detail.DetailFragment

class ListFragment : Fragment() {
	private lateinit var viewModel: MainViewModel
	private lateinit var binding: FragmentListBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = FragmentListBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = viewLifecycleOwner
		initViewModel()
		initToolbar()
		initRecyclerView()
		return binding.root
	}

	private fun initViewModel() {
		viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(requireContext()))
			.get(MainViewModel::class.java)
		binding.vm = viewModel
	}

	private fun initToolbar() {
		val activity = requireActivity() as MainActivity
		activity.supportActionBar?.apply {
			title = "Home"
			setHomeButtonEnabled(false)
			setDisplayHomeAsUpEnabled(false)
		}
	}

	private fun initRecyclerView() {
		val adapter = ListAdapter { position ->
			viewModel.setSelectedPosition(position)
			navigateToDetail()
		}

		binding.repositoryList.apply {
			layoutManager = LinearLayoutManager(requireContext())
			setHasFixedSize(true)
			this.adapter = adapter
		}

		viewModel.getRepositoryList().observe(viewLifecycleOwner, {
			val listItem = it.map { item ->
				ListItem(item.repositoryName, item.isFavorite)
			}
			adapter.setData(listItem)
		})
	}

	private fun navigateToDetail() {
		val fragment = parentFragmentManager.findFragmentByTag(DetailFragment::class.java.simpleName)
			?: DetailFragment()
		parentFragmentManager.beginTransaction()
			.replace(R.id.container, fragment, DetailFragment::class.java.simpleName)
			.addToBackStack(DetailFragment::class.java.simpleName)
			.commit()
	}
}
