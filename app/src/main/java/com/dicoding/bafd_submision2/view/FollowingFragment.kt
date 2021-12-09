package com.dicoding.bafd_submision2.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bafd_submision2.R
import com.dicoding.bafd_submision2.databinding.FragmentFollowingBinding
import com.dicoding.bafd_submision2.model.DataUser
import com.dicoding.bafd_submision2.network.FollowingResponse
import com.dicoding.bafd_submision2.viewModel.ListDataFollowingAdapter
import com.dicoding.bafd_submision2.viewmodel.FollowingViewModel

class FollowingFragment : Fragment() {

    companion object {
        val TAG = FollowingFragment::class.java.simpleName
        const val EXTRA_DETAIL = "extra_detail"
    }

    private val listData: ArrayList<FollowingResponse> = ArrayList()
    private lateinit var adapter: ListDataFollowingAdapter
    private lateinit var followingViewModel: FollowingViewModel

    private var fragmentFollowingBinding: FragmentFollowingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFollowingBinding.bind(view)
        fragmentFollowingBinding = binding


        adapter = ListDataFollowingAdapter(listData)
        followingViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)

        val dataUser = requireActivity().intent.getParcelableExtra<DataUser>(UserDetailActivity.EXTRA_DETAIL)!! as DataUser
//        val dataUser = requireActivity().intent.getParcelableExtra(EXTRA_DETAIL)!! as UserData
        binding.recyclerViewFollowing.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewFollowing.setHasFixedSize(true)
        binding.recyclerViewFollowing.adapter = adapter

        followingViewModel.UserFollowing(dataUser.login.toString())
        binding.progressbarFollowing.visibility = View.VISIBLE

        followingViewModel.getListFollowing().observe(requireActivity(), Observer { listFollowing ->
            if (listFollowing != null) {
                adapter.setData(listFollowing)
                binding.progressbarFollowing.visibility = View.INVISIBLE
            }
        })
    }


}
