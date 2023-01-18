package jiangziyi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilePojo {
    private Integer id;    // id
    private String fileName; // 文件名
    private String filePath; // 文件地址
    private Long fileSize; // 文件大小
    private Long updateTime; // 上传文件的时间戳
}
