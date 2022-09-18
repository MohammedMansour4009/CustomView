package com.example.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.example.customview.databinding.CustomViewBinding

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = CustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    private var onAreaSelectedListener: ((String) -> Unit)? = null



    private var require: Boolean = false
        set(value) {
            field = value
            binding.require = value
        }

    private var titleText: String = ""
        set(value) {
            field = value
            binding.textViewTitle.text = value
        }

    private var errorText: String = ""
        set(value) {
            field = value
            binding.textViewErrorMessage.text = value
        }

    @DrawableRes
    private var errorIcon = 0
        set(value) {
            field = value
            binding.imageViewError.setImageResource(value)
        }


    init {

        context.theme.obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0)
            .apply {
                try {
                    require = getInt(R.styleable.CustomView_status, 0) == REQUIRE
                    titleText = getString(R.styleable.CustomView_titleText) ?: "Ads Type"
                    errorText = getString(R.styleable.CustomView_errorText) ?: "Something went wrong"
                    errorIcon = getResourceId(R.styleable.CustomView_errorIcon, R.drawable.ic_error)
                } finally {
                    recycle()
                }
            }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (!isInEditMode) {
            initListeners()
        }
    }

    private fun initListeners() {
        binding.textViewTitle.setOnClickListener(::handleAreaClick)
    }

    private fun handleAreaClick(view: View) {
        onAreaSelectedListener?.invoke((view as TextView).text.toString())
    }

    fun checkView() {
        binding.isValid = true
    }

    fun setOnAreaSelectedListener(onItemSelectedListener: (String) -> Unit) {
        this.onAreaSelectedListener = onItemSelectedListener
    }


    companion object {
        const val REQUIRE = 1
    }
}


