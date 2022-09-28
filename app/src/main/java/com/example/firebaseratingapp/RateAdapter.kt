package com.example.firebaseratingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class RateAdapter(val mCtxt: Context, val layoutResId: Int, val RateList:List<Rate>)
    :ArrayAdapter<Rate>(mCtxt,layoutResId,RateList)

{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater=LayoutInflater.from(mCtxt);
        val view: View= layoutInflater.inflate(layoutResId,null);

        val txt_View= view.findViewById<TextView>(R.id.txt_View);

        val rate= RateList[position];
        txt_View.text=rate.name;
        return view;
    }

    }