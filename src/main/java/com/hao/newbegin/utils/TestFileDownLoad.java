package com.hao.newbegin.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 1，通过base64加密下载下来的文件有时候会出现与报表直接导出来不符的情况，
 *,2，该交易方法用来验证下载的文件是否正确，
 * 3，使用方法：在浏览器输入http://localhost:7021/hss/testFileDownLoad回车即可
 *    （在不同的服务下注意修改IP，端口号，上下文路径等）
 */
@RestController
public class TestFileDownLoad {

    //测试文件下载
    @GetMapping("/testFileDownLoad")
    public ResponseEntity<byte[]> testFileDownLoad() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		String excelName = "test.txt";
        String excelName = "test.PDF";
        try {
            FileInputStream fileInputStream=new FileInputStream("E:\\opt\\test.txt");
            byte[] b = new byte[1024];
            int n;
            while ((n = fileInputStream.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fileInputStream.close();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pdfString = new String(bos.toByteArray());
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = base64Decoder.decodeBuffer(pdfString);
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {//调整异常数据
                bytes[i] += 256;
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment;filename=", excelName);
        return new ResponseEntity<byte[]>( bytes, headers, HttpStatus.OK);
    }
}
