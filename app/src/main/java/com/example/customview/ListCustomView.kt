package com.example.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.databinding.CustomViewBinding
import com.example.customview.databinding.EmptyViewBinding
import com.example.customview.databinding.ListCustomViewBinding

class ListCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding = ListCustomViewBinding.inflate(LayoutInflater.from(context), this, true)

    val recyclerView: RecyclerView
        get() = binding.recyclerView


    private val emptyBinding: EmptyViewBinding = binding.customEmptyView

    var emptyText: String = ""
        set(value) {
            field = value
            binding.customEmptyView.emptyMessage.text = value
        }

    init {

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomRecyclerView,
            0,
            0
        ).apply {
            try {
                emptyText =
                    getString(R.styleable.CustomRecyclerView_emptyText) ?: "Nothing to show"

            } finally {
                recycle()
            }
        }
    }

    fun showEmptyView(msg: String? = null) {
        emptyText = msg ?: emptyText
        emptyBinding.root.visibility = View.VISIBLE
    }


}