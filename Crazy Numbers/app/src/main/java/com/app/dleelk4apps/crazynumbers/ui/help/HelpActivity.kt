package com.app.dleelk4apps.crazynumbers.ui.help

import android.app.Activity
import android.os.Bundle
import com.app.dleelk4apps.crazynumbers.R
import kotlinx.android.synthetic.main.activity_help.*


class HelpActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        back.setOnClickListener {
            this.finish()
        }
    }
}
