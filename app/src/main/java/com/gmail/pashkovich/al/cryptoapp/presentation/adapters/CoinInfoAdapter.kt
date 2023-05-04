package com.gmail.pashkovich.al.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pashkovich.al.cryptoapp.R
import com.gmail.pashkovich.al.cryptoapp.databinding.ItemCoinInfoBinding
import com.gmail.pashkovich.al.cryptoapp.data.model.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        holder.bind(coin)
        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    inner class CoinInfoViewHolder(private val binding: ItemCoinInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coinPriceInfo: CoinPriceInfo) {
            binding.tvSymbols.text = String.format(
                context.resources.getString(R.string.symbols_template),
                coinPriceInfo.fromSymbol,
                coinPriceInfo.toSymbol
            )
            binding.tvPrice.text = coinPriceInfo.price.toString()
            binding.tvTimeLastUpdate.text = String.format(
                context.resources.getString(R.string.last_update_template),
                coinPriceInfo.getFormattedTime()
            )
            Picasso.get().load(coinPriceInfo.getFullImageUrl()).into(binding.ivLogoCoin)
        }

    }

    interface OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }

}