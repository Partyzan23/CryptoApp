package com.gmail.pashkovich.al.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.pashkovich.al.cryptoapp.databinding.ActivityCoinDetailBinding
import com.gmail.pashkovich.al.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private var _binding: ActivityCoinDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        fromSymbol?.let {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                getCoinDetailInfo(it)
            })
        }

    }

    private fun getCoinDetailInfo(coinPriceInfo: CoinPriceInfo) {
        with(binding) {
            tvPrice.text = coinPriceInfo.price.toString()
            tvFromSymbol.text = coinPriceInfo.fromSymbol
            tvToSymbol.text = coinPriceInfo.toSymbol
            tvMinPrice.text = coinPriceInfo.lowDay.toString()
            tvMaxPrice.text = coinPriceInfo.highDay.toString()
            tvLastMarket.text = coinPriceInfo.lastMarket
            tvLastUpdate.text = coinPriceInfo.getFormattedTime()
            Picasso.get().load(coinPriceInfo.getFullImageUrl()).into(ivLogoCoin)
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