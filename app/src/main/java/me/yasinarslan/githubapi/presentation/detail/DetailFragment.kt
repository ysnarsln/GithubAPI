package me.yasinarslan.githubapi.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.yasinarslan.githubapi.databinding.FragmentDetailBinding
import me.yasinarslan.githubapi.presentation.MainActivity
import me.yasinarslan.githubapi.presentation.MainViewModel
import me.yasinarslan.githubapi.presentation.MainViewModelFactory

class DetailFragment : Fragment() {
	private lateinit var binding: FragmentDetailBinding
	private lateinit var viewModel: MainViewModel

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = FragmentDetailBinding.inflate(inflater, container, false)
		binding.lifecycleOwner = viewLifecycleOwner
		initViewModel()
		initToolbar()
		return binding.root
	}

	private fun initViewModel() {
		viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory()).get(MainViewModel::class.java)
		binding.vm = viewModel
	}

	private fun initToolbar() {
		val activity = requireActivity() as MainActivity
		activity.supportActionBar?.apply {
			title = viewModel.getSelectedRepositoryItem().repositoryName
			setHomeButtonEnabled(true)
			setDisplayHomeAsUpEnabled(true)
		}
	}

}