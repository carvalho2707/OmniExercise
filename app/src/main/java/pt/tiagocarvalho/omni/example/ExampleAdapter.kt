package pt.tiagocarvalho.omni.example

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pt.tiagocarvalho.omni.databinding.ItemExampleBinding

internal class ExampleAdapter(
    private val onItemClicked: (Example) -> Unit
) :
    ListAdapter<Example, ExampleAdapter.ExampleViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view = ItemExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    class ExampleViewHolder(private val binding: ItemExampleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(example: Example, onItemClicked: (Example) -> Unit) {
            with(binding) {
                content.text = example.content
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Example>() {
            override fun areItemsTheSame(oldItem: Example, newItem: Example) =
                oldItem.url == newItem.url

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Example, newItem: Example) =
                oldItem == newItem
        }
    }
}
