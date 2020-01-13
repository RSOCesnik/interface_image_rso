package si.fri.project.imageInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import si.fri.project.imageInterface.Dto.CommentDto;
import si.fri.project.imageInterface.Dto.ImagePlusComment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/addcomment")
public class CommentingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("comment") != null && request.getParameter("imageid") != null) {

            response.getWriter().write(request.getParameter("comment"));
            response.getWriter().write(request.getParameter("imageid"));

//            String data = ClientRSO.get_image_and_comments(request.getParameter("id"));
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
        }else{
            response.getWriter().write("Wrong parameters");
        }
    }

}
