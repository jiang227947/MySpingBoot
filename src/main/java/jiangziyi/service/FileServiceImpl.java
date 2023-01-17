package jiangziyi.service;

import jiangziyi.comstant.DocumentConstant;
import jiangziyi.dao.FileDao;
import jiangziyi.pojo.FilePojo;
import jiangziyi.pojo.User;
import jiangziyi.pojo.query.PageParams;
import jiangziyi.sys.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Service // 交由spring容器管理
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public ResultObj uploadFile(MultipartFile file) {
        if (file.getSize() > 10 * 1024 * 1024) {
            return new ResultObj(-1, "附件体积不能超过10MB", null);
        }
        //文件名
        String originalName = file.getOriginalFilename();
        assert originalName != null;
        String imgRealPath = originalName.substring(originalName.lastIndexOf("."));
//        String path = "D:/static/files";
        String path = DocumentConstant.FILE_ADDRESS;
        try {
            // 创建 File 文件目录对象
            File filePath = new File(path);
            // 获取上层目录
            File parentFilePath = filePath.getParentFile();
            // 判断文件目录是否存在
            if (!parentFilePath.exists()) {
                // 目录不存在, 则创建目录
                parentFilePath.mkdirs();
            }
            // 创建上传之后的文件全路径名称
            File uploadPath = new File(filePath, originalName);
            // 上传文件
            file.transferTo(uploadPath);
            // 上传文件的时间戳
            long updateTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            FilePojo filePojo = new FilePojo(null, originalName, path + "/" + originalName, updateTime);
            // 路径和名称存数据库
            fileDao.uploadFile(filePojo);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultObj(-1, "文件上传失败", null);
        }
        return new ResultObj(200, "文件上传成功", null);
    }

    // 查询所有用户
    @Override
    public List<FilePojo> queryFileList(PageParams pageParams) {
        return fileDao.queryFileList(pageParams);
    }

    // 根据id查询文件
    @Override
    public FilePojo queryFileById(Integer id) {
        return fileDao.queryFileById(id);
    }

    @Override
    public int deleteFile(Integer id) {
        try {
            // 根据id查询附件地址
            FilePojo filePojo = fileDao.queryFileById(id);
            log.info("filePojo {}", filePojo);
            // 删除服务器中附件
            File myDelFile = new File(filePojo.getFilePath());
            log.info("删除服务器中附件 {}", myDelFile);
            // 删除文件
            myDelFile.delete();
            // 删除数据库
            return fileDao.deleteFile(id);
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
            return 0;
        }
    }
}
