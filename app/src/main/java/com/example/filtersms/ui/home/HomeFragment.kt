package com.example.filtersms.ui.home

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.filtersms.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        var colCount = mutableListOf<String>()
        var message = mutableListOf<String>()
        //read sms here

        val parser = requireActivity().contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if(parser != null && parser.moveToFirst()) {
            do {
                    colCount.add(parser.getString(parser.getColumnIndexOrThrow("address")))
                    message.add(parser.getString(parser.getColumnIndexOrThrow("body")))
            } while(parser.moveToNext())
        }

        val listView: ListView = root.findViewById(R.id.list_view_inbox)
        val myListAdapter = MyListAdapter(requireActivity(),colCount.toTypedArray(),message.toTypedArray())
        listView.adapter = myListAdapter
        return root
    }
}