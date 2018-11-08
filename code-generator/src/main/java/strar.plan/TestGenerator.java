package strar.plan;

import com.github.zhuyizhuo.generator.mybatis.generator.Generator;
import com.github.zhuyizhuo.generator.mybatis.generator.GeneratorBuilder;
import org.apache.ibatis.io.Resources;
/**
 * 生成器使用
 */
public class TestGenerator {

    public static void main(String[] args) throws Exception {
        /** 此处使用 配置文件的绝对路径或者在项目中的相对路径
         * 本例配置文件路径在maven项目的src/main/resources文件夹下
         */
        Generator generator = new GeneratorBuilder()
                .build(Resources.getResourceAsStream("db.properties"));
        generator.generate();
    }
}