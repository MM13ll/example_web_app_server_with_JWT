package uz.course.hibernate.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.StringUtils;
import uz.course.hibernate.base.Db;
import uz.course.hibernate.base._AuditInfo;
import uz.course.hibernate.base._Entity;
import uz.course.to.SelectItem;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Db
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(name = "Constraint_user_phone", columnNames = {"phone"})
        },
        indexes = {@Index(name = "Index_user_phone_and_actived", columnList = "phone, actived, state"),
                @Index(name = "Index_user_phone", columnList = "phone, state")}
)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class _User extends _Entity {

    private String password;
    private String email;
    private boolean actived;
    private boolean sysAdmin;

    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dob;

    @Column(unique = true)
    private String phone;
    private String phoneMin;
    private String phoneCountryPrefix;

    //@Basic(fetch = FetchType.LAZY)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    //@ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<_Role> roles = new ArrayList<>();

    @Embedded
    private _AuditInfo auditInfo;

    private boolean gender = true;

    @Column(length = 1000)
    private String note;

    private Long balance;

    private String colorName;

    public _User(String firstName, String lastName, String middleName, String phone, LocalDate dob, boolean gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;

    }

    public _User() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public boolean isSysAdmin() {
        return sysAdmin;
    }

    public void setSysAdmin(boolean sysAdmin) {
        this.sysAdmin = sysAdmin;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneMin() {
        return phoneMin;
    }

    public void setPhoneMin(String phoneMin) {
        this.phoneMin = phoneMin;
    }

    public String getPhoneCountryPrefix() {
        return phoneCountryPrefix;
    }

    public void setPhoneCountryPrefix(String phoneCountryPrefix) {
        this.phoneCountryPrefix = phoneCountryPrefix;
    }

    public List<_Role> getRoles() {
        return roles;
    }

    public void setRoles(List<_Role> roles) {
        this.roles = roles;
    }

    public _AuditInfo getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(_AuditInfo auditInfo) {
        this.auditInfo = auditInfo;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getShortName() {
        return String.format("%s %s",
                StringUtils.isEmpty(lastName) ? "" : lastName,
                StringUtils.isEmpty(firstName) ? "" : firstName);
    }

    public String getFullName() {
        return String.format("%s %s %s",
                StringUtils.isEmpty(lastName) ? "" : lastName,
                StringUtils.isEmpty(firstName) ? "" : firstName,
                StringUtils.isEmpty(middleName) ? "" : middleName);
    }


    public SelectItem getSelectItem() {
        return new SelectItem(getId(), getLastName() + " " + getFirstName(), "" + getId());
    }
}