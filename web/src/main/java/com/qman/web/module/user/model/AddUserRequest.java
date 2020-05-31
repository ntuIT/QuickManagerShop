package com.qman.web.module.user.model;

public class AddUserRequest {

    private String roleCode;

    private String username;

    private String password;

    private String lastName;

    private String middleName;

    private String firstName;

    private String address;

    private String gender;

    private String birthday;

    private String phone;

    private String joinDate;

    private String idCode;

    public String getRoleCode() { return roleCode; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getLastName() { return lastName; }

    public String getMiddleName() { return middleName; }

    public String getFirstName() { return firstName; }

    public String getAddress() { return address; }

    public String getGender() { return gender; }

    public String getBirthday() { return birthday; }

    public String getPhone() { return phone; }

    public String getJoinDate() { return joinDate; }

    public String getIdCode() { return idCode; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setAddress(String address) { this.address = address; }

    public void setGender(String gender) { this.gender = gender; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setJoinDate(String joinDate) { this.joinDate = joinDate; }

    public void setIdCode(String idCode) { this.idCode = idCode; }

    public void setRoleCode(String roleCode) { this.roleCode = roleCode; }
}
