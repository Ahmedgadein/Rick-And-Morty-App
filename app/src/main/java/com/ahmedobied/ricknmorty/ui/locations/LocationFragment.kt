package com.ahmedobied.ricknmorty.ui.locations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedobied.ricknmorty.R
import com.ahmedobied.ricknmorty.data.db.entities.LocationEntity
import com.ahmedobied.ricknmorty.generic.listener.OnRecyclerObjectClickListener
import kotlinx.android.synthetic.main.location_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class LocationFragment : Fragment(), DIAware {
    private lateinit var viewModel: LocationViewModel
    override val di by di()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.location_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory: LocationViewModelFactory by instance()
        viewModel = ViewModelProvider(this, factory).get(LocationViewModel::class.java)
        setupUI()
    }

    private fun setupUI() {
        val locationAdapter = LocationAdapter().apply {
            setListener(listener)
        }

        recyclerview_location.apply {
            layoutManager = LinearLayoutManager(this@LocationFragment.requireContext())
            adapter = locationAdapter
        }

        lifecycleScope.launch {
            viewModel.locations.await().observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer
                Log.i("FetchNeeded", "Length of locations ${it.size}")
                locationAdapter.updateItems(it)
            })
        }
    }

    private val listener: OnRecyclerObjectClickListener<LocationEntity> =
        object : OnRecyclerObjectClickListener<LocationEntity> {
            override fun onItemClicked(item: LocationEntity) {
                onLocationClicked(item)
            }
        }

    private fun onLocationClicked(location: LocationEntity) {
        Toast.makeText(context, "Clicked location ${location.name}", Toast.LENGTH_SHORT).show()
    }
}