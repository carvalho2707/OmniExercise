package pt.tiagocarvalho.omni.news

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pt.tiagocarvalho.omni.R
import pt.tiagocarvalho.omni.databinding.SearchFragmentBinding
import pt.tiagocarvalho.omni.util.viewBinding

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment) {

    private val viewModel: SearchViewModel by viewModels()

    private val searchAdapter by lazy { SearchAdapter(this::onNewsClicked) }

    private val binding: SearchFragmentBinding by viewBinding(SearchFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.news.observe(viewLifecycleOwner) { news ->
            searchAdapter.submitList(news)
        }

        setupLayouts()
        setupNews()
    }

    private fun setupLayouts() {
        with(binding) {
            filter.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    filterDec.setText(R.string.search_filter_topics)
                } else {
                    filterDec.setText(R.string.search_filter_articles)
                }
                val searchTerm = searchTerm.text.toString()
                if (searchTerm.isNotBlank()) {
                    viewModel.search(searchTerm, filter.isChecked)
                }
            }

            searchTerm.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(serachTermEditable: Editable?) {
                    val searchTerm = serachTermEditable.toString()
                    if (searchTerm.isNotBlank()) {
                        viewModel.search(searchTerm, filter.isChecked)
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }
            })

            val layoutManager = LinearLayoutManager(context)
            news.layoutManager = layoutManager
            news.adapter = searchAdapter
        }
    }

    private fun setupNews() {
        viewModel.search("stockholm", binding.filter.isChecked)
    }

    private fun onNewsClicked(news: News) {
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
