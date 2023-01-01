package com.eddy.myexam.main.view.image

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.eddy.myexam.databinding.FragmentSearchImagesBinding
import com.eddy.myexam.extension.hide
import com.eddy.myexam.extension.hideKeyboard
import com.eddy.myexam.extension.show
import com.eddy.myexam.main.view.adapter.HistoryAdapter
import com.eddy.myexam.main.view.adapter.ImageAdapter
import com.eddy.myexam.main.viewmodel.image.SearchImagesViewModel
import com.eddy.myexam.utils.SpUtils
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject


@AndroidEntryPoint
class SearchImagesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    val binding by lazy { FragmentSearchImagesBinding.inflate(layoutInflater) }

    @Inject
    lateinit var imageAdapter: ImageAdapter

    @Inject
    lateinit var historyAdapter: HistoryAdapter
    var gridLayoutManager: GridLayoutManager? = null

    val viewModel by activityViewModels<SearchImagesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    companion object {
        const val GRID_SPAN_COUNT = 4

        @JvmStatic
        fun newInstance() = SearchImagesFragment().apply {
            arguments = Bundle().apply {}
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initLiveData()
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        if (viewModel.isGridLayout == null) {
            viewModel.isGridLayout = AtomicBoolean(SpUtils.getLayoutType(requireContext()))
        }
        initRecyclerView()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }

        binding.list.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                clearPage()
            }
            false
        }

        binding.searchBtn.setOnClickListener {
            lifecycleScope.launch {
                binding.searchEdit.text.toString().let { text ->
                    if (text.trim().isNotEmpty()) {
                        viewModel.getHistories().toMutableList().let {
                            if (it.contains(text.trim()).not()) {
                                it.add(0, text.trim())
                                viewModel.setHistories(it)
                            }
                        }
                        binding.searchEdit.clearFocus()
                        viewModel.searchForImage(text)
                    } else {
                        imageAdapter.submitList(listOf())
                        historyAdapter.submitList(listOf())
                    }
                }
            }
        }
        binding.switchBtn.setOnClickListener {
            if (viewModel.isGridLayout?.get() == true) {
                GRID_SPAN_COUNT
            } else {
                1
            }.let {
                viewModel.isGridLayout?.set(viewModel.isGridLayout?.get()?.not() ?: true)
                imageAdapter.gridCount = getGridLayoutCount()
                gridLayoutManager?.spanCount = getGridLayoutCount()
                imageAdapter.notifyItemRangeChanged(0, imageAdapter.itemCount)
            }
            clearPage()
        }

        binding.searchEdit.setOnFocusChangeListener { view, b ->
            if (b) {
                viewModel.getHistories().let {
                    historyAdapter.submitList(it.toList())
                }
            } else {
                historyAdapter.submitList(null)
            }
        }

    }

    private fun clearPage() {
        requireActivity().hideKeyboard()
        binding.searchEdit.clearFocus()
        historyAdapter.submitList(listOf())
    }

    private fun initLiveData() {
        viewModel.searchForImage.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { result ->
                when {
                    result.isSuccess() -> {
                        imageAdapter.submitList(result.data?.hits ?: listOf())
                        binding.loadingLayout.hide()
                    }
                    result.isError() -> {
                        binding.loadingLayout.hide()
                        Snackbar.make(
                            requireActivity().window.decorView,
                            result.message ?: "An unknown error occured.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    result.isLoading() -> {
                        binding.loadingLayout.show()
                    }
                }
            }
        }
    }


    private fun initRecyclerView() {
        binding.list.apply {
            adapter = imageAdapter.apply {
                setOnItemClickListener {
                    viewModel.currentImageUrl = it
                    findNavController().navigate(SearchImagesFragmentDirections.actionSearchImagesFragmentToAddImagesFragment())
                }
                gridCount = getGridLayoutCount()
            }
            gridLayoutManager = GridLayoutManager(requireContext(), getGridLayoutCount())
            layoutManager = gridLayoutManager
        }

        binding.historiesList.apply {
            adapter = historyAdapter.apply {
                setOnItemClickListener {
                    binding.searchEdit.setText(it)
                    binding.searchBtn.performClick()
                }
            }
        }
    }

    private fun getGridLayoutCount(): Int {
        return if (viewModel.isGridLayout?.get() == true) GRID_SPAN_COUNT else 1
    }

}