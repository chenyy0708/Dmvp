package com.huitian.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.huitian.chen.R;
import com.swifty.toptoastbar.BottomToast;
import com.swifty.toptoastbar.Toast;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class TUtils {

    /**
     * 全局Toast
     */
    private static Toast toast;

    private static Toast initToast(Context context, String message, long duration) {
        if (toast == null) {
            toast = BottomToast.make(context, message, duration);
            toast.setPosition(Toast.Position.TOP)
                    .setBackground(ContextCompat.getColor(context, R.color.red));
        } else {
            toast.setText(message);
            toast.setTime(duration);
        }
        return toast;
    }

    /**
     * 顶部提示
     *
     * @param msg     消息内容
     * @param context 上下文
     */
    public static void show(Context context, String msg) {
        initToast(context, msg, android.widget.Toast.LENGTH_SHORT).show();
    }
}
