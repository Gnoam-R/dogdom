package com.roh.dogdom.views.message.messages

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat.getRootWindowInsets
import androidx.fragment.app.viewModels
import com.roh.dogdom.R
import com.roh.dogdom.base.BaseFragment
import com.roh.dogdom.databinding.FragmentMessagesOnClickBinding
import com.roh.dogdom.util.enumUiColorPos
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesOnClickFragment : BaseFragment<FragmentMessagesOnClickBinding>(R.layout.fragment_messages_on_click) {

    private val viewModel by viewModels<MessagesOnClickViewModel>()

    override fun init() {
        SystemUiChangeColor(enumUiColorPos.totalUiBarBlack)
        initViewModelCallback()
        viewModel.initChatGpt()

        val etChattext = binding.etChat
        val etMyMessage = binding.tvMyMessage.text.toString()
        val etYourMessage = binding.tvYourMessage.text.toString()
//        val window = requireActivity().window
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

//        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        val imm = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(etChattext.windowToken, 0)
        val insets = getRootWindowInsets(binding.root)
        // Set an OnFocusChangeListener on the EditText


        etChattext.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
// If the EditText has focus, check if the keyboard is covering it
                val rect = Rect()
                etChattext.getGlobalVisibleRect(rect)
                val keyboardHeight = insets?.systemWindowInsetBottom
                if (rect.bottom < keyboardHeight!!) {
// If the keyboard is covering the EditText, move the layout up
                    val layout = mActivity.findViewById<ConstraintLayout>(R.id.my_layout)
                    layout.animate().translationY(-keyboardHeight.toFloat()).start()
                }
            } else {
// If the EditText loses focus, move the layout back down
                val layout = binding.root.findViewById<ConstraintLayout>(R.id.my_layout)
                layout.animate().translationY(0f).start()
            }
        }
//
//        etChattext.requestFocus()
//        inputMethodManager.showSoftInput(etChattext, InputMethodManager.SHOW_IMPLICIT)

        binding.sendButton.setOnClickListener {
            if(etChattext.text.toString() != ""){
                Log.e("inside", "inside")
                viewModel.requestChatGpt(etChattext.text.toString())
            }
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