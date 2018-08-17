package com.mine.shortvideo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mine.shortvideo.R;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class MessageFragment extends BaseFragment {
    @BindView(R.id.button2)
    Button button2;
    private Context context;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = getActivity();
    }

    @OnClick(R.id.button2)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2:
                RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE,"Android","Android");
                break;
        }
    }
}
