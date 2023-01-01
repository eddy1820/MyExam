package com.eddy.myexam.main.view.image

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.eddy.myexam.base.BaseActivity
import com.eddy.myexam.databinding.ActivitySearchImagesBinding
import com.eddy.myexam.main.view.adapter.ImagesSelectedAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchImagesActivity : BaseActivity() {


    private val binding by lazy { ActivitySearchImagesBinding.inflate(layoutInflater) }

    @Inject
    lateinit var adapter: ImagesSelectedAdapter

    companion object {
        fun start(activity: Activity) {
            Intent(activity, SearchImagesActivity::class.java).apply {
                putExtra(KEY_ACTIVITY_ANIM_FLAG, ACTIVITY_ANIM_FLAG_POPUP)
            }.let { activity.startActivity(it, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle()) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}