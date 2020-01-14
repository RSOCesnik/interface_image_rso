package si.fri.project.imageInterface;

import com.fasterxml.jackson.databind.ObjectMapper;
import si.fri.project.imageInterface.Dto.ImageDto;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@WebServlet("/images")
public class CatalogServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String data = ClientRSO.get_images();

        response.getWriter().write("<div class=\"image-form\"><form method=\"post\" action=\"/addimage\" enctype=\"multipart/form-data\">" +
                "<input placeholder=\"Title\" name=\"title\" maxlength=\"50\" required/>" +
                "<input placeholder=\"Description\" name=\"description\" maxlength=\"50\" required/>" +
                "<input  name=\"data\" type=\"file\" maxlength=\"50\" required/>" +
                "<input type=\"submit\" value=\"Add image\"/>" +
                "</form></div>");

        if (data == null || data.isEmpty()) {
            response.getWriter().println("No images found.");

        } else {
            ObjectMapper mapper = new ObjectMapper();
            List<ImageDto> ppl2 = Arrays.asList(mapper.readValue(data, ImageDto[].class));

            for (ImageDto image : ppl2) {

                response.getWriter().write("<h3>" + image.getTitle() + "</h3><br/>");
                response.getWriter().write("<a href=\"image/"+image.getId()+"\"><img src=\"" + image.getData() + "\" data-id=\"" + image.getId() + "\"  /></a><br/>");
                response.getWriter().write("<label>" + image.getDescription() + "</label><br/>");
            }
        }
    }
}



