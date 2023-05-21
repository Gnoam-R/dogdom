package com.roh.dogdom.views.message.messages

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat.getRootWindowInsets
import androidx.fragment.app.viewModels
import com.roh.dogdom.R
import com.roh.dogdom.api.ChatGptResponse
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentMessagesOnClickBinding
import com.roh.dogdom.util.enumUiColorPos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesOnClickFragment : BaseFragment<FragmentMessagesOnClickBinding>(R.layout.fragment_messages_on_click) {

    private val viewModel by viewModels<MessagesOnClickViewModel>()
    private var imm : InputMethodManager? = null
    private var gptResponse : ChatGptResponse? = null

    var etChattext : EditText? = null
    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarBlack)
        initViewModelCallback()
        viewModel.initChatGpt()

        etChattext = binding.etChat
        val etMyMessage = binding.tvMyMessage.text.toString()
        val etYourMessage = binding.tvYourMessage.text.toString()
//        val window = requireActivity().window
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

//        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        binding.sendButton.setOnClickListener {
            if(etChattext!!.text.toString() != ""){
                Log.e("inside", "inside")
                gptResponse = viewModel.requestChatGpt(etChattext!!.text.toString())
            }
            if(gptResponse != null) {
                Log.e("gptResponse", gptResponse!!.choices.get(0).message.content)
                binding.tvYourMessage.text = gptResponse!!.choices.get(0).message.content
            }
        }
    }

    fun hideKeyboard(v : View) {
        if(v != null) {
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
    fun showKeyboard(v : View) {
        if(v != null) {
            imm?.showSoftInput(etChattext!!, 0)
        }
    }

    private fun initViewModelCallback() {
        with(viewModel) {
//            goMain.observe(viewLifecycleOwner, Observer {
//                val direction: NavDirections = com.roh.dogdom.views.login.LoginFragmentDirections.actionLoginFragmentToMasterMainFragment()
//                findNavController().navigate(direction)
//            })
//
        }
    }

}