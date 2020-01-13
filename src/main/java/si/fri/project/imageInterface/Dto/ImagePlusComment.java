package si.fri.project.imageInterface.Dto;

import java.util.List;

public class ImagePlusComment {

    private List<CommentDto> comments;
    private ImageDto image;

    public List<CommentDto>  getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }
}
