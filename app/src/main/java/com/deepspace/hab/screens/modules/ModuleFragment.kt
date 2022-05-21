package com.deepspace.hab.screens.modules

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.Stratofox
import com.deepspace.hab.StratofoxViewModelFactory
import com.deepspace.hab.databinding.FragmentModuleBinding
import com.deepspace.hab.models.Module
import com.deepspace.hab.screens.home.HomeViewModel
import timber.log.Timber

class ModuleFragment : BaseFragment<FragmentModuleBinding>() {

    override fun getViewBinding(): FragmentModuleBinding =
        FragmentModuleBinding.inflate(layoutInflater)

    private val viewModel: HomeViewModel by activityViewModels {
        StratofoxViewModelFactory((requireActivity().application as Stratofox).homeRepository)
    }
    private var moduleAdapter: ModuleAdapter? = null

    override fun onViewCreated(savedInstanceState: Bundle?) {
        observeViewState()
        viewModel.fetchModuleList()
        initAdapter()

        binding.cardModule0.setOnClickListener {
            findNavController().navigate(
                ModuleFragmentDirections.actionModuleFragmentToLessonActivity(
                    Module(
                        description = "Learn about the fundamentals and experiment of building a HAB",
                        title = "Prerequisite",
                        rank = 0,
                    )
                )
            )
        }

    }

    private fun initAdapter() {
        moduleAdapter = ModuleAdapter { module -> onModuleClicked(module) }
        binding.rvModules.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.rvModules.adapter = moduleAdapter
    }

    private fun observeViewState() {
        viewModel.moduleViewState.observe(viewLifecycleOwner) {
            handleViewState(it)
        }
    }

    private fun handleViewState(viewState: ModuleViewState) {
        if (viewState.showLoader) {
            binding.progressBar.visibility = View.VISIBLE
            binding.moduleRoot.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.moduleRoot.visibility = View.VISIBLE
        }

        viewState.moduleList?.let {
            Timber.tag("abhinav").d("$it")
            moduleAdapter?.submitList(it)
        }
    }

    private fun onModuleClicked(module: Module) {
        Timber.d("Module Clicked: $module")
        findNavController().navigate(
            ModuleFragmentDirections.actionModuleFragmentToLessonActivity(module)
        )
    }

}