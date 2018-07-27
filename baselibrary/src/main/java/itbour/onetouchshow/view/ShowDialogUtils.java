package itbour.onetouchshow.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import itbour.onetouchshow.base.R;


/**
 * 加载对话框工具类
 */

public class ShowDialogUtils {
    private static final long TIME_DISMISS_DEFAULT = 1500;
    private Dialog mDialog;
    private View mDialogContentView;
    private TextView tv_loadText;
    private ImageView iv_loadImage;
    private ProgressBar pb_loadProgress;
    private Context mContext;
    private GifView gvloadProgress;

    public ShowDialogUtils(Context context) {
        this(context, 0);
    }

    @SuppressLint("InflateParams")
    public ShowDialogUtils(final Context context, int style) {
        this.mContext = context;
        mDialog = new Dialog(context, style);
        Window window = mDialog.getWindow();
        if (null != window) {
            window.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        }
        mDialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
        tv_loadText = (TextView) mDialogContentView.findViewById(R.id.tv_loading_text);
        iv_loadImage = (ImageView) mDialogContentView.findViewById(R.id.iv_load_image);
        pb_loadProgress = (ProgressBar) mDialogContentView.findViewById(R.id.pb_load_progress);
        gvloadProgress = (GifView) mDialogContentView.findViewById(R.id.gv_load_progress);
        // 设置Gif图片源
        gvloadProgress.setMovieResource(R.raw.loading_one_touch);
        mDialog.setCanceledOnTouchOutside(false);//调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用；
        mDialog.setCancelable(false);// 调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
        mDialog.setContentView(mDialogContentView);

    }


    /**
     * 显示加载的ProgressDialog
     */
    public void showProgressDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            pb_loadProgress.setVisibility(View.GONE);
            iv_loadImage.setVisibility(View.GONE);
            tv_loadText.setVisibility(View.GONE);
            mDialog.show();
        }
    }

    /**
     * 显示有加载文字ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param text 需要显示的文字
     */
    public void showProgressDialogWithText(String text) {
        if (TextUtils.isEmpty(text)) {
            showProgressDialog();
        } else {

            if (mDialog != null && !mDialog.isShowing()) {
                pb_loadProgress.setVisibility(View.GONE);
                iv_loadImage.setVisibility(View.GONE);
                tv_loadText.setText(text);
                tv_loadText.setVisibility(View.VISIBLE);
                if (mContext != null) {
                    Activity activity = (Activity) mContext;
                    if (!activity.isFinishing()) {
                        try {
                            mDialog.show();
                        } catch (Exception e) {
                            // e.printStackTrace();
                        }

                    }
                }

            }
        }
    }

    /**
     * 显示加载成功的ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param message 加载成功需要显示的文字
     * @param time    需要显示的时间长度(以毫秒为单位)
     */
    public void showProgressSuccess(String message, long time) {
        if (TextUtils.isEmpty(message)) {
            return;
        }

        if (mDialog != null && !mDialog.isShowing()) {
            pb_loadProgress.setVisibility(View.GONE);
            iv_loadImage.setBackgroundResource(R.drawable.ic_load_success_white);
            iv_loadImage.setVisibility(View.VISIBLE);
            tv_loadText.setText(message);
            tv_loadText.setVisibility(View.VISIBLE);
            if (mDialog != null) {
                mDialog.show();
            }


            mDialogContentView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mDialog != null) {
                        mDialog.dismiss();
                    }
                }
            }, time);
        }
    }

    /**
     * 显示加载成功的ProgressDialog，文字显示在ProgressDialog的下面
     * ProgressDialog默认消失时间为1秒(1000毫秒)
     *
     * @param message 加载成功需要显示的文字
     */
    public void showProgressSuccess(String message) {
        showProgressSuccess(message, TIME_DISMISS_DEFAULT);
    }

    /**
     * 显示加载失败的ProgressDialog，文字显示在ProgressDialog的下面
     *
     * @param message 加载失败需要显示的文字
     * @param time    需要显示的时间长度(以毫秒为单位)
     */
    public void showProgressFail(String message, long time) {
        if (TextUtils.isEmpty(message)) {
            return;
        }

        if (mDialog != null && !mDialog.isShowing()) {
            pb_loadProgress.setVisibility(View.GONE);
            iv_loadImage.setBackgroundResource(R.drawable.ic_load_fail_white);
            iv_loadImage.setVisibility(View.VISIBLE);
            tv_loadText.setText(message);
            tv_loadText.setVisibility(View.VISIBLE);
            mDialog.show();

            mDialogContentView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, time);
        }
    }

    /**
     * 显示加载失败的ProgressDialog，文字显示在ProgressDialog的下面
     * ProgressDialog默认消失时间为1秒(1000毫秒)
     *
     * @param message 加载成功需要显示的文字
     */
    public void showProgressFail(String message) {
        showProgressFail(message, TIME_DISMISS_DEFAULT);
    }

    /**
     * 隐藏加载的ProgressDialog
     */
    public void dismissProgressDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

    }

    /**
     * 隐藏加载的ProgressDialog
     */
    public void destoryProgressDialog() {
        Activity activity = (Activity) mContext;
        if (!activity.isFinishing()) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
                mDialog = null;
            }
        }

    }
}
