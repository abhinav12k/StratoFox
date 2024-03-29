package com.deepspace.hab.screens.lesson

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepspace.common.base.BaseFragment
import com.deepspace.hab.R
import com.deepspace.hab.Stratofox
import com.deepspace.hab.StratofoxViewModelFactory
import com.deepspace.hab.databinding.FragmentLessonListBinding
import com.deepspace.hab.models.Module
import com.deepspace.hab.models.ModuleSection
import timber.log.Timber

class LessonListFragment : BaseFragment<FragmentLessonListBinding>() {

    override fun getViewBinding(): FragmentLessonListBinding = FragmentLessonListBinding.inflate(layoutInflater)

    private val lessonViewModel: LessonViewModel by activityViewModels {
        StratofoxViewModelFactory((requireActivity().application as Stratofox).homeRepository)
    }
    private var lessonListAdapter: LessonListAdapter? = null

    override fun onViewCreated(savedInstanceState: Bundle?) {
        observeViewState()
        initUI()
        binding.ivBackBtn.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun initUI() {
        lessonViewModel.currModule?.id?.let { lessonViewModel.fetchModuleSections(it, lessonViewModel.currModule?.moduleSectionVersion) } //TODO: if id is null show error and retry
        lessonViewModel.currModule?.let { setupTextualContent(it) }
        initAdapter()
    }

    private fun initAdapter() {
        lessonListAdapter = LessonListAdapter { moduleSection -> goToLessonDetailActivity(moduleSection) }
        binding.rvLessonList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvLessonList.adapter = lessonListAdapter
    }

    private fun observeViewState() {
        lessonViewModel.moduleSectionsViewState.observe(viewLifecycleOwner) {
            handleViewState(it)
        }
    }

    private fun handleViewState(viewState: ModuleSectionsViewState){
        if(viewState.showLoader){
            //show loader
        }else{
            //hide loader
        }
        viewState.moduleSections?.let {
            lessonListAdapter?.submitList(it)
        }
    }

    private fun setupTextualContent(module: Module) {
        val moduleName = "Module " + module.rank
        binding.tvModuleTitle.text = module.title
        binding.tvModuleDescription.text = module.description
        binding.tvModuleName.text = moduleName
        module.rank?.let { setupLineColor(it) }
        module.rank?.let { setupImage(it) }
    }

    private fun setupImage(moduleNo: Int) {
        binding.ivModuleCoverImage.setImageResource(
            when (moduleNo) {
                0 -> R.drawable.ic_prerequisite
                1 -> R.drawable.ic_planning
                2 -> R.drawable.ic_test
                3 -> R.drawable.ic_launch
                4 -> R.drawable.ic_retrieval
                else -> R.drawable.ic_planning
            }
        )
    }

    private fun setupLineColor(moduleNo: Int) {
        binding.viewLineColor.setBackgroundResource(getColorID(moduleNo))
    }

    private fun goToLessonDetailActivity(moduleSection: ModuleSection) {
        Timber.d("ModuleSection LessonId: ${moduleSection.lessonId}")

        val lessonContent = when (moduleSection.lessonId) {
            "1" -> LessonDetailFragment.LessonContent(
                LessonDetailFragment.Content(
                    "Understanding Equipments",
                    "It's great to see you here. This is the point from which you can start your journey of making a High Altitude Balloon, known as HAB (this will be the direct reference to everything we state as high altitude balloon in the app). \n" +
                            "\n" +
                            "In this lesson you are going to learn about a what a high altitude balloon is and what are its various functions and usages? You learn about concepts like overall construction, architecture and different components that goes in the making of a HAB.\n" +
                            "\n" +
                            "The objective of this app is to make you learn everything about a HAB, right from scratch to building one for yourself. This project is really cool to consider for summer holidays or as a backyard science project to do with your friends and community.",
                    R.drawable.ic_module_0_lesson_1_0
                ),
                listOf(
                    LessonDetailFragment.Content(
                        "Get Started",
                        "Let's talk about what exactly is a High Altitude Balloon, or a HAB.\n" +
                                "\n" +
                                "A high altitude balloon is also known as a weather balloon or near space balloon. It have great advantages to explore the different dimensions of the near space. For this specific experiment, we are defining near space as the region between 20KM to 100KM above the sea-level. This range extends from Troposphere and lies somewhat between Stratosphere and Mesosphere. Mesosphere is the layer where the ionic charges separate from each other."
                    ),
                    LessonDetailFragment.Content(
                        "Aim of the Experiment",
                        "The major objectives of designing and conducting this experiment is to : \n" +
                                "\n" +
                                "1) Build a high altitude balloon.   \n" +
                                "\n" +
                                "2) Collect the data and perform operations on them to visualise and interpret it\n" +
                                "\n" +
                                "The data collected will usually include weather conditions, pressure levels, height indicators and ambient sensor to measure the amount of sunlight at different proportions of layers",

                        ),
                    LessonDetailFragment.Content(
                        "Understanding the HAB Structure",
                        "A typical High Altitude Balloon usually consists of 3 major components: \n" +
                                "\n" +
                                "1. The Payload\n" +
                                "2. Parachute\n" +
                                "3. Balloon\n" +
                                "\n" +
                                "The communication setup can be inside the payload as internal integration or can be used externally as well.",
                        R.drawable.ic_module_0_lesson_1_1
                    )
                )
            )
            "2" -> LessonDetailFragment.LessonContent(
                LessonDetailFragment.Content(
                    "Understanding Equipments",
                    "",
                    R.drawable.ic_module_1_lesson_1
                ),
                listOf(
                    LessonDetailFragment.Content(
                        "What is sensor?",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and \n" +
                                "scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was \n" +
                                ". popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                    ),
                    LessonDetailFragment.Content(
                        "The Control System",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic "
                    )
                )
            )
            "3" -> LessonDetailFragment.LessonContent(
                LessonDetailFragment.Content(
                    "Understanding Equipments",
                    "",
                    R.drawable.ic_module_1_lesson_1
                ),
                listOf(
                    LessonDetailFragment.Content(
                        "What is sensor?",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and \n" +
                                "scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was \n" +
                                ". popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                    ),
                    LessonDetailFragment.Content(
                        "The Control System",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic "
                    )
                )
            )
            "4" -> LessonDetailFragment.LessonContent(
                LessonDetailFragment.Content(
                    "Understanding Equipments",
                    "",
                    R.drawable.ic_module_1_lesson_1
                ),
                listOf(
                    LessonDetailFragment.Content(
                        "What is sensor?",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and \n" +
                                "scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was \n" +
                                ". popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                    ),
                    LessonDetailFragment.Content(
                        "The Control System",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic "
                    )
                )
            )
            "5" -> LessonDetailFragment.LessonContent(
                LessonDetailFragment.Content(
                    "Understanding Equipments",
                    "",
                    R.drawable.ic_module_1_lesson_1
                ),
                listOf(
                    LessonDetailFragment.Content(
                        "What is sensor?",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and \n" +
                                "scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was \n" +
                                ". popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                    ),
                    LessonDetailFragment.Content(
                        "The Control System",
                        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic "
                    )
                )
            )
            else -> LessonDetailFragment.LessonContent()
        }
        lessonViewModel.lessonContent = lessonContent
        findNavController().navigate(
            LessonListFragmentDirections.actionLessonListFragmentToLessonDetailFragment(
                moduleSection.moduleNo ?: 1,
                moduleSection.lessonNumber ?: 1
            )
        )
    }

    private fun getColorID(moduleNo: Int): Int {
        return when (moduleNo) {
            0 -> R.color.RedOrange
            1 -> R.color.bg_module1
            2 -> R.color.bg_module2
            3 -> R.color.bg_module3
            4 -> R.color.bg_module4
            else -> R.color.bg_module1
        }
    }
}