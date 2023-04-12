package com.example.picsumview.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picsumview.databinding.FragmentPicsumBinding
import kotlinx.coroutines.launch

class PicsumFragment : Fragment() {

    companion object {
        fun newInstance() = PicsumFragment()
    }

    private val viewModel: PicsumViewModel by viewModels()

    private val picsumAdapter: PicsumAdapter = PicsumAdapter()

    private var _binding: FragmentPicsumBinding? = null
    private val binding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPicsumBinding.inflate(layoutInflater)
        binding.listPicsum.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = picsumAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectPictures()
    }

    private fun collectPictures() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    picsumAdapter.pictures = it
                    picsumAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
