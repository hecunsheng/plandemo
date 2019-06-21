package run.star.plan.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 14:01
 * @Description: 浅入了解策略模式、责任链模式、管道模式 dubbo里面运用了很多这种模式
 */
public class FilterApplesMain {

    static List<Apple>  repos   = Arrays.asList(new Apple(100, "green"), new Apple(90, "white"),
                                    new Apple(110, "green"), new Apple(120, "yellow"), new Apple(
                                        80, "green"));
    static List<Banana> bananas = Arrays.asList(new Banana("red"), new Banana("white"), new Banana(
                                    "red"));

    public static void main(String[] args) {
        //05版本
        System.out.println(filterFruit(bananas, new IAppleFilter<Banana>() {
            public boolean test(Banana banana) {
                return "red".equals(banana.getColor());
            }
        }));
        /*   System.out.println(filterApples(new AppleFilterByGreenColor()));//04版本
           System.out.println(filterApples(new AppleFilterByWeight()));
           System.out.println(filterApples(new IAppleFilter() {//匿名内部类方式调用
               public boolean test(Apple apple) {
                   return "white".equals(apple.getColor());
               }
           }));*/
        //        System.out.println(filterApples("green",100,false));//03版本
        //        System.out.println(filterApplesByWithColor("yellow"));//02版本
        //        System.out.println(FilterApplesMain.filterGreenApples()); //01版本
    }

    //05版本 抽象泛型
    private static <T> List<T> filterFruit(List<T> repos, IAppleFilter iAppleFilter) {
        List<T> result = new ArrayList<T>();
        for (T t : repos) {
            if (iAppleFilter.test(t)) {
                result.add(t);
            }
        }
        return result;
    }
    //04版本 抽象(标准接口)面向接口 定义一个标准(标准模型作为入参) 可扩展性 单一性
    /*    private static List<Apple> filterApples(IAppleFilter iAppleFilter){
            List<Apple> result = new ArrayList<Apple>();
            for(Apple apple:repos){
                if(iAppleFilter.test(apple)){
                    result.add(apple);
                }
            }
            return result;
        }*/
    //03版本 加入重量筛选后，扩展性变差
    /*  private static List<Apple> filterApples(String color,Integer weight,boolean flag){
          List<Apple> result = new ArrayList<Apple>();
          for(Apple apple:repos){
              if((flag&&color.equals(apple.getColor()))||(!flag&&apple.getWeight()>weight)){
                  result.add(apple);
              }
          }
          return result;
      }*/
    //02版本 抽象了颜色 考虑了部分扩展性
    /* private static List<Apple> filterApplesByWithColor(String color){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple:repos){
            if(color.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }*/
    //01版本 只满足了需求 无可扩展性
    /* private static List<Apple> filterGreenApples(){
         List<Apple> result = new ArrayList<Apple>();
         for(Apple apple:repos){
             if("green".equals(apple.getColor())){
                 result.add(apple);
             }
         }
         return result;
     }*/
}
