package com.gmail.pashkovich.al.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gmail.pashkovich.al.cryptoapp.databinding.ActivityCoinDetailBinding
import com.gmail.pashkovich.al.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        fromSymbol?.let {
            viewModel.getDetailInfo(fromSymbol).observe(this) {
                getCoinDetailInfo(it)
            }
        }

    }

    private fun getCoinDetailInfo(coinPriceInfo: CoinInfo) {
        with(binding) {
            tvPrice.text = coinPriceInfo.price.toString()
            tvFromSymbol.text = coinPriceInfo.fromSymbol
            tvToSymbol.text = coinPriceInfo.toSymbol
            tvMinPrice.text = coinPriceInfo.lowDay.toString()
            tvMaxPrice.text = coinPriceInfo.highDay.toString()
            tvLastMarket.text = coinPriceInfo.lastMarket
            tvLastUpdate.text = coinPriceInfo.lastUpdate
            Picasso.get().load(coinPriceInfo.imageUrl).into(ivLogoCoin)
        }

    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}