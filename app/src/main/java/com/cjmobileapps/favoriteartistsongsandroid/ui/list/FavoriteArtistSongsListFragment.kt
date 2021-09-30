package com.cjmobileapps.favoriteartistsongsandroid.ui.list

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjmobileapps.favoriteartistsongsandroid.FavoriteArtistSongsApplication
import com.cjmobileapps.favoriteartistsongsandroid.R
import com.cjmobileapps.favoriteartistsongsandroid.data.FavoriteArtistSongsService
import com.cjmobileapps.favoriteartistsongsandroid.databinding.FragmentFavoriteArtistSongsListBinding
import com.cjmobileapps.favoriteartistsongsandroid.ui.FavoriteArtistSongsActivity
import com.cjmobileapps.favoriteartistsongsandroid.ui.dagger.DaggerFavoriteArtistSongsComponent
import com.cjmobileapps.favoriteartistsongsandroid.ui.viewmodel.FavoriteArtistSongsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class FavoriteArtistSongsListFragment : Fragment() {

    private val favoriteArtistSongsActivity by lazy { activity as? FavoriteArtistSongsActivity }

    private var binding: FragmentFavoriteArtistSongsListBinding? = null

    @Inject
    lateinit var favoriteArtistSongsService: FavoriteArtistSongsService

    private val favoriteArtistSongsListViewModel: FavoriteArtistSongsListViewModel by viewModels {
        FavoriteArtistSongsViewModelFactory(
            favoriteArtistSongsService
        )
    }

    override fun onAttach(context: Context) {

        val favoriteArtistSongsApplication =
            context.applicationContext as FavoriteArtistSongsApplication

        DaggerFavoriteArtistSongsComponent.builder()
            .favoriteArtistSongsApplicationComponent(favoriteArtistSongsApplication.favoriteArtistApplicationComponent)
            .build()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteArtistSongsListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.favoriteArtistSongsListSongsList?.layoutManager =
            LinearLayoutManager(requireContext())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding?.favoriteArtistSongsListSongsList?.addItemDecoration(dividerItemDecoration)

        val adapter = FavoriteArtistSongsListAdapter()
        binding?.favoriteArtistSongsListSongsList?.adapter = adapter

        binding?.favoriteArtistSongsListTextField?.editText?.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.songs = emptyList()
                adapter.notifyDataSetChanged()

                if (text != null && text.isNotEmpty()) {
                    favoriteArtistSongsListViewModel.getSongs(text.toString())
                } else {
                    binding?.favoriteArtistSongsListNoSongsFound?.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        favoriteArtistSongsListViewModel.songsLiveData.observe(
            viewLifecycleOwner,
            { songs ->
                if (songs.isNotEmpty()) {
                    adapter.songs = songs
                    adapter.notifyDataSetChanged()
                    binding?.favoriteArtistSongsListNoSongsFound?.visibility = View.GONE
                } else {
                    binding?.favoriteArtistSongsListNoSongsFound?.visibility = View.VISIBLE
                }
            }
        )

        favoriteArtistSongsListViewModel.songsAreLoading.observe(
            viewLifecycleOwner,
            { songsAreLoading ->
                if (songsAreLoading) {
                    binding?.favoriteArtistSongsListProgress?.visibility = View.VISIBLE
                } else {
                    binding?.favoriteArtistSongsListProgress?.visibility = View.GONE
                }
            }
        )

        favoriteArtistSongsListViewModel.showDefaultErrorMessageLiveData.observe(
            viewLifecycleOwner,
            { isError ->
                if(isError) {
                    favoriteArtistSongsActivity?.binding?.root?.let {
                        Snackbar.make(
                            it,
                            getString(R.string.error_occurred),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
