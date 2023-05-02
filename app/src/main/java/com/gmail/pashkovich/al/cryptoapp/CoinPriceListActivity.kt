package com.gmail.pashkovich.al.cryptoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.pashkovich.al.cryptoapp.adapters.CoinInfoAdapter
import com.gmail.pashkovich.al.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.gmail.pashkovich.al.cryptoapp.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private var _binding: ActivityCoinPriceListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener{
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol)
                startActivity(intent)
            }
        }

    }

}