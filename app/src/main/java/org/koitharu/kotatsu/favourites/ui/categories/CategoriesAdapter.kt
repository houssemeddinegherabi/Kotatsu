package org.koitharu.kotatsu.favourites.ui.categories

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.koitharu.kotatsu.base.ui.list.OnListItemClickListener
import org.koitharu.kotatsu.core.model.FavouriteCategory

class CategoriesAdapter(
	onItemClickListener: OnListItemClickListener<FavouriteCategory>,
) : AsyncListDifferDelegationAdapter<FavouriteCategory>(DiffCallback()) {

	init {
		delegatesManager.addDelegate(categoryAD(onItemClickListener))
		setHasStableIds(true)
	}

	override fun getItemId(position: Int): Long {
		return items[position].id
	}

	private class DiffCallback : DiffUtil.ItemCallback<FavouriteCategory>() {

		override fun areItemsTheSame(
			oldItem: FavouriteCategory,
			newItem: FavouriteCategory,
		): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(
			oldItem: FavouriteCategory,
			newItem: FavouriteCategory,
		): Boolean {
			return oldItem.id == newItem.id && oldItem.title == newItem.title
					&& oldItem.order == newItem.order
		}

		override fun getChangePayload(
			oldItem: FavouriteCategory,
			newItem: FavouriteCategory,
		): Any? = when {
			oldItem.title == newItem.title && oldItem.order != newItem.order -> newItem.order
			else -> super.getChangePayload(oldItem, newItem)
		}
	}
}