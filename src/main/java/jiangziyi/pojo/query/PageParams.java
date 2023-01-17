package jiangziyi.pojo.query;

// 分页参数
public class PageParams {
    // 当前页码
    private Integer pageNum;
    // 一页多少条数据
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
