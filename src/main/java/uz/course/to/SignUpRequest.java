package uz.course.to;

import uz.course.hibernate.domain._User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

public class SignUpRequest implements Serializable {
    @NotBlank
    @Size(min = 4, max = 40)
    private String firstName;

    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;

    @NotBlank
    @Size(min = 4, max = 40)
    private String middleName;

    @NotBlank
    @Size(min = 3, max = 12)
    private String phone;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank
    @Size(min = 6, max = 20)
    private String rePassword;

    @NotBlank
    private boolean gender;

    @NotBlank
    private Date dob;

    @NotBlank
    private Long branch;

    @NotBlank
    private Long lesson;

    @NotBlank
    private Long period;

    @NotBlank
    private Long advertise;

    @NotBlank
    private Long level;

    public Long getAdvertise() {
        return advertise;
    }

    public void setAdvertise(Long advertise) {
        this.advertise = advertise;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Long getLesson() {
        return lesson;
    }

    public void setLesson(Long lesson) {
        this.lesson = lesson;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public _User toEntity() {
        return new _User(firstName, lastName, middleName, phone, LocalDate.of(dob.getYear() + 1900, dob.getMonth() + 1, dob.getDate()), gender);
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Long getBranch() {
        return branch;
    }

    public void setBranch(Long branch) {
        this.branch = branch;
    }
}