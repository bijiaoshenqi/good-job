package net.bingyan.goodjob.path;

import android.support.annotation.NonNull;

/**
 * 点赞动画移动路径
 *
 * @author Chris Wong
 * @time 2016-05-25
 */
public abstract class Animation implements IAnimation {
    private float alpha;
    private float x;
    private float y;

    @Override public float getAlpha() { return alpha; }
    @Override public float getX() { return x; }
    @Override public float getY() { return y; }

    protected void setAlpha(float alpha) { this.alpha = alpha; }
    protected void setX(float x) { this.x = x; }
    protected void setY(float y) { this.y = y; }

}
