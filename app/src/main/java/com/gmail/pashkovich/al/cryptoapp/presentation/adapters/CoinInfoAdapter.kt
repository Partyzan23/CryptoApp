package com.gmail.pashkovich.al.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.pashkovich.al.cryptoapp.R
import com.gmail.pashkovich.al.cryptoapp.databinding.ItemCoinInfoBinding
import com.gmail.pashkovich.al.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoViewHolder>() {

    var coinInfoList: List<CoinInfo> = listOf()
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
        with(holder.binding) {
            tvSymbols.text = String.format(
                context.resources.getString(R.string.symbols_template),
                coin.fromSymbol,
                coin.toSymbol
            )
            tvPrice.text = coin.price.toString()
            tvTimeLastUpdate.text = String.format(
                context.resources.getString(R.string.last_update_template),
                coin.lastUpdate
            )
            Picasso.get().load(coin.imageUrl)
                .into(ivLogoCoin)
            root.setOnClickListener {
                onCoinClickListener?.onCoinClick(coin)
            }
        }

    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }

}