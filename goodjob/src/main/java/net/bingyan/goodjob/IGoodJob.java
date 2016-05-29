package net.bingyan.goodjob;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Interpolator;

import net.bingyan.goodjob.path.IAnimation;

/**
 * GoodJob对外表现的所有常量和接口
 * <p>
 * 虽然有多种点赞效果，但是只保留最后设置的哪一个，也只播放那一个的动画 如果想要播放好几种动画，那就要创建几个IGoodJob
 *
 * @author Chris Wong
 * @time 2016-05-26
 */
public interface IGoodJob {

    /**
     * 展示点赞动画
     *
     * @param target 需要展示点赞动画的View，多数为一个大拇指
     */
    void show(@NonNull View target);

    /**
     * 取消点赞动画
     */
    void cancel();

    /**
     * 设置动画本体为一张图片
     *
     * @param imageId 图片res id
     * @return itself for chained calls
     */
    IGoodJob setEffectIsImage(@DrawableRes int imageId);

    /**
     * 设置动画本体为一张图片
     *
     * @param drawable 图片Drawable
     * @return itself for chained calls
     */
    IGoodJob setEffectIsImage(@NonNull Drawable drawable);

    /**
     * 设置动画本体为一串字
     *
     * @param text  需要显示的字，例如：+1
     * @param color 字体的颜色
     * @param sp    字体大小，sp size
     * @return itself for chained calls
     */
    IGoodJob setEffectIsText(@NonNull String text, @ColorInt int color, float sp);

    /**
     * 设置动画本体为一个View，可以是自定义，也可以是别的ImageView之类的，无所谓
     *
     * @param view 需要显示的动画本体
     * @return itself for chained calls
     */
    IGoodJob setEffectIsView(@NonNull View view);

    /**
     * 设置动画的时间
     *
     * @param duration 完整动画的时间,ms
     * @return itself for chained calls
     */
    IGoodJob setDuration(int duration);

    /**
     * 设置动画的Interpolator，默认{@link android.view.animation.AccelerateInterpolator}
     *
     * @param interpolator 播放动画时用interpolator
     * @return itself for chained calls
     */
    IGoodJob setInterpolator(@NonNull Interpolator interpolator);

    /**
     * 设置动画，包括alpha和轨迹
     *
     * @param animation 动画的运动轨迹
     * @return itself for chained calls
     */
    IGoodJob setAnimation(@NonNull IAnimation animation);

    /**
     * 设置动画的运动轨迹为一条直线
     *
     * @param endOffsetX 最后停下来的相对x坐标
     * @param endOffsetY 最后停下来的相对y坐标
     * @return itself for chained calls
     */
    IGoodJob setPathStraight(float endOffsetX, float endOffsetY);



}
