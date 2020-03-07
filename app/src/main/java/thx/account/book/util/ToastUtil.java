package thx.account.book.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import thx.account.book.BaseApp;

/**
 * 吐司工具类
 *
 * @author thkcheng
 */

public class ToastUtil {

    private static Context getContext() {
        return BaseApp.getContext();
    }

    public static void showToast(String message) {
        showToast(getContext(), message, Toast.LENGTH_SHORT);
    }

    public static void showToast(int resId) {
        showToast(getContext(), BaseApp.getContext().getString(resId), Toast.LENGTH_SHORT);
    }

    private static void showToast(final Context context, final String msg, final int duration) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context, msg, duration).show();
            return;
        }
        //保证在子线程中也能toast
        new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, msg, duration).show());
    }

}
