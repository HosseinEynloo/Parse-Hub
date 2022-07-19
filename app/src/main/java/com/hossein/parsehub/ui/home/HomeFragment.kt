package com.hossein.parsehub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.hossein.parsehub.databinding.FragmentHomBinding
import com.hossein.parsehub.models.ResponseItems

import com.hossein.parsehub.ui.home.adapters.ItemsAdapter
import com.hossein.parsehub.ui.home.adapters.ItemsAdapterPaging
import com.hossein.parsehub.viewmodel.HomeViewModel
import com.hossein.parsehub.viewmodel.HomeViewModelPaging
import com.hossein.utils.initRecyclerView
import com.hossein.utils.showInvisible
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomBinding


//    @Inject
//    lateinit var itemsAdapter: ItemsAdapter

//    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }
//    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var itemsAdapter: ItemsAdapterPaging
    private val viewModel: HomeViewModelPaging by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        // call api
//        viewModel.loadItems()



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            //Get Item List
//            viewModel.itemsList.observe(viewLifecycleOwner) { response ->
//                itemsAdapter.setData(response.docs as List<ResponseItems.Doc>)
//                //recyclerView
//                recyclerViewItemsList.initRecyclerView(
//                    LinearLayoutManager(
//                        requireContext(),
//                        LinearLayoutManager.VERTICAL,
//                        false
//                    ), itemsAdapter
//                )
//            }
//            //Loading
//            viewModel.loading.observe(viewLifecycleOwner) {
//                if (it) {
//                    itemsLoading.showInvisible(true)
//                    itemsListLay.showInvisible(false)
//                } else {
//                    itemsLoading.showInvisible(false)
//                    itemsListLay.showInvisible(true)
//                }
//            }

            // With Pagin
            // Load Data
           lifecycleScope.launchWhenCreated {
               viewModel.itemList.collect{
                   itemsAdapter.submitData(it)
               }

           }
            //Loading
            lifecycleScope.launchWhenCreated {
                itemsAdapter.loadStateFlow.collect{
                    val state=it.refresh
                    itemsLoading.isVisible=state is LoadState.Loading
                }

            }

            //RecyclerView init
            recyclerViewItemsList.apply {
                layoutManager=LinearLayoutManager(requireContext())
                adapter=itemsAdapter

            }









        }
    }

}