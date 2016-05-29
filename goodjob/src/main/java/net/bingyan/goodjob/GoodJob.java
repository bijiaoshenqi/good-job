package net.bingyan.goodjob;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.widget.Space;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.bingyan.goodjob.path.IAnimation;
import net.bingyan.goodjob.path.StraightLineAnimation;

/**
 * @author Chris Wong
 */
public class GoodJob implements IGoodJob, Runnable {
    private static final String TAG = GoodJob.class.getSimpleName();

    private static final int DEFAULT_DURATION = 1000;
    private static final Interpolator DEFAULT_INTERPOLATOR = new AccelerateInterpolator();
    private static final IAnimation DEFAULT_ANIMATION = new StraightLineAnimation(0, -60);

    private Context context; // application context in case of memory leak
    private Handler handler;

    private PopupWindow popupWindow;
    private View contentView;
    private int duration;
    private Interpolator interpolator;
    private IAnimation animation;

    private View target;
    private boolean isShowing;
    private long startTime;

    public GoodJob(@NonNull Context context) {
        this.context = context.getApplicationContext();
        handler = new Handler(Looper.getMainLooper());

        contentView = new Space(context);
        duration = DEFAULT_DURATION;
        interpolator = DEFAULT_INTERPOLATOR;
        animation = DEFAULT_ANIMATION;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void show(@NonNull View target) {
        if (isShowing) return;
        if (target == null) {
            throw new NullPointerException("Goodjob show target==null");
        }
        this.target = target;
        startTime = System.currentTimeMillis();
        isShowing = true;

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        contentView.measure(w, h);

        popupWindow = new PopupWindow(contentView, contentView.getMeasuredWidth(), contentView.getMeasuredHeight());
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(false);
        popupWindow.setTouchable(false);
        popupWindow.setOutsideTouchable(false);

        int[] location = new int[2];
        target.getLocationOnScreen(location);
        int x = location[0] - (contentView.getWidth() - target.getMeasuredWidth()) / 2;
        int y = location[1];

        popupWindow.showAtLocation((View) target.getParent(), Gravity.NO_GRAVITY, x, y);
        handler.post(this);
    }

    @Override
    public void run() {
        if (!isShowing) return;

        float percent = 1;
        if (duration != 0) {
            percent = interpolator.getInterpolation((System.currentTimeMillis() - startTime) * 1.0f / duration);
        }
        if (percent > 1) {
            cancel();
            return;
        }

        animation.calculate(percent);

        int[] location = new int[2];
        target.getLocationOnScreen(location);
        int x = (int) (location[0] - (contentView.getWidth() - target.getMeasuredWidth()) / 2.0f + animation.getX());
        int y = (int) (location[1] + animation.getY());

        contentView.setAlpha(animation.getAlpha());
        popupWindow.update(x, y, popupWindow.getWidth(), popupWindow.getHeight());

        handler.post(this);
    }

    @Override
    public void cancel() {
        isShowing = false;
        target = null;
        popupWindow.dismiss();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public IGoodJob setEffectIsView(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("GoodJob set effect view null!");
        }

        if (view.getParent() != null) {
            throw new IllegalStateException("The specified child already has a parent. " +
                    "You must call removeView() on the child's parent first.");
        }

        contentView = view;
        return this;
    }

    @Override
    public IGoodJob setEffectIsImage(@DrawableRes int imageId) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageId);
        return setEffectIsView(imageView);
    }

    @Override
    public IGoodJob setEffectIsImage(@NonNull Drawable drawable) {
        ImageView imageView = new ImageView(context);
        imageView.setImageDrawable(drawable);
        return setEffectIsView(imageView);
    }

    @Override
    public IGoodJob setEffectIsText(@NonNull String text, @ColorInt int color, float sp) {
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextColor(color);
        textView.setTextSize(sp);
        return setEffectIsView(textView);
    }

    @Override
    public IGoodJob setDuration(int duration) {
        this.duration = duration;
        if (duration < 0) this.duration = 0;
        return this;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public IGoodJob setInterpolator(@NonNull Interpolator interpolator) {
        if (interpolator == null) {
            throw new NullPointerException("GoodJob set interpolator null!");
        }

        this.interpolator = interpolator;
        return this;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public IGoodJob setAnimation(@NonNull IAnimation animation) {
        if (animation == null) {
            throw new NullPointerException("GoodJob set animation null");
        }

        this.animation = animation;
        return this;
    }

    @Override
    public IGoodJob setPathStraight(float endOffsetX, float endOffsetY) {
        this.animation = new StraightLineAnimation(endOffsetX, endOffsetY);
        return this;
    }

}
