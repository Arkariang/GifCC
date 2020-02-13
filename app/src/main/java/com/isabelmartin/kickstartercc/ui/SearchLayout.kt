package com.isabelmartin.kickstartercc.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.isabelmartin.kickstartercc.R
import com.isabelmartin.kickstartercc.viewmodel.SearchGifViewModel
import kotlinx.android.synthetic.main.search_layout.view.*

class SearchLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), TextWatcher, TextView.OnEditorActionListener {

    private lateinit var searchViewModel: SearchGifViewModel

    override fun afterTextChanged(s: Editable) {
    }

    override fun onEditorAction(textView: TextView?, actionID: Int, key: KeyEvent?) =
        when (actionID) {
            EditorInfo.IME_ACTION_DONE,
            KeyEvent.ACTION_DOWN -> {
                executeSearch(textView?.text.toString())
                true
            }
            else -> false
        }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        executeSearch(s.toString())
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        clearSearch.visibility = s?.let { View.VISIBLE } ?: View.INVISIBLE
    }

    private fun executeSearch(query: String) = query.trim().takeIf { it.isNotEmpty() }?.apply {
        searchViewModel.search(this)
    }

    init {
        View.inflate(context, R.layout.search_layout, this)
        search_field.addTextChangedListener(this)
        search_field.setOnEditorActionListener(this)
        search_field.imeOptions = EditorInfo.IME_ACTION_DONE
    }

    // TODO: receive functions as parameter for search as well to de-couple view from viewModel
    fun setup(searchViewModel: SearchGifViewModel, closeCallback: (() -> Unit)? = null) {
        this.searchViewModel = searchViewModel

        clearSearch.setOnClickListener {
            closeCallback?.invoke()
            search_field.text.clear()
        }
    }
}