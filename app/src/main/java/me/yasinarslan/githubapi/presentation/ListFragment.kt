package me.yasinarslan.githubapi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.yasinarslan.githubapi.data.GithubRepositoryImpl
import me.yasinarslan.githubapi.databinding.FragmentListBinding
import me.yasinarslan.githubapi.domain.ListRepositoriesUseCase

class ListFragment : Fragment() {
	private lateinit var viewModel: MainViewModel
	private lateinit var binding: FragmentListBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = FragmentListBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = viewLifecycleOwner
		initViewModel()
		initRecyclerView()
		return binding.root
	}

	private fun initViewModel() {
		val githubRepository = GithubRepositoryImpl()
		val listRepositoriesUseCase = ListRepositoriesUseCase(githubRepository)
		viewModel = ViewModelProvider(
			requireActivity(),
			ViewModelFactory(listRepositoriesUseCase)
		).get(MainViewModel::class.java)
		binding.vm = viewModel
	}

	private fun initRecyclerView() {
		val adapter = ListAdapter { position ->
			// navigateToDetail with position
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

	class ViewModelFactory(private val listRepositoriesUseCase: ListRepositoriesUseCase) : ViewModelProvider.Factory {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
				return MainViewModel(listRepositoriesUseCase) as T
			}
			throw IllegalArgumentException("Unknown ViewModel class")
		}
	}

}