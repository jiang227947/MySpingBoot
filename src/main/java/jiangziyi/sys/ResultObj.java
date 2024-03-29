package jiangziyi.sys;

import java.util.List;
/**
 * 返回结果
 */
public class ResultObj {

    private Integer code;
    private String msg;
    private Object data;

    public ResultObj(Integer code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public void setData(List<?> data) {
        this.data = data;
    }
}