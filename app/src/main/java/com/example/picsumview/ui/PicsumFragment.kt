package com.example.picsumview.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picsumview.R
import com.example.picsumview.databinding.FragmentPicsumBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PicsumFragment : Fragment() {

    companion object {
        fun newInstance() = PicsumFragment()
    }

    private val viewModel: PicsumViewModel by viewModels()

    private lateinit var picsumLayoutManager: GridLayoutManager

    private val picsumAdapter = PicsumAdapter()

    private var _binding: FragmentPicsumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPicsumBinding.inflate(layoutInflater)
        picsumLayoutManager = GridLayoutManager(context, 2)
        binding.listPicsum.apply {
            layoutManager = picsumLayoutManager
            adapter = picsumAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        collectPictures()
    }

    private fun collectPictures() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest {
                    picsumAdapter.submitData(it)
                }
            }
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) { }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.btn_switch_layout -> {
                        if (picsumLayoutManager.spanCount == 1) {
                            picsumLayoutManager.spanCount = 2
                            menuItem.title = "Switch List View"
                        } else {
                            picsumLayoutManager.spanCount = 1
                            menuItem.title = "Switch Grid View"
                        }
                        picsumAdapter.notifyDataSetChanged()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
