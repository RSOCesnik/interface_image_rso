package si.fri.project.imageInterface.Dto;

public class CommentDto {

    private Integer id;
    private String commentData;
    private String imageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentData() {
        return commentData;
    }

    public void setCommentData(String comment_data) {
        this.commentData = comment_data;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String image_id) {
        this.imageId = image_id;
    }
}
