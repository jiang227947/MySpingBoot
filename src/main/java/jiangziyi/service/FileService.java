package jiangziyi.service;

import jiangziyi.pojo.FilePojo;
import jiangziyi.pojo.query.PageParams;
import jiangziyi.sys.ResultList;
import jiangziyi.sys.ResultObj;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


public interface FileService {
    // 上传文件
    ResultObj uploadFile(MultipartFile file);

    // 分页查询所有文件
    ResultList queryFileList(@Param("pageParams") PageParams pageParams);

    // 查询文件总数
    Integer countQueryFileList(@Param("pageParams") PageParams pageParams);

    // 根据文件名查询文件
    FilePojo queryFileByFileName(String fileName);

    // 根据id查询文件
    FilePojo queryFileById(Integer id);

    // 删除文件
    int deleteFile(Integer id);

    // 修改上传时间
    int updateFileUpdateTime(FilePojo filePojo);
}
