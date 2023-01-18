package jiangziyi.controller;

import jiangziyi.comstant.DocumentConstant;
import jiangziyi.pojo.query.PageParams;
import jiangziyi.service.FileService;
import jiangziyi.sys.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Slf4j
@RestController
public class FileHandlerController {
    @Autowired
    private FileService fileService;

    // 上传文件
    @PostMapping("/uploadFile")
    @ResponseBody
    public ResultObj uploadFile(MultipartFile file) {
        return this.fileService.uploadFile(file);
    }

    // 分页查询所有文件
    @PostMapping("/queryFileList")
    @ResponseBody
    public ResultObj queryFileList(@RequestBody PageParams pageParams) {
        return new ResultObj(200, "查询成功", fileService.queryFileList(pageParams));
    }

    // 根据文件名查询文件
    @PostMapping("/queryFileByFileName/{id}")
    @ResponseBody
    public ResultObj queryFileByFileName(@PathVariable("id") Integer id) {
        int i = fileService.deleteFile(id);
        if (i > 0) {
            return new ResultObj(200, "删除成功", 1);
        } else {
            return new ResultObj(-1, "删除失败", null);
        }
    }

    // 删除文件
    @PostMapping("/deleteFile/{id}")
    @ResponseBody
    public ResultObj deleteFile(@PathVariable("id") Integer id) {
        int i = fileService.deleteFile(id);
        if (i > 0) {
            return new ResultObj(200, "删除成功", 1);
        } else {
            return new ResultObj(-1, "删除失败", null);
        }
    }

    // 下载文件
    @PostMapping("/download")
    @ResponseBody
    public void download(@RequestBody String filename, HttpServletResponse response) {
//        File file = new File("D:/static/files/" + filename);
        File file = new File(DocumentConstant.FILE_ADDRESS + filename);
        String fileName = file.getName();
        String fileAbsolutePath = file.getAbsolutePath();
        try (FileInputStream inputStream = new FileInputStream(fileAbsolutePath);
             InputStream is = new BufferedInputStream(inputStream);
             OutputStream os = new BufferedOutputStream(response.getOutputStream())) {

            response.setCharacterEncoding("UTF-8");
            //设置返回类型
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "UTF-8"));

            byte[] b = new byte[8 * 1024];
            int len = 0;
            while (-1 != (len = is.read(b))) {
                os.write(b, 0, len);
            }
        } catch (Exception e) {
            log.error("关闭流异常", e);
        }
    }

}
