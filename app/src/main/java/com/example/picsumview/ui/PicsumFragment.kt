package com.example.picsumview.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.picsumview.databinding.FragmentPicsumBinding

class PicsumFragment : Fragment() {

    companion object {
        fun newInstance() = PicsumFragment()
    }

    private val viewModel: PicsumViewModel by viewModels()

    private var _binding: FragmentPicsumBinding? = null
    private val binding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPicsumBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}