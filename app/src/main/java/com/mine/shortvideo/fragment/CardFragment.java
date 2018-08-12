package com.mine.shortvideo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mine.shortvideo.R;
import com.mine.shortvideo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：created by lun.zhang on 8/8/2018 14:42
 * 邮箱：zhanglun_study@163.com
 */
public class CardFragment extends Fragment {
    private static final String INDEX_KEY = "index_key";
    @BindView(R.id.rc_rate)
    RatingBar rcRate;
    @BindView(R.id.tv_match)
    TextView tvMatch;
    @BindView(R.id.card_view)
    CardView cardView;
    Unbinder unbinder;

    public static CardFragment newInstance(int index) {
        CardFragment fragment = new CardFragment();
        Bundle bdl = new Bundle();
        bdl.putInt(INDEX_KEY, index);
        fragment.setArguments(bdl);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_card, container, false);
        final Bundle bundle = getArguments();
        if (bundle != null) {
//            cardNumTv.setText(bundle.getInt(INDEX_KEY, 0) + "");
        }
//        cardNumTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "点击了" + bundle.getInt(INDEX_KEY, 0) + "", Toast.LENGTH_SHORT).show();
//            }
//        });
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_match)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_match:
                ToastUtils.show("匹配");
                break;
        }
    }
}
