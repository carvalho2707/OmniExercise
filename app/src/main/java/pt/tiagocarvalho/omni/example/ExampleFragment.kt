package pt.tiagocarvalho.omni.example

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import pt.tiagocarvalho.omni.R
import pt.tiagocarvalho.omni.databinding.ExampleFragmentBinding
import pt.tiagocarvalho.omni.util.viewBinding

@AndroidEntryPoint
class ExampleFragment : Fragment(R.layout.example_fragment) {

    private val viewModel: ExampleViewModel by viewModels()

    private val exampleAdapter by lazy { ExampleAdapter(this::onExampleClicked) }

    private val binding: ExampleFragmentBinding by viewBinding(ExampleFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.examples.observe(viewLifecycleOwner) { examples ->
            exampleAdapter.submitList(examples)
        }

        setupExamples()
    }

    private fun setupExamples() {
        with(binding) {
            val layoutManager = LinearLayoutManager(context)
            examples.layoutManager = layoutManager
            examples.adapter = exampleAdapter
        }
        viewModel.fetchExample()
    }

    private fun onExampleClicked(example: Example) {
    }

    companion object {
        fun newInstance() = ExampleFragment()
    }
}
