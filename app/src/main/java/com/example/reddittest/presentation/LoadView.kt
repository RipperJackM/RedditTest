package com.example.reddittest.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.reddittest.R
import com.example.reddittest.databinding.LoadViewBinding

class LoadingView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LoadViewBinding =
            LoadViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val spinAnimation: Animation = AnimationUtils.loadAnimation(context, R.anim.load_spinner_anim)

    init {
        binding.loadingViewContainer.setOnClickListener(LoadingViewClickListener())
    }

    fun showView() {
        binding.loadingViewSpinner.startAnimation(spinAnimation)
        binding.loadingViewContainer.visibility = VISIBLE
    }

    fun hideView() {
        binding.loadingViewSpinner.clearAnimation()
        binding.loadingViewContainer.visibility = GONE
    }

    inner class LoadingViewClickListener: OnClickListener {
        override fun onClick(v: View?) {
            return
        }
    }
}
