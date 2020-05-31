package com.qman.web.orm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.SplittableRandom;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qman.web.constant.biz.CommonConstant;
import com.qman.web.masterdata.entity.RolePo;
import com.qman.web.orm.entity.converter.GenderConverter;
import com.qman.web.orm.entity.converter.UserStatusConverter;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.orm.glossary.UserStatus;

@Entity
@Table(name = "user")
@Embeddable
public class UserPo implements Serializable {

    private static final long serialVersionUID = -2625124228821132031L;

    @Id
    @JsonProperty(value = "id")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @JsonProperty(value = "username")
    @Column(name = "username", nullable = false, length = 50)
    private String userName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
        @JoinColumn(name = "user_id", insertable = false, updatable = false, referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", insertable = false, updatable = false, referencedColumnName = "id")})
    private List<RolePo> roleList;

    @JsonProperty(value = "password")
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @JsonProperty(value = "status")
    @Convert(converter = UserStatusConverter.class)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @JsonProperty(value = "last_name")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @JsonProperty(value = "middle_name")
    @Column(name = "middle_name", nullable = true, length = 100)
    private String middleName;

    @JsonProperty(value = "first_name")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @JsonProperty(value = "gender")
    @Convert(converter = GenderConverter.class)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @JsonProperty(value = "avatar_url")
    @Column(name = "avatar_url", nullable = true, length = 200)
    private String avatarUrl;

    @JsonProperty(value = "birthday")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @JsonProperty(value = "phone_no")
    @Column(name = "phone_no", nullable = false, length = 45)
    private String phoneNo;

    @JsonProperty(value = "join_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_date", nullable = false)
    private Date joinDate;

    @JsonProperty(value = "id_code")
    @Column(name = "id_code", nullable = true, length = 20)
    private String idCode;

    @JsonProperty(value = "address")
    @Column(name = "address", nullable = false, length = 300)
    private String address;

    @JsonProperty(value = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login", nullable = true)
    private Date lastLogin;

    @JsonProperty(value = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Transient
    private List<String> roleCodeList;

    @Transient
    private List<Long> roleIdList;

    @Transient
    public boolean isKeepLogin; // use for keep login at /login page

    @Transient
    public String newPassword;

    @PreUpdate
    private void onPreUpdate() {
        this.updatedAt = new Date();
    }

    @PrePersist
    public void onPrePersist() {
        this.id = new SplittableRandom().nextLong(CommonConstant.TRILLION);
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    public UserPo() {
        super();
    }

    public UserPo(Long id, String userName, List<RolePo> roleList, String password, UserStatus status, String lastName, String middleName,
        String firstName, Gender gender, String avatarUrl, Date birthday, String phoneNo, Date joinDate, String idCode, String address,
        Date lastLogin, Date createdAt, Date updatedAt, List<String> roleCodeList, List<Long> roleIdList, boolean isKeepLogin, String newPassword) {
        super();
        this.id = id;
        this.userName = userName;
        this.roleList = roleList;
        this.password = password;
        this.status = status;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstName = firstName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
        this.phoneNo = phoneNo;
        this.joinDate = joinDate;
        this.idCode = idCode;
        this.address = address;
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.roleCodeList = roleCodeList;
        this.roleIdList = roleIdList;
        this.isKeepLogin = isKeepLogin;
        this.newPassword = newPassword;
    }

    public UserPo(String userName, List<RolePo> roleList, String password, UserStatus status, String lastName, String middleName, String firstName,
        Gender gender, String avatarUrl, Date birthday, String phoneNo, Date joinDate, String idCode, String address, Date lastLogin,
        List<String> roleCodeList) {
        super();
        this.userName = userName;
        this.roleList = roleList;
        this.password = password;
        this.status = status;
        this.lastName = lastName;
        this.middleName = middleName;
        this.firstName = firstName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
        this.phoneNo = phoneNo;
        this.joinDate = joinDate;
        this.idCode = idCode;
        this.address = address;
        this.lastLogin = lastLogin;
        this.roleCodeList = roleCodeList;
    }

    public Long getId() { return id; }

    public String getUserName() { return userName; }

    public List<RolePo> getRoleList() { return roleList; }

    public String getPassword() { return password; }

    public UserStatus getStatus() { return status; }

    public String getLastName() { return lastName; }

    public String getMiddleName() { return middleName; }

    public String getFirstName() { return firstName; }

    public Gender getGender() { return gender; }

    public String getAvatarUrl() { return avatarUrl; }

    public Date getBirthday() { return birthday; }

    public String getPhoneNo() { return phoneNo; }

    public Date getJoinDate() { return joinDate; }

    public String getIdCode() { return idCode; }

    public String getAddress() { return address; }

    public Date getLastLogin() { return lastLogin; }

    public Date getCreatedAt() { return createdAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public List<String> getRoleCodeList() { return roleCodeList; }

    public List<Long> getRoleIdList() { return roleIdList; }

    public boolean isKeepLogin() { return isKeepLogin; }

    public String getNewPassword() { return newPassword; }

    public void setId(Long id) { this.id = id; }

    public void setUserName(String userName) { this.userName = userName; }

    public void setRoleList(List<RolePo> roleList) { this.roleList = roleList; }

    public void setPassword(String password) { this.password = password; }

    public void setStatus(UserStatus status) { this.status = status; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setGender(Gender gender) { this.gender = gender; }

    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }

    public void setIdCode(String idCode) { this.idCode = idCode; }

    public void setAddress(String address) { this.address = address; }

    public void setLastLogin(Date lastLogin) { this.lastLogin = lastLogin; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public void setRoleCodeList(List<String> roleCodeList) { this.roleCodeList = roleCodeList; }

    public void setRoleIdList(List<Long> roleIdList) { this.roleIdList = roleIdList; }

    public void setKeepLogin(boolean isKeepLogin) { this.isKeepLogin = isKeepLogin; }

    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

    // Utility function
    public String getFullName() { return lastName + " " + middleName + " " + firstName; }

    public String getRoleCodesForDisplay() {
        StringBuilder sb = new StringBuilder("");
        if (this.roleList != null) {
            int size = roleList.size();
            for (int i = 0; i < size; i++) {
                RolePo item = roleList.get(i);
                sb.append(item.getName());
                if (i < size - 1) sb.append(", ");
            }
        }
        return sb.toString();
    }
}
