package com.ipekkochisarli.forinvest_crypto.features.onboarding.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewpager2.widget.ViewPager2
import com.ipekkochisarli.forinvest_crypto.R
import com.ipekkochisarli.forinvest_crypto.core.base.BaseFragment
import com.ipekkochisarli.forinvest_crypto.core.data.local.PreferencesManager
import com.ipekkochisarli.forinvest_crypto.databinding.FragmentOnboardingBinding
import com.ipekkochisarli.forinvest_crypto.features.onboarding.adapter.OnBoardingPagerAdapter
import com.ipekkochisarli.forinvest_crypto.features.onboarding.uimodel.OnBoardingModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding>(FragmentOnboardingBinding::inflate) {

    private lateinit var onboardingAdapter: OnBoardingPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator

    @Inject
    lateinit var preferences: PreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        initializeViews()
        setupOnboardingAdapter()

        if (preferences.isOnBoardingFinished()) {
            navigateToHomeScreen()
        }
        return binding.root
    }

    private fun initializeViews() {
        viewPager = binding.viewPagerOnboarding
        dotsIndicator = binding.dotsIndicator
    }

    private fun setupOnboardingAdapter() {
        val onboardingPages = listOf(
            OnBoardingModel(
                titleResId = R.string.onboarding_title_1,
                descriptionResId = R.string.onboarding_description_1,
                imageResId = R.drawable.onboarding_1
            ),
            OnBoardingModel(
                titleResId = R.string.onboarding_title_2,
                descriptionResId = R.string.onboarding_description_2,
                imageResId = R.drawable.onboarding_2
            )
        )

        onboardingAdapter = OnBoardingPagerAdapter(onboardingPages) {
            handleNextButtonClick()
        }
        viewPager.adapter = onboardingAdapter
        dotsIndicator.attachTo(viewPager)
    }

    private fun handleNextButtonClick() {
        val currentItem = viewPager.currentItem
        val lastIndex = onboardingAdapter.itemCount - 1

        if (currentItem == lastIndex) {
            markOnBoardingAsFinished()
            navigateToHomeScreen()
        } else {
            viewPager.currentItem = currentItem + 1
        }
    }

    private fun markOnBoardingAsFinished() {
        preferences.setOnBoardingFinished(true)
    }

    private fun navigateToHomeScreen(){
        findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment, null,
            navOptions {
                popUpTo(R.id.onBoardingFragment) {
                    inclusive = true
                }})
    }

}