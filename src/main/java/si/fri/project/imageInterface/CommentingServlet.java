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
import si.fri.project.imageInterface.ClientRSO;

@WebServlet("/addcomment")
public class CommentingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("comment") != null && request.getParameter("imageid") != null) {

            String data = request.getParameter("comment");
            String id = request.getParameter("imageid");
            String response_code = ClientRSO.add_comment(id,data);
            response.getWriter().write(response_code);
            response.sendRedirect(Helper.getBaseUrl(request)+"/image/"+id);


        }else{
            response.getWriter().write("Wrong parameters");
        }
    }

}
