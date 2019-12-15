package com.example.savingmanager

import Saving
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.saving_row_layout.view.*

class ListSavingAdapter(private val activity: Activity ,
                    private val savingList: List<Saving>,
                    private val edittextSavingName:EditText,
                    private val edittextSavingAmount:EditText,
                    private val edittextMonthlyAmount:EditText,
                    private val edittextDesiredAmount:EditText,
                    private val textViewSavingID:TextView):BaseAdapter()
{
    internal val inflater:LayoutInflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView:View = inflater.inflate(R.layout.saving_row_layout,null)

        rowView.textViewSavingID.text=savingList[position].getId().toString()
        rowView.textViewSavingName.text=savingList[position].name
        rowView.textViewSavingTotalAmount.text=savingList[position].getSavingAmount().toString()
        rowView.textViewMonthlySavingAmount.text=savingList[position].monthlySavingAmount.toString()
        rowView.textViewDesiredAmount.text=savingList[position].getDesiredAmount().toString()
        rowView.textViewStartDateTime.text=savingList[position].getSavingDateTime()
        rowView.textViewElapsedMonths.text=savingList[position].getElapsedMonths().toString()
        rowView.textViewMonthsToReach.text=savingList[position].getMonthsToReachGoal().toString()

        rowView.setOnClickListener {
            textViewSavingID.setText(rowView.textViewSavingID.text.toString())
            edittextSavingName.setText(rowView.textViewSavingName.text.toString())
            edittextSavingAmount.setText(rowView.textViewSavingTotalAmount.text.toString())
            edittextMonthlyAmount.setText(rowView.textViewMonthlySavingAmount.text.toString())
            edittextDesiredAmount.setText(rowView.textViewDesiredAmount.text.toString())
        }

        return rowView
    }

    override fun getItem(position: Int): Any {
        return savingList[position]
    }

    override fun getItemId(position: Int): Long {
       return savingList[position].getId().toLong()
    }

    override fun getCount(): Int {
        return savingList.count()
    }

}