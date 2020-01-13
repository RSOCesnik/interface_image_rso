package si.fri.project.imageInterface;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import si.fri.project.imageInterface.Dto.CommentDto;
import si.fri.project.imageInterface.Dto.ImagePlusComment;

import javax.net.ssl.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getPathInfo() != null) {
            String data = ClientRSO.get_image_and_comments(request.getPathInfo());

            if (data == null || data.isEmpty()) {
                response.getWriter().println("No image found.");

            } else {
                ObjectMapper mapper = new ObjectMapper();
                ImagePlusComment image = mapper.readValue(data, ImagePlusComment.class);
                response.getWriter().write("<link href=\"../css/style.css\" rel=\"stylesheet\" type=\"text/css\">");

                response.getWriter().write("<div class=\"image\">");
                response.getWriter().write("<h3>" + image.getImage().getTitle() + "</h3><br/>");
                response.getWriter().write("<img src=\"" + image.getImage().getData() + "\"/><br/>");
                response.getWriter().write("<label>" + image.getImage().getDescription() + "</label><br/>");
                response.getWriter().write("<div class=\"comments-image-title\">Comments</div>");
                response.getWriter().write("<div class=\"comments-image\">");

                for (CommentDto comment : image.getComments()) {

                    response.getWriter().write("<div class=\"comment\">" + comment.getCommentData() + "</div><br/>");

                }
                response.getWriter().write("</div>" +
                        "<div class=\"comment-form\"><form method=\"post\" action=\"/addcomment\">" +
                        "<input name=\"comment\" maxlength=\"50\" required/>" +
                        "<input name=\"imageid\" value=\""+image.getImage().getId()+"\" type=\"hidden\"/>" +
                        "<input type=\"submit\" value=\"Submit\"/>" +
                        "</form></div></div>");

            }
        }else{
            response.getWriter().write("No image");
        }

//        request.setAttribute("id", request.getPathInfo());
//        request.getRequestDispatcher("/image.jsp").forward(request, response);
    }


//    @Path("/opened")
//    public void getImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        if (request.getPathInfo() != null) {
//            String data = ClientRSO.get_image_and_comments(request.getPathInfo());
//
//            if (data == null || data.isEmpty()) {
//                response.getWriter().println("No image found.");
//
//            } else {
//                ObjectMapper mapper = new ObjectMapper();
//                ImagePlusComment image = mapper.readValue(data, ImagePlusComment.class);
//                response.getWriter().write("<h3>" + image.getImage().getTitle() + "</h3><br/>");
//                response.getWriter().write("<img src=\"" + image.getImage().getData() + "\"/><br/>");
//                response.getWriter().write("<label>" + image.getImage().getDescription() + "</label><br/>");
//                response.getWriter().write("<div class=\"comments-image\">");
//
//                for (CommentDto comment : image.getComments()) {
//
//                    response.getWriter().write("<div class=\"comment\">" + comment.getCommentData() + "</div><br/>");
//
//                }
//                response.getWriter().write("</div>");
//
//            }
//        }else{
//            response.getWriter().write("No image");
//        }
//
////        }
////        response.getWriter().write("No image selected");
//    }


}
