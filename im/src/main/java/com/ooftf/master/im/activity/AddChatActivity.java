package com.ooftf.master.im.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.master.im.R;
import com.ooftf.master.im.databinding.ImActivityAddCartBinding;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMFriendshipManager;
import com.tencent.imsdk.TIMGroupManager;
import com.tencent.imsdk.TIMValueCallBack;
import com.tencent.imsdk.friendship.TIMFriendRequest;
import com.tencent.imsdk.friendship.TIMFriendResult;
import com.tencent.imsdk.friendship.TIMFriendStatus;
import com.tencent.qcloud.tim.uikit.modules.contact.ContactListView;
import com.tencent.qcloud.tim.uikit.utils.SoftKeyBoardUtil;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import org.jetbrains.annotations.Nullable;

import butterknife.ButterKnife;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/21 0021
 */
@Route(path = RouterPath.IM_ACTIVITY_ADD_CHAT)
public class AddChatActivity extends BaseActivity {

    ImActivityAddCartBinding binding;
    @Autowired(name = TUIKitConstants.GroupType.GROUP)
    boolean isGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.im_activity_add_cart);
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);

    }

    @Nullable
    @Override
    public View getToolbar() {
        return binding.addFriendTitlebar;
    }

    public void add(View view) {
        if (isGroup) {
            addGroup(view);
        } else {
            addFriend(view);
        }
    }

    public void addFriend(View view) {
        String id = binding.userId.getText().toString();
        if (TextUtils.isEmpty(id)) {
            return;
        }

        TIMFriendRequest timFriendRequest = new TIMFriendRequest(id);
        timFriendRequest.setAddWording(binding.addWording.getText().toString());
        timFriendRequest.setAddSource("android");
        TIMFriendshipManager.getInstance().addFriend(timFriendRequest, new TIMValueCallBack<TIMFriendResult>() {
            @Override
            public void onError(int i, String s) {
                ToastUtil.toastShortMessage("Error code = " + i + ", desc = " + s);
            }

            @Override
            public void onSuccess(TIMFriendResult timFriendResult) {
                switch (timFriendResult.getResultCode()) {
                    case TIMFriendStatus.TIM_FRIEND_STATUS_SUCC:
                        ToastUtil.toastShortMessage("成功");
                        break;
                    case TIMFriendStatus.TIM_FRIEND_PARAM_INVALID:
                        if (TextUtils.equals(timFriendResult.getResultInfo(), "Err_SNS_FriendAdd_Friend_Exist")) {
                            ToastUtil.toastShortMessage("对方已是您的好友");
                            break;
                        }
                    case TIMFriendStatus.TIM_ADD_FRIEND_STATUS_SELF_FRIEND_FULL:
                        ToastUtil.toastShortMessage("您的好友数已达系统上限");
                        break;
                    case TIMFriendStatus.TIM_ADD_FRIEND_STATUS_THEIR_FRIEND_FULL:
                        ToastUtil.toastShortMessage("对方的好友数已达系统上限");
                        break;
                    case TIMFriendStatus.TIM_ADD_FRIEND_STATUS_IN_SELF_BLACK_LIST:
                        ToastUtil.toastShortMessage("被加好友在自己的黑名单中");
                        break;
                    case TIMFriendStatus.TIM_ADD_FRIEND_STATUS_FRIEND_SIDE_FORBID_ADD:
                        ToastUtil.toastShortMessage("对方已禁止加好友");
                        break;
                    case TIMFriendStatus.TIM_ADD_FRIEND_STATUS_IN_OTHER_SIDE_BLACK_LIST:
                        ToastUtil.toastShortMessage("您已被被对方设置为黑名单");
                        break;
                    case TIMFriendStatus.TIM_ADD_FRIEND_STATUS_PENDING:
                        ToastUtil.toastShortMessage("等待好友审核同意");
                        break;
                    default:
                        ToastUtil.toastLongMessage(timFriendResult.getResultCode() + " " + timFriendResult.getResultInfo());
                        break;
                }
                finish();
            }
        });
    }

    public void addGroup(View view) {
        String id = binding.userId.getText().toString();
        if (TextUtils.isEmpty(id)) {
            return;
        }
        TIMGroupManager.getInstance().applyJoinGroup(id, binding.addWording.getText().toString(), new TIMCallBack() {

            @Override
            public void onError(int i, String s) {
                ToastUtil.toastShortMessage("Error code = " + i + ", desc = " + s);
            }

            @Override
            public void onSuccess() {
                ToastUtil.toastShortMessage("加群请求已发送");
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        SoftKeyBoardUtil.hideKeyBoard(binding.userId.getWindowToken());
    }
}
