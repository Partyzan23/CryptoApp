package com.gmail.pashkovich.al.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.gmail.pashkovich.al.cryptoapp.domain.CoinInfo

class CoinInfoDiffCallBack : DiffUtil.ItemCallback<CoinInfo>() {
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}