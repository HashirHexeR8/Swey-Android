package com.business.swey.features.onBoarding.fragments

import android.content.Context
import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.business.swey.R
import com.business.swey.core.views.OTPTextWatcher
import com.business.swey.core.base.FullScreenDialogBindingFragment
import com.business.swey.databinding.FragmentRecoveryCodeBinding
import com.business.swey.databinding.OtpViewBinding
import com.business.swey.features.home.HomeActivity

class RecoveryCodeBindingFragment : FullScreenDialogBindingFragment<FragmentRecoveryCodeBinding>() {

    override fun getLayout() = R.layout.fragment_recovery_code

    override fun setListeners(binding: FragmentRecoveryCodeBinding) {
        binding.ibBackButton.setOnClickListener {
            dismiss()
        }
        binding.layoutCodeOtp.root.setOnClickListener {
            initOTPView(binding.layoutCodeOtp.layoutOtp)
        }

        binding.btnVerify.setOnClickListener {
            val intent = Intent(activity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            activity?.finish()
        }
        binding.tvCodeResend.setOnClickListener {
            Toast.makeText(requireContext(), "Re-sent", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initOTPView(layoutCodeOtp : OtpViewBinding) {
        layoutCodeOtp.apply {
            val watcher1 = OTPTextWatcher.createWatcher(editText1, editText2, null)
            val watcher2 = OTPTextWatcher.createWatcher(editText2, editText3, editText1)
            val watcher3 = OTPTextWatcher.createWatcher(editText3, editText4, editText2)
            val watcher4 = OTPTextWatcher.createWatcher(editText4, editText5, editText3)
            val watcher5 = OTPTextWatcher.createWatcher(editText5, editText6, editText4)
            val watcher6 = OTPTextWatcher.createWatcher(editText6, null, editText5)

            editText1.addTextChangedListener(watcher1)
            editText2.addTextChangedListener(watcher2)
            editText3.addTextChangedListener(watcher3)
            editText4.addTextChangedListener(watcher4)
            editText5.addTextChangedListener(watcher5)
            editText6.addTextChangedListener(watcher6)

            editText1.requestFocus()
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText1, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    override fun initViews(binding: FragmentRecoveryCodeBinding) {}

    companion object {
        const val TAG = "RecoveryCodeFragment"

        fun getInstance(): RecoveryCodeBindingFragment = RecoveryCodeBindingFragment()
    }
}
