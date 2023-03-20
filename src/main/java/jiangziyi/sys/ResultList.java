package jiangziyi.sys;

/**
 * 列表分页返回结果
 */
public class ResultList<T> {

    private Integer code;
    private String msg;
    private T data;

    /*** 当前是多少页*/
    private Integer pageNum;

    /*** 每页显示记录数*/
    private Integer pageSize;

    /*** 总页数*/
    private Integer totalPage;

    /*** 总记录数*/
    private Integer totalCount;

    /**
     * Result
     */
    public ResultList(Integer code, String msg, T data, Integer pageNum, Integer pageSize, Integer totalPage, Integer totalCount) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.pageNum = pageNum;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSize() {
        return pageSize;
    }

    public void setSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
