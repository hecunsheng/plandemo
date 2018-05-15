package run.star.plan.hecs;

/**
 * Created by hecs on 2018/5/14.
 * desc:
 */
public class plat extends BaseContext{

    private String yinwq = "sab";

    public String getYinwq() {
        return yinwq;
    }

    public void setYinwq(String yinwq) {
        this.yinwq = yinwq;
    }

    public static void main(String[] args) {
        plat plat = new plat();
        System.out.println(plat.toString());
    }
}
