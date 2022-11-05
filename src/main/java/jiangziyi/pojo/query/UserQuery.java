package jiangziyi.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    private Integer pageNum = 1; // 当前起始页
    private Integer pageSize = 2; // 每一页展示的数据数量
    private String userName; // 根据用户名查询
}
