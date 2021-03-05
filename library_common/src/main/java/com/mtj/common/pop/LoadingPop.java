package com.mtj.common.pop;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.mtj.common.R;

import razerdp.basepopup.BasePopupWindow;

public class LoadingPop extends BasePopupWindow {

    public LoadingPop(Context context) {
        super(context);
        setOutSideDismiss(false);
        ImageView ivLoading = findViewById(R.id.ivLoading);
        ivLoading.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.progress_anim));
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.pop_loading_layout);
    }
}
