package me.yasinarslan.githubapi.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.yasinarslan.githubapi.databinding.ItemRepositoryListBinding

class ListAdapter(private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
	private val list = mutableListOf<ListItem>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding = ItemRepositoryListBinding.inflate(layoutInflater, parent, false)
		return ViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(list[position])
	}

	override fun getItemCount(): Int = list.size

	fun setData(list: List<ListItem>) {
		this.list.clear()
		this.list.addAll(list)
		notifyDataSetChanged()
	}

	inner class ViewHolder(private val binding: ItemRepositoryListBinding) : RecyclerView.ViewHolder(binding.root) {

		fun bind(item: ListItem) {
			binding.item = item
			binding.root.setOnClickListener {
				if (adapterPosition == RecyclerView.NO_POSITION) {
					return@setOnClickListener
				}

				itemClickListener(adapterPosition)
			}
		}
	}
}