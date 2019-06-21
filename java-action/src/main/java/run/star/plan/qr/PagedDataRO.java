package run.star.plan.qr;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * 分布查询结果
 *
 * @name 分布查询结果
 * @author xuhaifeng@qr.com
 * @since Revision:1.0.0, Date: 2015年11月13日 下午7:18:55
 */
public class PagedDataRO<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分页大小
     */
    public int pageSize;

    /**
     * 页码
     */
    public int pageNo;

    /**
     * 结果总数
     */
    public int totalSize;

    /** 返回的结果列表 */
    public List<T> resultList;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}