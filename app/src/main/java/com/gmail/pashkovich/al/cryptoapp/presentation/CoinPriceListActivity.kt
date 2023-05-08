package com.gmail.pashkovich.al.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.pashkovich.al.cryptoapp.R
import com.gmail.pashkovich.al.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.gmail.pashkovich.al.cryptoapp.databinding.FragmentCoinDetailBinding
import com.gmail.pashkovich.al.cryptoapp.domain.CoinInfo
import com.gmail.pashkovich.al.cryptoapp.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                if (isOnePaneMode()) {
                    launchCoinDetailActivity(coinPriceInfo.fromSymbol)
                } else {
                    launchDetailFragment(coinPriceInfo.fromSymbol)
                }

            }
        }
    }

    private fun launchCoinDetailActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    private fun isOnePaneMode(): Boolean {
        return binding.fragmentContainer == null
    }

}