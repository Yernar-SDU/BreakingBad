package com.example.mvvm_template.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_template.R
import com.example.mvvm_template.databinding.FragmentHomeBinding
import com.example.mvvm_template.ui.home.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), CharacterAdapter.OnCharacterClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: CharacterAdapter
    private var firstOpenLoading = true
    private val TAG = javaClass.simpleName.toString()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObservers()
        viewModel.getCharacters()
    }

    private fun initView() {

        //Characters recyclerview init
        adapter = CharacterAdapter(this)
        binding.charactersRecyclerView.adapter = adapter
        binding.charactersRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        Log.i(TAG, "initView: ")


    }

    private fun setObservers() {
        //Sets recyclerview data if characters changes
        viewModel.characters.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            //Misses first observe of variable
            if (firstOpenLoading){
                firstOpenLoading = false
                return@observe
            }
            //Shows loading dialog fragment if data is not ready
            val action = HomeFragmentDirections.toLoadingDialogFragment()
            if (it){
                findNavController().navigate(action)
            }else{
                findNavController().popBackStack()
            }
        }
    }

    override fun onCharacterClick(character_id: Int) {
        val action = HomeFragmentDirections.toCharacterDetails(character_id)
        findNavController().navigate(action)
    }

    override fun onPause() {
        super.onPause()
        firstOpenLoading = true
    }


}