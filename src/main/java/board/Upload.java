package board;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/board/testupload")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,      // 10 MB
	    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class Upload extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tmpPath = "c:/Temp/upload";
        File file = new File(tmpPath);
        if(!file.exists()) 
            file.mkdirs();
        
        /* Receive file uploaded to the Servlet from the HTML5 form */
        request.setCharacterEncoding("utf-8");
        String param = request.getParameter("param");
        System.out.println("param: " + param);
        
        String fileName = null;
//        Part filePart = request.getPart("file");
//        if (filePart == null) {
//        	System.out.println("No files uploaded.");
//        } else {
//        	fileName = filePart.getSubmittedFileName();
//        	System.out.println("fileName: " + fileName);
//        	
//        	for (Part part : request.getParts()) {
//                part.write(tmpPath + File.separator + fileName);
//            }
//        }
        	
        Part filePart = null;	
        List<String> fileList = new ArrayList<>();
        for (int i=1; i<=2; i++) {
            filePart = request.getPart("file" + i);		// name이 file1, file2
            if (filePart == null)
            	continue;
            fileName = filePart.getSubmittedFileName();
            System.out.println("file" + i + ": " + fileName);
            if (fileName == null || fileName.equals(""))
                continue;
            fileList.add(fileName);
            
//            ByteArrayInputStream bis = (ByteArrayInputStream) filePart.getInputStream();
//            byte[] buffer = bis.readAllBytes();
//            OutputStream fos = new FileOutputStream(tmpPath + File.separator + fileName);
//            fos.write(buffer);
			for (Part part : request.getParts()) { 
				part.write(tmpPath + File.separator + fileName); 
			}
            response.getWriter().print("The file is uploaded sucessfully.");	
        }
	}

}