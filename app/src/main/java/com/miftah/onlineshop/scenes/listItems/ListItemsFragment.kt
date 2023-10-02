package com.miftah.onlineshop.scenes.listItems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miftah.onlineshop.R
import com.miftah.onlineshop.databinding.FragmentListItemsBinding
import com.miftah.onlineshop.scenes.listItems.Model.DatumProduct

class ListItemsFragment : Fragment(), ListItemAdapter.ItemAdapterCallback {

    private var listItem: ArrayList<DatumProduct> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListItemAdapter

    private lateinit var binding: FragmentListItemsBinding

    private lateinit var viewModel: ListItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ListItemViewModel::class.java]
        viewModel.getListProduct()
        listItem = arrayListOf()

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.home_list_rv)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = ListItemAdapter(listItem, this)
        recyclerView.adapter = adapter

        setDataList()
    }

    private fun setDataList() {
        viewModel.listItems.observe(requireActivity(), Observer {
            listItem.clear()

            it.data.forEach { it1 ->
                listItem.add(it1)
            }
            adapter.notifyDataSetChanged()
        })
    }

    override fun onCLick(v: View, data: DatumProduct) {
        val direction = ListItemsFragmentDirections.actionNavigationHomeToDetailItemActivity(data)
        findNavController().navigate(direction)
    }
}