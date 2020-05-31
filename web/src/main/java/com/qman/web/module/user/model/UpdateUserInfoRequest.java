package com.qman.web.module.user.model;

public class UpdateUserInfoRequest {

    private String roleCode;

    private String lastName;

    private String middleName;

    private String firstName;

    private String address;

    private String gender;

    private String birthday;

    private String phone;

    private String joinDate;

    private String idCode;

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {

        private UpdateUserInfoRequest result;

        private Builder() {
            result = new UpdateUserInfoRequest();
        }

        public UpdateUserInfoRequest build() {
            return result;
        }

        public Builder lastName(String param) {
            this.result.lastName = param;
            return this;
        }

        public Builder middleName(String param) {
            this.result.middleName = param;
            return this;
        }

        public Builder firstName(String param) {
            this.result.firstName = param;
            return this;
        }

        public Builder address(String param) {
            this.result.address = param;
            return this;
        }

        public Builder gender(String param) {
            this.result.gender = param;
            return this;
        }

        public Builder birthday(String param) {
            this.result.birthday = param;
            return this;
        }

        public Builder phone(String param) {
            this.result.phone = param;
            return this;
        }

        public Builder joinDate(String param) {
            this.result.joinDate = param;
            return this;
        }

        public Builder idCode(String param) {
            this.result.idCode = param;
            return this;
        }

        public Builder roleCode(String param) {
            this.result.roleCode = param;
            return this;
        }
    }

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

    public String getRoleCode() { return roleCode; }

    public void setRoleCode(String roleCode) { this.roleCode = roleCode; }
}
