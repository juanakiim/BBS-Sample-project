package board;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet("/board/imageupload")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
	    maxFileSize = 1024 * 1024 * 10,      // 10 MB
	    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class ImageUpload extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tmpPath = "c:/Temp/images";
        String fileName = null;
        Part filePart = request.getPart("file");
        if (filePart == null) {
        	System.out.println("No files uploaded.");
        } else {
        	fileName = filePart.getSubmittedFileName();
        	String now = LocalDateTime.now().toString().replace(" ", "");
        	System.out.println("fileName: " + fileName);
  	
        	for (Part part : request.getParts()) {
        		part.write(tmpPath + File.separator + fileName);
        	}
        }
	}

}