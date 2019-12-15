package com.eakurnikov.kaspressosample.view.flaky

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProviders
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.view.base.BaseActivity
import com.eakurnikov.kaspressosample.viewmodel.flaky.FlakyViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_flaky.*
import java.util.concurrent.TimeUnit

class FlakyActivity : BaseActivity<FlakyViewModel>() {

    companion object {
        private val BTN_DELAY = TimeUnit.SECONDS.toMillis(6)
        private val TV_DELAY = TimeUnit.SECONDS.toMillis(12)

        fun start(context: Context): Unit =
            context.startActivity(Intent(context, FlakyActivity::class.java))
    }

    override lateinit var viewModel: FlakyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flaky)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(FlakyViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        Handler(mainLooper)
            .apply { postDelayed({ tv_flaky_6.text = "TextView" }, TV_DELAY) }
            .apply { postDelayed({ btn_flaky_5.text = "BUTTON" }, BTN_DELAY) }
    }
}