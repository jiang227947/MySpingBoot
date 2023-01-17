package jiangziyi.service;

import jiangziyi.pojo.FilePojo;
import jiangziyi.pojo.query.PageParams;
import jiangziyi.sys.ResultObj;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    // 上传文件
    ResultObj uploadFile(MultipartFile file);

    // 分页查询所有文件
    List<FilePojo> queryFileList(@Param("pageParams") PageParams pageParams);

    // 根据id查询文件
    FilePojo queryFileById(Integer id);

    // 删除文件
    int deleteFile(Integer id);
}