package run.star.plan.log;

/**
 * @author hecs
 * @date 2020-07-13 11:16
 */
public enum OperateDataTypeEnum {
    OPERATE_DATA_TYPE_1(1, "商品"),
    OPERATE_DATA_TYPE_2(2, "品牌"),
    OPERATE_DATA_TYPE_3(3, "SKU"),
    OPERATE_DATA_TYPE_4(4, "属性"),
    OPERATE_DATA_TYPE_5(5, "属性值");

    OperateDataTypeEnum(Integer operateType, String operateName) {
        this.operateType = operateType;
        this.operateName = operateName;
    }

    /**
     * 操作类型名称
     */
    private Integer operateType;
    /**
     * 操作类型名称
     */
    private String operateName;

    public String getOperateName() {
        return operateName;
    }

    public static OperateDataTypeEnum getEnumByCode(Integer operateType) {
        if (operateType == null) {
            return null;
        }
        for (OperateDataTypeEnum each : values()) {
            assert each.getOperateType() != null;
            if (each.getOperateType().equals(operateType)) {
                return each;
            }
        }
        return null;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public boolean equalEnum(Integer operateType) {
        return operateType != null && getOperateType().equals(operateType);
    }
}
