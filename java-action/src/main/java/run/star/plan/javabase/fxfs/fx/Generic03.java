package run.star.plan.javabase.fxfs.fx;

/**
 * @Author hecs
 * @Date 2021/11/11 20:53
 */
public class Generic03 {
    public static void main(String[] args) {
        DefEntry<String> defEntry1 = new DefEntry<>("hello") ;
        DefEntry<Long> defEntry2 = new DefEntry<>(999L) ;
        // Always True
        System.out.println(defEntry1.getClass()==defEntry2.getClass());
    }

    static class DefEntry<T> {
        private T param ;
        public DefEntry (T param){
            this.param = param ;
        }
    }
}
