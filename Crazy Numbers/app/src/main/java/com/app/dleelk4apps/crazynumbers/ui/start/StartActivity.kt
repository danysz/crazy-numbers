package com.app.dleelk4apps.crazynumbers.ui.start

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.app.dleelk4apps.crazynumbers.R
import com.app.dleelk4apps.crazynumbers.ui.views.GameCustomStartButtonView
import com.app.dleelk4apps.crazynumbers.ui.game.GameActivity
import com.app.dleelk4apps.crazynumbers.ui.help.HelpActivity
import com.app.dleelk4apps.crazynumbers.ui.setting.SettingActivity
import com.irozon.alertview.AlertActionStyle
import com.irozon.alertview.AlertStyle
import com.irozon.alertview.AlertTheme
import com.irozon.alertview.AlertView
import com.irozon.alertview.objects.AlertAction

class StartActivity : AppCompatActivity() {

    private var playButtonView: GameCustomStartButtonView? = null
    private var settingButtonView: GameCustomStartButtonView? = null
    private var helpButtonView: GameCustomStartButtonView? = null
    private var aboutButtonView: GameCustomStartButtonView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        initView()
    }

    private fun initView() {
        playButtonView = findViewById(R.id.playGameCustomStartButtonView)
        settingButtonView = findViewById(R.id.settingGameCustomStartButtonView)
        helpButtonView = findViewById(R.id.helpGameCustomStartButtonView)
        aboutButtonView = findViewById(R.id.aboutGameCustomStartButtonView)

        playButtonView?.setOnClickListener(PlayButtonViewClickListener())
        settingButtonView?.setOnClickListener(SettingButtonViewClickListener())
        helpButtonView?.setOnClickListener(HelpButtonViewClickListener())
        aboutButtonView?.setOnClickListener(AboutButtonViewClickListener())
    }

    private fun startGameActivity() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun openSettingActivity() {
        val intent = Intent(this, SettingActivity::class.java)
        startActivity(intent)
    }

    private fun openHelpActivity() {
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }

    private fun openAboutView() {
        val alert = AlertView("Crazy Numbers", "Crazy Numbers\nVersion 1.0 \nDeveloper : Crazy Numbers\ncrazynumbers@gmail.com\nCopyright\u00a9Crazy Numbers\n2019", AlertStyle.DIALOG)
        alert.addAction(AlertAction("Close", AlertActionStyle.POSITIVE) {

        })

        alert.setTheme(AlertTheme.LIGHT)
        alert.show(this)
    }

    inner class AboutButtonViewClickListener : View.OnClickListener {
        override fun onClick(p0: View?) {
            openAboutView()
        }
    }

    inner class HelpButtonViewClickListener : View.OnClickListener {
        override fun onClick(p0: View?) {
            openHelpActivity()
        }
    }

    inner class SettingButtonViewClickListener : View.OnClickListener {
        override fun onClick(p0: View?) {
            openSettingActivity()
        }
    }

    inner class PlayButtonViewClickListener : View.OnClickListener {

        override fun onClick(p0: View?) {
            startGameActivity()
        }
    }
}
