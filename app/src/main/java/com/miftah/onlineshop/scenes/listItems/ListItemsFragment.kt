package com.miftah.onlineshop.scenes.listItems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miftah.onlineshop.R
import com.miftah.onlineshop.model.HomeListModel

class ListItemsFragment : Fragment(), ListItemAdapter.ItemAdapterCallback {

    private var listItem: ArrayList<HomeListModel> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataDummy()

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.home_list_rv)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = ListItemAdapter(listItem, this)
        recyclerView.adapter = adapter
    }

    private fun initDataDummy() {
        listItem = ArrayList()
        listItem.add(HomeListModel("Title 1", "12.000"))
        listItem.add(HomeListModel("Title 2", "15.000"))
        listItem.add(HomeListModel("Title 3", "42.000"))
        listItem.add(HomeListModel("Title 4", "22.000"))
        listItem.add(HomeListModel("Title 5", "122.000"))
        listItem.add(HomeListModel("Title 6", "12.000"))
        listItem.add(HomeListModel("Title 7", "15.000"))
        listItem.add(HomeListModel("Title 8", "42.000"))
        listItem.add(HomeListModel("Title 9", "22.000"))
        listItem.add(HomeListModel("Title 10", "122.000"))
        listItem.add(HomeListModel("Title 11", "12.000"))
        listItem.add(HomeListModel("Title 12", "15.000"))
        listItem.add(HomeListModel("Title 13", "42.000"))
        listItem.add(HomeListModel("Title 14", "22.000"))
        listItem.add(HomeListModel("Title 15", "122.000"))
    }

    override fun onCLick(v: View, data: HomeListModel) {
        Toast.makeText(context, "Clickk item: " + data.title, Toast.LENGTH_SHORT).show()
    }

}