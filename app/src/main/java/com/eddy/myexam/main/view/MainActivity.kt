package com.eddy.myexam.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eddy.myexam.databinding.ActivityMainBinding
import com.eddy.myexam.main.view.adapter.ImagesSelectedAdapter
import com.eddy.myexam.main.view.image.SearchImagesActivity
import com.eddy.myexam.main.viewmodel.MainViewModel
import com.eddy.myexam.utils.SpUtils
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var imagesSelectedAdapter: ImagesSelectedAdapter

    val viewModel by viewModels<MainViewModel>()

    companion object {
        const val LAYOUT_IS_GRID = "LAYOUT_IS_GRID"
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.addImagesFab.setOnClickListener {
            SearchImagesActivity.start(this)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter = imagesSelectedAdapter.apply {
            setOnItemClickListener {
                viewModel.deleteImageItem(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.imageItems.observe(this) {
            imagesSelectedAdapter.submitList(it)
        }
        checkFirebaseConfig()
    }

    private fun checkFirebaseConfig() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder().apply {
            minimumFetchIntervalInSeconds = 0
        }.build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val status = remoteConfig.getBoolean(LAYOUT_IS_GRID)
                Timber.e("Firebase remote config layout is grid :${status}")
                SpUtils.setLayoutType(this, status)
            }
        }
    }
}