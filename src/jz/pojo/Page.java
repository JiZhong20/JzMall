package jz.pojo;

import java.util.List;

/**
 * @param
 * @description
 * @Created by IntelliJ IDEA.
 * @author:
 * @Date: 2020/3/27
 * @return
 */
//page是分页的模型对象，<T>视具体的模块的javabean类
public class Page <T>{
    public static final Integer PAGE_SIZE = 4;
    //当前页码
    private  Integer pageNum;
    //总页码
    private  Integer pageTotal;
    //当前页面的显示数量
    private Integer pageSize = PAGE_SIZE;
    //总记录数
    private Integer pageTotalCount;
    //所有图书数据
    private List<T> items;
    //分页调的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        //检查边界
        if (pageNum < 1){
            pageNum = 1;
        }
        if (pageNum > pageTotal){
            pageNum = pageTotal;
        }
        this.pageNum = pageNum;
    }
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
