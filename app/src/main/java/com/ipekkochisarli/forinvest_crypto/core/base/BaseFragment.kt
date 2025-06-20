package com.ipekkochisarli.forinvest_crypto.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ipekkochisarli.forinvest_crypto.common.CustomErrorDialog
import java.util.concurrent.atomic.AtomicInteger

abstract class BaseFragment<VB : ViewBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    ) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    private val errorDialogCount = AtomicInteger(0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = this.inflater.invoke(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showErrorDialog(message: String, onDismiss: (() -> Unit)? = null) {
        if (errorDialogCount.incrementAndGet() == 1) {
            CustomErrorDialog(
                errorMessage = message,
                onClickButton = {
                    errorDialogCount.decrementAndGet()
                    onDismiss?.invoke()
                }
            ).show(parentFragmentManager, CustomErrorDialog.TAG)
        }
    }
}