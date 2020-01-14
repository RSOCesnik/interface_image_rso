package si.fri.project.imageInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import si.fri.project.imageInterface.Dto.CommentDto;
import si.fri.project.imageInterface.Dto.ImagePlusComment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import si.fri.project.imageInterface.ClientRSO;

@WebServlet("/deletecomment")
public class DeleteCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("commentId") != null ) {

            String id = request.getParameter("commentId");
            HttpResponse hr = ClientRSO.delete_comment(id);

            response.sendRedirect(Helper.getBaseUrl(request)+"/app/welcome.jsp");


        }else{
            response.getWriter().write("Wrong parameters");
        }
    }

}
