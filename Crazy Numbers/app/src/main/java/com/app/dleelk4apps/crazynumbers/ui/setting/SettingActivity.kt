package com.app.dleelk4apps.crazynumbers.ui.setting

import android.os.Bundle
import android.app.Activity
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.ToggleButton
import com.app.dleelk4apps.crazynumbers.App.CrazyNumbersApplication
import com.app.dleelk4apps.crazynumbers.R
import com.app.dleelk4apps.crazynumbers.utils.Constants
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils.GameDifficultyType
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils.GameMatrixType
import kotlinx.android.synthetic.main.activity_setting.*


class SettingActivity : Activity() {

    private lateinit var matrixRadioGroup: RadioGroup
    private lateinit var difficultyRadioGroup: RadioGroup
    private lateinit var soundToggleButton: ToggleButton
    private lateinit var musicToggleButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        matrixRadioGroup = findViewById(R.id.matrixRadioGroup)
        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup)

        soundToggleButton = findViewById(R.id.soundToggleButton)
        musicToggleButton = findViewById(R.id.musicToggleButton)

        soundToggleButton.setOnCheckedChangeListener(SoundCheckedChangeListener())
        musicToggleButton.setOnCheckedChangeListener(MusicCheckedChangeListener())

        matrixRadioGroup.setOnCheckedChangeListener(MatrixCheckedChangeListener())
        difficultyRadioGroup.setOnCheckedChangeListener(DifficultyCheckedChangeListener())

        initSettingView()

        back.setOnClickListener {
            this.finish()
        }
    }


    private fun initSettingView() {
        val prefs = CrazyNumbersApplication.appInstance.getPrefsInstance()

        val difficultyValue = prefs.getInteger(Constants.GAME_DIFFICULTY_KEY)
        val matrixValue = prefs.getInteger(Constants.GAME_MATRIX_KEY)

        (difficultyRadioGroup.getChildAt(difficultyValue - GameDifficultyType.Easy.value) as RadioButton).isChecked = true
        (matrixRadioGroup.getChildAt(matrixValue - GameMatrixType.Mat3x3.value) as RadioButton).isChecked = true

        val isSoundActive = prefs.getBoolean(Constants.GAME_SOUND_KEY)
        val isMusicActive = prefs.getBoolean(Constants.GAME_MUSIC_DATA)

        soundToggleButton.isChecked = isSoundActive
        musicToggleButton.isChecked = isMusicActive

    }

    private fun saveMatSize(type: GameMatrixType) {
        val prefs = CrazyNumbersApplication.appInstance.getPrefsInstance()
        prefs.setInteger(Constants.GAME_MATRIX_KEY, type.value)
    }

    private fun saveDifficultyType(type: GameDifficultyType) {
        val prefs = CrazyNumbersApplication.appInstance.getPrefsInstance()
        prefs.setInteger(Constants.GAME_DIFFICULTY_KEY, type.value)
    }

    inner class MatrixCheckedChangeListener : RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
            when (p1) {
                R.id.mat3SettingCustomRadioButton -> saveMatSize(GameMatrixType.Mat3x3)
                R.id.mat4SettingCustomRadioButton -> saveMatSize(GameMatrixType.Mat4x4)
                R.id.mat5SettingCustomRadioButton -> saveMatSize(GameMatrixType.Mat5x5)
                R.id.mat6SettingCustomRadioButton -> saveMatSize(GameMatrixType.Mat6x6)
                R.id.mat7SettingCustomRadioButton -> saveMatSize(GameMatrixType.Mat7x7)
                else -> {
                    saveMatSize(GameMatrixType.Mat3x3)
                }
            }
        }
    }

    inner class DifficultyCheckedChangeListener : RadioGroup.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
            when (p1) {
                R.id.easySettingCustomRadioButton -> saveDifficultyType(GameDifficultyType.Easy)
                R.id.normalSettingCustomRadioButton -> saveDifficultyType(GameDifficultyType.Normal)
                R.id.mediumSettingCustomRadioButton -> saveDifficultyType(GameDifficultyType.Medium)
                R.id.hardSettingCustomRadioButton -> saveDifficultyType(GameDifficultyType.Hard)
                else -> {
                    saveDifficultyType(GameDifficultyType.Easy)
                }
            }
        }
    }

    inner class MusicCheckedChangeListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            val prefs = CrazyNumbersApplication.appInstance.getPrefsInstance()
            prefs.setBoolean(Constants.GAME_MUSIC_DATA, p1)
        }
    }

    inner class SoundCheckedChangeListener : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            val prefs = CrazyNumbersApplication.appInstance.getPrefsInstance()
            prefs.setBoolean(Constants.GAME_SOUND_KEY, p1)
        }
    }

}
