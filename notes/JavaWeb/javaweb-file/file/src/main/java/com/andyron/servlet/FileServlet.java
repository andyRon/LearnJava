package com.andyron.servlet;

//import javax.servlet.

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author Andy Ron
 */
public class FileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断上传的是普通表单还是带文件的表单
        if (!ServletFileUpload.isMultipartContent(req)) {
            return;
        }

        // 创建上传文件的保存路径，建议在WEB-INF下，安全，用户无法直接访问到上传的文件
        String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
        File uploadFile = new File(uploadPath);
        if(!uploadFile.exists()) {
            uploadFile.mkdir(); // 上传目录如果不存在，就创建
        }

        // 缓存，临时文件
        // 临时路径，假如文件超过了预期的大小，就把它放到一个临时目录中，过几天删除，或者提醒用户转为永久
        String tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
        File file = new File(uploadPath);
        if(!file.exists()) {
            file.mkdir(); // 上传目录如果不存在，就创建
        }

        // 处理上传的文件，一般都需要流来获取，可以使用原生态的文件上传流获取request.getInputStream()，比较麻烦
        // 建议是使用Apache的文件上传组来实现，commons-fileupload，它依赖于commons-io


        // 1.创建DiskFileItemFactory对象，处理文件上传路径或者大小
        DiskFileItemFactory factory = getDiskFileItemFactory(uploadFile);

        // 2.获取ServletFileUpload
        ServletFileUpload upload = getServletFileUpload(factory);

        // 3.处理上传文件
        try {
            String msg = uploadParseRequest(upload, req, uploadPath);
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("info.jsp").forward(req, resp);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }








    }

    // 1.创建DiskFileItemFactory对象，处理文件上传路径或者大小
    static DiskFileItemFactory getDiskFileItemFactory(File file) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置一个缓冲区，当上传的文件大于这个缓冲区的时候，将它放到临时目录中
        factory.setSizeThreshold(1024 * 1024); // 缓冲区大小为1M
        factory.setRepository(file);
        return factory;
    }

    // 2.获取ServletFileUpload
    static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 监听上传文件进度
        upload.setProgressListener(new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("总大小：" + pContentLength + "已上传：" + pBytesRead);
            }
        });
        // 处理乱码
        upload.setHeaderEncoding("UTF-8");
        // 设置单个文件的最大值
        upload.setFileSizeMax(1024 * 1024 * 10);
        // 设置总共能上传的文件大小
        upload.setSizeMax(1024 * 1024 * 10);

        return upload;
    }

    // 3.处理上传文件
    static String uploadParseRequest(ServletFileUpload upload, HttpServletRequest req, String uploadPath) throws FileUploadException, IOException {
        String msg = "";

        // 把前端请求封装成一个FileItem对象，从ServletFileUpload对象中获取
        List<FileItem> fileItems = upload.parseRequest(req);
        for (FileItem fileItem : fileItems) { // fileItem表示每一个表单对象
            // 判断是否是带有文件的表单
            if (fileItem.isFormField()) {
                String name = fileItem.getFieldName();
                String value = fileItem.getString("utf-8");
                System.out.println(name + ":" + value);
            } else { // 文件
                //===========处理文件=========//
                String uploadFileName = fileItem.getName();
                // 可能存在文件名不合法的情况
                if (uploadFileName.trim().equals("") || uploadFileName == null) {
                    continue;
                }
                // 获得上传的文件名
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                // 获得文件的后缀名
                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);

                System.out.println("文件信息[文件名：" + fileName + "----文件类型" + fileExtName + "]");
                /*
                   可以使用UUID（唯一识别的通用码），保证文件名唯一；
                   UUD.randomUUID（），随机生一个唯一识别的通用码；
                   网络传输中的东西，都需要序列化；
                   P0J0，实体类，如果想要在多个电脑上运行；传输===》需要把对象都序列化了；
                   `implements Serializable` ，这种空接口叫做标记接口；
                   这种接口会通知JVM做一些事情，JVM一->本地方法栈native--> C++
                   JNI = Java Native Interface
                 */
                String uuidPath = UUID.randomUUID().toString();

                //===========存放地址=========//
                //
                String realPath = uploadPath + "/" + uuidPath;
                // 给每个文件创建一个对应的文件夹
                File realPathFile = new File(realPath);
                if (!realPathFile.exists()) {
                    realPathFile.mkdir();
                }
                //===========文件传输=========//
                // 获得文件上传流
                InputStream inputStream = fileItem.getInputStream();
                // 创建一个文件输出流
                FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);

                // 创建一个缓冲区
                byte[] buffer = new byte[1024 * 1024];
                // 判断是否读取完毕
                int len = 0;
                while ((len = inputStream.read(buffer))>0) {
                    fos.write(buffer,0, len);
                }
                // 关闭流
                fos.close();
                inputStream.close();
                msg = "文件上传成功";
                fileItem.delete(); // 上传成功，清楚临时文件
            }
        }
        return msg;
    }
}
