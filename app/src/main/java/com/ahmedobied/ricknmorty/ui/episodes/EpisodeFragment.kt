package com.ahmedobied.ricknmorty.ui.episodes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmedobied.ricknmorty.R
import com.ahmedobied.ricknmorty.data.db.entities.EpisodeEntity
import com.ahmedobied.ricknmorty.generic.listener.OnRecyclerObjectClickListener
import kotlinx.android.synthetic.main.episode_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class EpisodeFragment : Fragment(), DIAware {
    override val di: DI by di()
    private lateinit var viewModel: EpisodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.episode_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory by instance<EpisodeViewModelFactory>()
        viewModel = ViewModelProvider(this, factory).get(EpisodeViewModel::class.java)
        setupUI()
    }

    private fun setupUI() {
        val episodeAdapter = EpisodeAdapter().apply {
            setListener(listener)
        }

        recyclerview_episode.apply {
            adapter = episodeAdapter
            layoutManager = LinearLayoutManager(this@EpisodeFragment.requireContext())
        }

        lifecycleScope.launch {
            viewModel.episodes.await().observe(viewLifecycleOwner, Observer {
                if (it == null) return@Observer

                episodeAdapter.updateItems(it)
            })
        }
    }

    private fun onEpisodeClicked(episode: EpisodeEntity) {
        Toast.makeText(this.requireContext(), "Clicked ${episode.episode}", Toast.LENGTH_SHORT)
            .show()
    }

    private val listener = object : OnRecyclerObjectClickListener<EpisodeEntity> {
        override fun onItemClicked(item: EpisodeEntity) {
            onEpisodeClicked(item)
        }
    }
}