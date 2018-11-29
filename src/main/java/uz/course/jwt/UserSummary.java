package uz.course.jwt;

import java.io.Serializable;

public class UserSummary implements Serializable {
    private Long id;
    private String phone;
    private String name;
    private String photo;
    private Boolean success;
    private String uri;

    public UserSummary(Long id, String phone, String name, String photo, String uri) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.photo = photo;
        this.uri = uri;
        this.success = true;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
