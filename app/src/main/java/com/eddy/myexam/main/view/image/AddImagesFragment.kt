package com.eddy.myexam.main.view.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.eddy.myexam.databinding.FragmentAddImageBinding
import com.eddy.myexam.extension.hideKeyboard
import com.eddy.myexam.main.viewmodel.image.SearchImagesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class AddImagesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    @Inject
    lateinit var glide: RequestManager
    val binding by lazy { FragmentAddImageBinding.inflate(layoutInflater) }
    val viewModel by activityViewModels<SearchImagesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddImagesFragment().apply {
            arguments = Bundle().apply {}
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initLiveData()
    }

    private fun initView() {
        glide.load(viewModel.currentImageUrl)
            .centerCrop()
            .into(binding.image)

        binding.saveBtn.setOnClickListener {
            val note = binding.noteEdit.text.toString()
            if (note.isEmpty()) {
                requireActivity().hideKeyboard()
                Snackbar.make(
                    requireActivity().window.decorView,
                    "please enter a note",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.saveImage(note)
                }
            }
        }
        binding.layout.setOnClickListener {
            requireActivity().hideKeyboard()
            binding.noteEdit.clearFocus()
        }
    }

    private fun initLiveData() {
        viewModel.saveImage.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                when {
                    it.isSuccess() -> {
                        requireActivity().finishAfterTransition()
                    }
                    it.isError() -> {
                        Snackbar.make(
                            requireActivity().window.decorView,
                            it.message ?: "An unknown error occured.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    it.isLoading() -> {
                        requireActivity().hideKeyboard()
                        binding.noteEdit.clearFocus()
                    }
                }
            }
        }
    }


}