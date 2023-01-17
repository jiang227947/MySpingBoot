package jiangziyi.dao;

import jiangziyi.pojo.FilePojo;
import jiangziyi.pojo.query.PageParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper // 告诉springboot这是一个mybatis类
@Repository // 将userDao交由spring容器管理
public interface FileDao {

    // 上传文件
    void uploadFile(@Param("filePojo") FilePojo filePojo);

    // 分页查询所有文件
    List<FilePojo> queryFileList(@Param("pageParams") PageParams pageParams);

    // 根据id查询文件
    FilePojo queryFileById(Integer id);

    // 删除文件
    int deleteFile(Integer id);
}
