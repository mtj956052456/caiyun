package com.mtj.common.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.mtj.common.interf.PermissionCallBack;
import com.mtj.common.swipeback.ViewDragHelper;
import com.mtj.common.swipeback.app.SwipeBackActivity;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public abstract class BaseActivity extends SwipeBackActivity {

    protected Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        beforeSetView();
        setContentView(setView());
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);//注册路由
        ActivityHolder.addActivity(this);
        setSwipeBackEnable(true);
        getSwipeBackLayout().setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
        afterBinder();                                                     //界面控件初始化
    }


    public void intoActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }

    public void intoActivityWithBundle(String path, Bundle bundle) {
        ARouter.getInstance().build(path).with(bundle).navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
            }

            @Override
            public void onLost(Postcard postcard) {
                finish();
            }

            @Override
            public void onArrival(Postcard postcard) {
            }

            @Override
            public void onInterrupt(Postcard postcard) {
            }
        });
    }

    public void finishIntoActivity(String path) {
        ARouter.getInstance().build(path).navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {

            }

            @Override
            public void onLost(Postcard postcard) {
                finish();
            }

            @Override
            public void onArrival(Postcard postcard) {

            }

            @Override
            public void onInterrupt(Postcard postcard) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    //设置界面之前做的事
    protected void beforeSetView() {

    }

    protected abstract int setView();

    //设置界面
    protected void afterBinder() {
        //if (Build.VERSION.SDK_INT >= 21) {
        //    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        //    getWindow().setStatusBarColor(Color.TRANSPARENT);
        //}
    }

    /**
     * 检查微信是否登录
     *
     * @return
     */
    public boolean checkWechatLogin() {
        //        String usergid = SPWXUtil.get(SPWXUtil.WX_USER_GID);
        //        if (usergid == null || "".equals(usergid) || "null".equals(usergid)) // 如果没有token说明没有微信登录过
        //            return false;
        //        else
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v instanceof EditText) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    public void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示键盘
     *
     * @param et 输入焦点
     */
    public void showInput(EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏键盘
     */
    public void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    public void checkPermission(final PermissionCallBack callBack, String... permissions) {
        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .onGranted(new com.yanzhenjie.permission.Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        callBack.success();
                    }
                }).onDenied(new com.yanzhenjie.permission.Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                ToastUtils.showShort("禁用权限部分功能将无法使用");
            }
        }).start();
    }

    public void checkPermission(final PermissionCallBack callBack, String[]... groups) {
        AndPermission.with(this)
                .runtime()
                .permission(groups)
                .onGranted(new com.yanzhenjie.permission.Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        callBack.success();
                    }
                }).onDenied(new com.yanzhenjie.permission.Action<List<String>>() {
            @Override
            public void onAction(List<String> data) {
                ToastUtils.showShort("禁用权限部分功能将无法使用");
            }
        }).start();
    }

    /**
     * 设置最大系统字体为标准字体
     *
     * @return
     */
    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = 1.0f;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return resources;
    }

    @Override
    protected void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        ActivityHolder.removeActivity(this);
        super.onDestroy();
    }
}
