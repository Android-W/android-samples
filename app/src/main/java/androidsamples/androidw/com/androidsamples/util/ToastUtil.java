package androidsamples.androidw.com.androidsamples.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

public class ToastUtil {

	// field
	private static Toast mToast;
	private static final int DEFAULT_LENGTH = Toast.LENGTH_LONG;


	/*
     * -------------------------------------------------------------------
     * Common toast(default custom view)
     * -------------------------------------------------------------------
     */

	public static void show(Context context, int resId) {
		show(context, context.getString(resId), DEFAULT_LENGTH);
	}

	public static void show(Context context, String message) {
		show(context, message, DEFAULT_LENGTH);
	}

	public static void show(Context context, String message, int duration) {
		makeText(context, null, message, duration);
	}

	/**
	 * @param innerView inflate 된 뷰를 셋팅
	 * @param message 기본 toast 사용시 사용되는 message
	 * @param duration
	 * @param gravity toast의 위치를 설정 [gravity, x offset, y offset]
	 */
	private static void makeText(final Context context, final View innerView, final String message, final int duration, final int... gravity) {
		dismiss();

		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				mToast = Toast.makeText(context, message, duration);

				// custom view
				if (innerView != null) {
					mToast.setView(innerView);
				}

				// gravity
				if (gravity != null && gravity.length == 3) {
					mToast.setGravity(gravity[0], gravity[1], gravity[2]);
				}

				mToast.show();
			}
		});
	}

	public static void dismiss() {
		if (mToast != null) {
			mToast.cancel();
		}
	}
}
