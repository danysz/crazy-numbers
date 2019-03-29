package com.app.dleelk4apps.crazynumbers.ui.game.adapter

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.AppCompatTextView
import android.widget.BaseAdapter
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.app.dleelk4apps.crazynumbers.R
import com.app.dleelk4apps.crazynumbers.data.GameGridItemData
import com.app.dleelk4apps.crazynumbers.utils.FontUtils
import com.app.dleelk4apps.crazynumbers.utils.GameSettingUtils


/**
 * Created by OmarAyed on 03/01/2018.
 */
class GameGridViewAdapter(context: Context, data: ArrayList<GameGridItemData>, gameSetting: GameSettingUtils) : BaseAdapter() {


    private val context = context
    private val layoutInflater = LayoutInflater.from(context)
    private var data = data
    private var gameSetting = gameSetting

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (p1 == null) {
            view = layoutInflater.inflate(R.layout.game_grid_item_row, p2, false)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder

        } else {
            view = p1
            viewHolder = view.tag as ViewHolder
        }

        val item = data[p0]
        if(item.isUsed) {
           // viewHolder.gameItemCustomTextView?.text = ""
            viewHolder.gameItemCustomTextView?.setBackgroundDrawable(context.getDrawable(R.drawable.used_radius_corner))

        } else {
            if (item.isSelected) {
                viewHolder.gameItemCustomTextView?.setBackgroundDrawable(context.getDrawable(R.drawable.selected_radius_corner))
                view?.alpha = 0f
                view?.animate()?.alpha(1f)?.setDuration(50)?.start()
            }
            else {
                viewHolder.gameItemCustomTextView?.setBackgroundDrawable(context.getDrawable(R.drawable.unselected_radius_corner))
            }

            viewHolder.gameItemCustomTextView?.text = when(item.gameItemType) {
                GameGridItemData.GameItemType.Number ->  (item.value as Int).toString()
                GameGridItemData.GameItemType.Operator -> item.value as String
            }
        }


        viewHolder.gameItemCustomTextView?.typeface = FontUtils.getFont(context)

        return view!!
    }

    fun refreshView() {
        notifyDataSetChanged()
    }

    fun updateData(data: ArrayList<GameGridItemData>) {
        this.data = data
        refreshView()
    }

    internal class ViewHolder(row: View?) {
        val gameItemCustomTextView = row?.findViewById<AppCompatTextView>(R.id.gameItemCustomTextView)
    }

}