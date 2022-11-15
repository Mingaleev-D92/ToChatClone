package com.example.tochatclone.ui.shats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tochatclone.data.local.UserHelper
import com.example.tochatclone.databinding.FragmentChatsBinding
import com.example.tochatclone.domain.ext.gone
import com.example.tochatclone.domain.ext.show


class ChatsFragment : Fragment() {

   private var mBinding: FragmentChatsBinding? = null
   private val binding: FragmentChatsBinding get() = mBinding!!

   private val chatsListAdapter = ChatsListAdapter(){
      Toast.makeText(requireContext(), it.chatName, Toast.LENGTH_SHORT).show()
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      mBinding = FragmentChatsBinding.inflate(inflater, container, false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      setupRecyclerView()
      handleChatList()
   }

   private fun setupRecyclerView() {
      binding.chatsListRv.apply {
         adapter = chatsListAdapter
      }

   }

   private fun handleChatList() {
      val chatList = UserHelper.chatsList
      if (chatList.isEmpty()){
         binding.chatsListRv.gone()
         binding.emptyMessage.show()
      }else{
         binding.emptyMessage.gone()
         binding.chatsListRv.show()

         chatsListAdapter.submitList(UserHelper.chatsList)
      }
   }


}