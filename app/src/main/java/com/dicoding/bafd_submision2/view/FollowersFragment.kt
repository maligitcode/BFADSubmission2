package com.dicoding.bafd_submision2.view

import android.os.Bundle
import android.service.autofill.UserData
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bafd_submision2.R
import com.dicoding.bafd_submision2.databinding.FragmentFollowersBinding
import com.dicoding.bafd_submision2.model.DataUser
import com.dicoding.bafd_submision2.network.FollowersResponse
import com.dicoding.bafd_submision2.viewModel.ListDataFollowerAdapter
import com.dicoding.bafd_submision2.viewmodel.FollowerViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [Followers.newInstance] factory method to
 * create an instance of this fragment.
 */
class FollowersFragment : Fragment() {

    companion object {
        val TAG = FollowersFragment::class.java.simpleName
        const val EXTRA_DETAIL = "extra_detail"
    }

    private val listData: ArrayList<FollowersResponse> = ArrayList()
    private lateinit var adapter: ListDataFollowerAdapter
    private lateinit var followerViewModel: FollowerViewModel

    private var fragmentFollowersBinding: FragmentFollowersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFollowersBinding.bind(view)
        fragmentFollowersBinding = binding


        adapter = ListDataFollowerAdapter(listData)
        followerViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(FollowerViewModel::class.java)

        val dataUser = requireActivity().intent.getParcelableExtra<DataUser>(UserDetailActivity.EXTRA_DETAIL)!! as DataUser
//        val dataUser = requireActivity().intent.getParcelableExtra(EXTRA_DETAIL)!! as UserData
        binding.recyclerViewFollowers.layoutManager = LinearLayoutManager(activity)
        binding.recyclerViewFollowers.setHasFixedSize(true)
        binding.recyclerViewFollowers.adapter = adapter

        followerViewModel.UserFollower(dataUser.login.toString())
        binding.progressbarFollowers.visibility = View.VISIBLE

        followerViewModel.getListFollower().observe(requireActivity(), Observer { listFollower ->
            if (listFollower != null) {
                adapter.setData(listFollower)
                binding.progressbarFollowers.visibility = View.INVISIBLE
            }
        })
    }


}
