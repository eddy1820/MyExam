package com.eddy.myexam.base

import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.transition.platform.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val KEY_ACTIVITY_ANIM_FLAG = "ACTIVITY_ANIM_FLAG"
        const val ACTIVITY_ANIM_FLAG_EXPLODE = "EXPLODE"
        const val ACTIVITY_ANIM_FLAG_SLIDE = "SLIDE"
        const val ACTIVITY_ANIM_FLAG_POPUP = "POPUP"
        const val ACTIVITY_ANIM_FLAG_FADE = "FADE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
        setupTransition()
    }

    fun setupTransition() {
        when (intent.getStringExtra(KEY_ACTIVITY_ANIM_FLAG)) {
            ACTIVITY_ANIM_FLAG_EXPLODE -> {
                val explodeTransition = Explode()
                explodeTransition.epicenter
                explodeTransition.duration = 300
                window.enterTransition = explodeTransition
                window.exitTransition = explodeTransition
            }

            ACTIVITY_ANIM_FLAG_SLIDE -> {
                val slideTransition = Slide()
                slideTransition.slideEdge = Gravity.END
                slideTransition.duration = 300
                window.enterTransition = slideTransition
                window.reenterTransition = null
                window.exitTransition = null
                window.returnTransition = slideTransition
            }

            ACTIVITY_ANIM_FLAG_FADE -> {
                val fadeTransition = Fade()
                fadeTransition.duration = 300
                window.enterTransition = fadeTransition
                window.exitTransition = fadeTransition
            }

            ACTIVITY_ANIM_FLAG_POPUP -> {
                window.enterTransition = MaterialSharedAxis(MaterialSharedAxis.Y, true).apply {
                    duration = 300
                }
                window.reenterTransition = null
                window.exitTransition = null
                window.returnTransition = MaterialSharedAxis(MaterialSharedAxis.Y, false).apply {
                    duration = 300
                }
            }
        }
    }
}