package si.fri.project.imageInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import si.fri.project.imageInterface.Dto.CommentDto;
import si.fri.project.imageInterface.Dto.ImagePlusComment;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import si.fri.project.imageInterface.ClientRSO;

import static si.fri.project.imageInterface.ClientRSO.upload_picture;

@WebServlet("/addimage")
@MultipartConfig
public class UploadingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("data");
        response.getWriter().write(filePart.toString());

        if (request.getParameter("description") != null && request.getParameter("title") != null) {

            String desc = request.getParameter("description");
            String title = request.getParameter("title");

            InputStream fileContent = filePart.getInputStream();
            byte[] imageBytes = IOUtils.toByteArray(fileContent);
            String imageStr = Base64.encodeBase64String(imageBytes);
            String upload = null;
            try {
                upload = upload_picture(title,desc,imageStr);
                response.sendRedirect(Helper.getBaseUrl(request)+"/welcome.jsp");


            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            response.getWriter().write("Wrong parameters");
        }
    }

}
