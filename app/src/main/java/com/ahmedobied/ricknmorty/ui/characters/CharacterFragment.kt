package com.ahmedobied.ricknmorty.ui.characters

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ahmedobied.ricknmorty.R
import com.ahmedobied.ricknmorty.data.db.RickMortyDatabase
import com.ahmedobied.ricknmorty.data.network.CharacterNetworkDataSource
import com.ahmedobied.ricknmorty.data.network.CharacterNetworkDataSourceImpl
import com.ahmedobied.ricknmorty.data.network.RickAndMortyApiService
import com.ahmedobied.ricknmorty.data.repository.CharacterRepositoryImpl
import kotlinx.android.synthetic.main.character_fragment.*
import kotlinx.coroutines.launch

class CharacterFragment : Fragment() {
    private lateinit var viewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val dao = RickMortyDatabase(this.requireContext().applicationContext).getCharacterDao()
        val dataSource = CharacterNetworkDataSourceImpl(RickAndMortyApiService())
        val respository = CharacterRepositoryImpl(dataSource,dao)
        val factory = CharacterViewModelFactory(respository)
        viewModel = ViewModelProvider(this,factory).get(CharacterViewModel::class.java)
        // TODO: Use the ViewModel

        lifecycleScope.launch {
            viewModel.characters.await().observe(viewLifecycleOwner, Observer {
                characterTextView.text = it.toString()
            })
        }
    }

}