package net.bingyan.goodjob.path;

import android.support.annotation.NonNull;

/**
 * 直线方式向上消失
 * README.md中有描述
 *
 * @author Chris Wong
 */
public class StraightLineAnimation extends Animation {
    private float startX;
    private float endX;
    private float startY;
    private float endY;

    private float startAlpha;
    private float endAlpha;

    /**
     * 完整版构造函数
     *
     * @param startX     动画起始位置x,非原始位置0
     * @param endX       动画终止位置x
     * @param startY     动画起始位置y,非原始位置0
     * @param endY       动画终止位置y
     * @param startAlpha 动画起始alpha
     * @param endAlpha   动画终止alpha
     */
    public StraightLineAnimation(float startX, float endX, float startY, float endY, float startAlpha, float endAlpha) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.startAlpha = startAlpha;
        this.endAlpha = endAlpha;
    }

    /**
     * 精简版构造函数
     * 从原始位置(0,0)开始运动
     *
     * @param endX 动画终止偏移位置x
     * @param endY 动画终止偏移位置y
     */
    public StraightLineAnimation(float endX, float endY) {
        this.startX = 0;
        this.endX = endX;
        this.startY = 0;
        this.endY = endY;
        this.startAlpha = 1;
        this.endAlpha = 0;
    }

    @Override
    public void calculate(float percent) {
        setAlpha((endAlpha - startAlpha) * percent + startAlpha);
        setX((endX - startX) * percent + startX);
        setY((endY - startY) * percent + startY);
    }

    @NonNull
    public StraightLineAnimation setStartX(float startX) {
        this.startX = startX;
        return this;
    }

    @NonNull
    public StraightLineAnimation setEndX(float endX) {
        this.endX = endX;
        return this;
    }

    @NonNull
    public StraightLineAnimation setStartY(float startY) {
        this.startY = startY;
        return this;
    }

    @NonNull
    public StraightLineAnimation setEndY(float endY) {
        this.endY = endY;
        return this;
    }

    @NonNull
    public StraightLineAnimation setStartAlpha(float startAlpha) {
        this.startAlpha = startAlpha;
        return this;
    }

    @NonNull
    public StraightLineAnimation setEndAlpha(float endAlpha) {
        this.endAlpha = endAlpha;
        return this;
    }
}
