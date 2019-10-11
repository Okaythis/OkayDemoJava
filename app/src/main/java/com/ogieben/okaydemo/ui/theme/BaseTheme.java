package com.ogieben.okaydemo.ui.theme;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import com.ogieben.okaydemo.R;
import com.protectoria.psa.dex.common.ui.PageTheme;

public class BaseTheme {
    PageTheme DEFAULT_PAGE_THEME  = new PageTheme();
    private Context context;

    public BaseTheme(Context context) {
        this.context = context;
        Resources it = context.getResources();
        PageTheme theme = DEFAULT_PAGE_THEME;

        theme.setActionBarBackgroundColor(it.getColor(R.color.primaryDarkColor));
        theme.setActionBarTextColor(it.getColor(R.color.primaryTextColor));
        theme.setScreenBackgroundColor(it.getColor(R.color.primaryColor));

        theme.setButtonBackgroundColor(it.getColor(R.color.secondaryColor));
        theme.setButtonTextColor(it.getColor(R.color.secondaryTextColor));

        theme.setPinNumberButtonTextColor(it.getColor(R.color.secondaryTextColor));
        theme.setPinNumberButtonBackgroundColor(it.getColor(R.color.secondaryLightColor));
        theme.setPinRemoveButtonBackgroundColor(it.getColor(R.color.secondaryLightColor));
        theme.setPinRemoveButtonTextColor(it.getColor(R.color.secondaryTextColor));
        theme.setPinTitleTextColor(it.getColor(R.color.primaryTextColor));
        theme.setPinValueTextColor(it.getColor(R.color.primaryTextColor));

        theme.setTitleTextColor(it.getColor(R.color.primaryTextColor));
        theme.setQuestionMarkColor(it.getColor(R.color.primaryLightColor));
        theme.setTransactionTypeTextColor(it.getColor(R.color.primaryTextColor));

        theme.setAuthInfoBackgroundColor(it.getColor(R.color.transaction_info_background));
        theme.setInfoSectionTitleColor(it.getColor(R.color.secondaryLightColor));
        theme.setInfoSectionValueColor(it.getColor(R.color.secondaryTextColor));
        theme.setFromTextColor(it.getColor(R.color.secondaryTextColor));
        theme.setMessageTextColor(it.getColor(R.color.secondaryTextColor));

        theme.setConfirmButtonBackgroundColor(it.getColor(R.color.secondaryLightColor));
        theme.setConfirmButtonTextColor(it.getColor(R.color.secondaryTextColor));
        theme.setCancelButtonBackgroundColor(it.getColor(R.color.primaryLightColor));
        theme.setCancelButtonTextColor(it.getColor(R.color.primaryTextColor));
        theme.setAuthConfirmationButtonBackgroundColor(it.getColor(R.color.secondaryColor));

        theme.setAuthConfirmationButtonBackgroundColor(it.getColor(R.color.secondaryColor));
        theme.setAuthConfirmationButtonTextColor(it.getColor(R.color.secondaryTextColor));
        theme.setAuthCancellationButtonBackgroundColor(it.getColor(R.color.primaryColor));
        theme.setAuthCancellationButtonTextColor(it.getColor(R.color.primaryTextColor));

        theme.setNameTextColor(it.getColor(R.color.secondaryTextColor));

        theme.setButtonBackgroundColor(it.getColor(R.color.primaryLightColor));
        theme.setButtonTextColor(it.getColor(R.color.primaryTextColor));
        theme.setInputTextColor(it.getColor(R.color.secondaryTextColor));
        theme.setInputSelectionColor(Color.GREEN);
        theme.setInputErrorColor(Color.RED);
        theme.setInputDefaultColor(Color.GRAY);
    }

    public PageTheme getDefaultTheme() {
        return DEFAULT_PAGE_THEME;
    }


}
