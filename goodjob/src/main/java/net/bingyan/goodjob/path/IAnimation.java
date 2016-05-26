package net.bingyan.goodjob.path;

/**
 * 计算点赞动画的接口
 */
public interface IAnimation {

    /**
     * 根据动画完成百分比重新计算alpha,x,y
     *
     * @param percent [0,1]，动画完成程度百分比
     */
    void calculate(float percent);

    /**
     * 获得最近一次计算得到的alpha
     *
     * @return alpha [0,1]
     * @see #calculate(float)
     */
    float getAlpha();

    /**
     * 获得最近一次计算得到的x
     *
     * @return x,相对原先(0,0)的横向偏移
     * @see #calculate(float)
     */
    float getX();

    /**
     * 获得最近一次计算得到的y
     *
     * @return y,相对原先(0,0)的纵向偏移
     * @see #calculate(float)
     */
    float getY();

}
