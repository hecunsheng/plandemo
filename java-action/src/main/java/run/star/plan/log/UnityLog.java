package run.star.plan.log;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author hecs
 * @date 2020-07-13 11:17
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data
@Builder
public class UnityLog {
    /**
     * id
     */
    private Long id;

    /**
     * 数据库表名
     */
    private String unityTag;

    /**
     * 数据库表记录id对应的操作日志
     */
    private Long unityTagId;

    /**
     * 操作人
     */
    private String unityOperator;

    /**
     * 操作时间
     */
    private Date unityOperateTime;

    /**
     * 操作类型
     */
    private String unityOperate;

    /**
     * 操作事项
     */
    private String unityMatter;

    /**
     * 操作内容
     */
    private String unityContent;
}