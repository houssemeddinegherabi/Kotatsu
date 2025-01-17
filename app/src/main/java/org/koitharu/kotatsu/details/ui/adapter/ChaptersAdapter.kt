package org.koitharu.kotatsu.details.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.koitharu.kotatsu.base.ui.list.OnListItemClickListener
import org.koitharu.kotatsu.details.ui.model.ChapterListItem
import kotlin.jvm.internal.Intrinsics

class ChaptersAdapter(
	onItemClickListener: OnListItemClickListener<ChapterListItem>,
) :	AsyncListDifferDelegationAdapter<ChapterListItem>(DiffCallback()) {

	init {
		setHasStableIds(true)
		delegatesManager.addDelegate(chapterListItemAD(onItemClickListener))
	}

	override fun getItemId(position: Int): Long {
		return items[position].chapter.id
	}

	fun setItems(newItems: List<ChapterListItem>, callback: Runnable) {
		differ.submitList(newItems, callback)
	}

	private class DiffCallback : DiffUtil.ItemCallback<ChapterListItem>() {

		override fun areItemsTheSame(oldItem: ChapterListItem, newItem: ChapterListItem): Boolean {
			return oldItem.chapter.id == newItem.chapter.id
		}

		override fun areContentsTheSame(
			oldItem: ChapterListItem,
			newItem: ChapterListItem
		): Boolean {
			return Intrinsics.areEqual(oldItem, newItem)
		}

		override fun getChangePayload(oldItem: ChapterListItem, newItem: ChapterListItem): Any? {
			if (oldItem.extra != newItem.extra && oldItem.chapter == newItem.chapter) {
				return newItem.extra
			}
			return null
		}
	}
}