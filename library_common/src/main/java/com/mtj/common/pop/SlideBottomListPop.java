package com.mtj.common.pop;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mtj.common.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.ScaleConfig;
import razerdp.util.animation.TranslationConfig;


/**
 * 创建者 孟腾蛟
 * 创建时间 2019/03/21
 * 描述
 */
public class SlideBottomListPop extends BasePopupWindow {

    public SlideBottomListPop(Context context) {
        super(context);
        setPopupGravity(Gravity.BOTTOM);
        initView();
    }

    public SlideBottomListPop(Context context, int size) {
        super(context);
        setPopupGravity(Gravity.BOTTOM);
        int itemHeight = 40;
        int defaultHeight = 250;
        if (size * itemHeight < defaultHeight)
            setHeight(ConvertUtils.dp2px(size * itemHeight));
        else
            setHeight(ConvertUtils.dp2px(defaultHeight));
        initView();
    }

    private View view;
    private SlideBottomListAdapter mAdapter;
    private List<String> mList = new ArrayList<>();


    private void initView() {
        RecyclerView mRecyclerView = view.findViewById(R.id.recycler_slide_bottom_list);
        mAdapter = new SlideBottomListAdapter(R.layout.pop_slide_bottom_list_item, mList);
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                mOnItemClickListener.callBack(mList.get(position));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return AnimationHelper.asAnimation()
                .withTranslation(TranslationConfig.FROM_BOTTOM)
                .toShow();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation()
                .withScale(ScaleConfig.TOP_TO_BOTTOM)
                .toDismiss();
    }

    @Override
    public View onCreateContentView() {
        view = createPopupById(R.layout.pop_slide_bottom_list);
        return view;
    }

    public interface OnItemClickListener {
        void callBack(String value);
    }

    public void setList(List<String> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    public void setList(String... value) {
        mList.clear();
        mList.addAll(Arrays.asList(value));
        mAdapter.notifyDataSetChanged();
    }


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class SlideBottomListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SlideBottomListAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            if (mList.size() - 1 == helper.getAdapterPosition()) {
                helper.setVisible(R.id.view_line, false);
            } else {
                helper.setVisible(R.id.view_line, true);
            }
            helper.setText(R.id.tv_name_item, item);
        }

        @NotNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            addChildClickViewIds(R.id.tv_name_item);
            return super.onCreateViewHolder(parent, viewType);
        }
    }
}
