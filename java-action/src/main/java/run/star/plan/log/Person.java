package run.star.plan.log;

import lombok.Data;

import java.util.List;

/**
 * @author hecs
 * @date 2020-07-13 11:31
 */
@Data
public class Person extends BaseLog {
    private Long id;
    @LogCompar(name = "姓名")
    private String name;
    @LogCompar(name = "年龄")
    private Integer age;
    private Boolean sex;
    @LogCompar(name = "sku属性值")
    private List<SkuRo> skuList;
    @LogCompar(name = "skuId列表")
    private List<Integer> skuIds;


    public Person(Long id, String name, Integer age, Boolean sex, List<SkuRo> skuList) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.skuList = skuList;
    }

    public Person(Long id, String name, Integer age, Boolean sex, List<SkuRo> skuList, List<Integer> skuIds) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.skuList = skuList;
        this.skuIds = skuIds;
    }

    @Override
    OperateDataTypeEnum getOperateDataType() {
        return OperateDataTypeEnum.OPERATE_DATA_TYPE_1;
    }
}
