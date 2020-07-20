package run.star.plan.log;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hecs
 * @date 2020-07-13 11:19
 */
public abstract class BaseLog {
    public abstract Long getId();

    abstract OperateDataTypeEnum getOperateDataType();

    //region 生成数据修改日志
    public final static String ADD = "新增";
    public final static String UPDATE = "更新";
    public final static String DELETE = "删除";
    public final static String MERGE = "合并";
    public final static String DISABLE = "禁用";


    public <T extends BaseLog> UnityLog createLog(String unityOperate, T oldObj) throws IllegalAccessException {
        //保存操作日志
        UnityLog unityLog = comparatorObject(unityOperate, oldObj);
        unityLog.setId(getId());
        return unityLog;
    }

    /**
     * 比较新老对象的差别
     *
     * @param unityOperate
     * @param oldObj
     * @return
     * @throws IllegalAccessException
     */
    private UnityLog comparatorObject(String unityOperate, Object oldObj) throws IllegalAccessException {
        StringBuilder matter = new StringBuilder();
        StringBuilder content = new StringBuilder();

        if (oldObj != null && UPDATE.equals(unityOperate)) {
            Map<String, Object> oldMap = changeValueToMap(oldObj);
            Map<String, Object> newMap = changeValueToMap(this);
            if (MapUtils.isNotEmpty(oldMap)) {
                for (Map.Entry<String, Object> entry : oldMap.entrySet()) {
                    String key = entry.getKey();
                    Object oldValue = entry.getValue();
                    Object newValue = newMap.get(key);
                    if (oldValue == null || !oldValue.equals(newValue)) {
                        matter.append("[").append(key).append("]");
                        content.append(key + ":").append("[").append(oldValue).append("->").append(newValue).append("]").append(" ");
                    }
                }
            }
        } else {
            matter.append("-");
//            content.append("-");
            Map<String, Object> newMap = changeValueToMap(this);
            if (MapUtils.isNotEmpty(newMap)) {
                for (Map.Entry<String, Object> entry : newMap.entrySet()) {
                    String key = entry.getKey();
                    Object newValue = newMap.get(key);
                    content.append(key + ":").append("[").append(newValue).append("]").append(" ");
                }
            }
        }
        return UnityLog.builder()
                .unityOperate(unityOperate)
                .unityTag(getOperateDataType().getOperateName())
                .unityMatter(String.valueOf(matter))
                .unityContent(String.valueOf(content))
                .build();
    }

    /**
     * 将类对象转换成Map
     *
     * @param entity 原对象
     * @return Map
     * @throws IllegalAccessException 类型转换时报错
     */
    private static Map<String, Object> changeValueToMap(Object entity) throws IllegalAccessException {
        Map<String, Object> resultMap = new HashMap<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (PropertyUtils.isReadable(entity, name) && PropertyUtils.isWriteable(entity, name)) {
                if (field.isAnnotationPresent(LogCompar.class)) {
                    LogCompar anno = field.getAnnotation(LogCompar.class);
                    //获取private对象字段值
                    field.setAccessible(true);
                    resultMap.put(anno.name(), field.get(entity));
                }
            }
        }
        return resultMap;
    }
    //endregion

}

