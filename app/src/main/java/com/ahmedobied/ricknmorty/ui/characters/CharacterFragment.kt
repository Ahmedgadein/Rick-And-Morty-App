package com.ahmedobied.ricknmorty.ui.characters

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ahmedobied.ricknmorty.R
import com.ahmedobied.ricknmorty.data.db.entities.CharacterEntity
import com.ahmedobied.ricknmorty.generic.listener.OnRecyclerObjectClickListener
import com.ahmedobied.ricknmorty.generic.listener.PaginationScrollListener
import kotlinx.android.synthetic.main.character_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class CharacterFragment : Fragment(), DIAware {
    private lateinit var viewModel: CharacterViewModel
    override val di by di()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.character_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory: CharacterViewModelFactory by instance()
        viewModel = ViewModelProvider(this, factory).get(CharacterViewModel::class.java)
        setupUI()
    }

    private fun setupUI() {
        val charactersAdapter = CharactersAdapter().apply {
            setListener(listener)
        }

        val gridLayoutManager = GridLayoutManager(this@CharacterFragment.requireContext(), 2)

        recyclerview_characters.apply {
            layoutManager = gridLayoutManager
            adapter = charactersAdapter
        }


        recyclerview_characters.setOnScrollListener(object :
            PaginationScrollListener(gridLayoutManager) {
            override fun loadMoreItems() {
                character_progress_bar.visibility = View.VISIBLE
                viewModel.fetchNextPage()
            }
        })

        lifecycleScope.launch {
            viewModel.characters.await().observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer

                character_progress_bar.visibility = View.GONE
                charactersAdapter.updateItems(it)
            })
        }
    }

    private fun onCharacterClicked(character: CharacterEntity) {
        Toast.makeText(this.requireContext(), "Clicked ${character.name}", Toast.LENGTH_SHORT)
            .show()
    }

    private val listener = object:OnRecyclerObjectClickListener<CharacterEntity>{
        override fun onItemClicked(item: CharacterEntity) {
            onCharacterClicked(item)
        }
    }
}