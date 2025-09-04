package ir.vy.wikipedia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.vy.wikipedia.data.ItemPost
import ir.vy.wikipedia.databinding.ItemExploreCardBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class ExploreAdapter(
    private var data: ArrayList<ItemPost>,
    val itemEventsExplore: ItemEventsExplore,
    val itemEventsTrend: ItemEventsTrend
) :
    RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder>() {

    inner class ExploreViewHolder(val binding: ItemExploreCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(itemPost: ItemPost) {

            // Explore
            Glide.with(itemView.context)
                .load(itemPost.imgUrl)
                .into(binding.imgExploreMain)

            binding.txtExploreTitle.text = itemPost.txtTitle
            binding.txtExploreSubtitle.text = itemPost.txtSubtitle
            binding.txtDetail.text = itemPost.txtDetail

            // Trend
            Glide.with(itemView.context)
                .load(itemPost.imgUrlTrend)
                .transform(RoundedCornersTransformation(32, 4))
                .into(binding.imgTrendMain)

            binding.txtNumTrend.text = (adapterPosition + 1).toString()
            binding.txtTitleTrend.text = itemPost.txtTitleTrend
            binding.txtSubtitleTrend.text = itemPost.txtSubtitleTrend
            binding.txtInsightTrend.text = itemPost.txtInsight + "K"

            // interface Explore
            binding.cardExplore.setOnClickListener {
                itemEventsExplore.onItemClicked(itemPost)
            }
            // interface Trend
            binding.cardTrend.setOnClickListener {
                itemEventsTrend.onItemClickedTrend(itemPost)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        val binding =
            ItemExploreCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExploreViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: ArrayList<ItemPost>) {

        // set new data to list
        data.clear()
        data.addAll(newList)
        // فهماندن به ریسایکلر که اطلاعات تغییر کرده
        notifyDataSetChanged()
    }
}