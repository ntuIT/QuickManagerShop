package com.qman.web.validator.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.qman.web.constant.biz.UserBizConstant;
import com.qman.web.masterdata.entity.RolePo;
import com.qman.web.masterdata.model.UserGender;
import com.qman.web.orm.glossary.Gender;
import com.qman.web.orm.glossary.UserStatus;

public class UserValidation {

    public static Boolean validateUsername(String username) {
        if (username == null || "".equals(username) || username.length() > 10 || username.length() < 6) return false;
        Pattern pattern = Pattern.compile(UserBizConstant.VALIDATION_USERNAME);
        return pattern.matcher(username).matches();
    }

    public static Boolean validatePassword(String password) {
        if (password == null || "".equals(password) || password.length() > 10 || password.length() < 6) return false;
        Pattern pattern = Pattern.compile(UserBizConstant.VALIDATION_PASSWORD_REGEX);
        return pattern.matcher(password).matches();
    }

    public static Boolean validateStatus(Byte status) {
        if (status == null) return false;
        UserStatus status2 = UserStatus.parse(status);
        return status2 != null;
    }

    public static List<Long> validateRoleName(String param, List<RolePo> roles) {
        if (param == null || param.isEmpty()) return null;
        String[] listRoleName = param.split(", ");
        if (listRoleName == null || listRoleName.length == 0) return null;
        List<String> listRoleName2 = Arrays.asList(listRoleName);
        List<Long> result = roles.stream().filter(Item -> listRoleName2.stream().anyMatch(role -> role.equals(Item.getName())))
            .map(item -> item.getId()).collect(Collectors.toList());
        if (result != null && result.size() > 0) { return result; }
        return null;
    }

    public static Boolean validateLastName(String lastName) {
        if (lastName == null || "".equals(lastName) || lastName.length() > 100) return false;
        return true;
        // TODO: Upgrade check with new validation
        // Pattern pattern = Pattern.compile(UserBizConstant.VALIDATION_HUMAN_NAME);
        // return pattern.matcher(lastName).matches();
    }

    public static Boolean validateMiddleName(String middleName) {
        if (middleName == null) return true;
        if ("".equals(middleName) || middleName.length() > 100) return false;
        return true;
        // TODO: Upgrade check with new validation
        // Pattern pattern = Pattern.compile(UserBizConstant.VALIDATION_HUMAN_NAME);
        // return pattern.matcher(middleName).matches();
    }

    public static Boolean validateFirstName(String firstName) {
        if (firstName == null || "".equals(firstName) || firstName.length() > 100) return false;
        return true;
        // TODO: Upgrade check with new validation
        // Pattern pattern = Pattern.compile(UserBizConstant.VALIDATION_HUMAN_NAME);
        // return pattern.matcher(firstName).matches();
    }

    public static Gender validateGender(String name, List<UserGender> genders) {
        if (name == null || name.isEmpty()) return null;
        List<UserGender> result = genders.stream().filter(item -> item.getDisplayName().equals(name)).collect(Collectors.toList());
        if (result != null && result.size() == 1) {
            UserGender matched = result.get(0);
            Gender correct = Gender.parse(matched.getValue());
            return correct;
        }
        return null;
    }

    public static Date validateBirthday(String birthday) {
        if (birthday == null || "".equals(birthday)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date d = sdf.parse(birthday);
            return d;
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean validatePhone(String phone) {
        if (phone == null || "".equals(phone)) return false;
        Pattern pattern = Pattern.compile(UserBizConstant.VALIDATION_PHONE);
        return pattern.matcher(phone).matches();
    }

    public static Date validateJoinDate(String joinDate) {
        if (joinDate == null || "".equals(joinDate)) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date d = sdf.parse(joinDate);
            return d;
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean validateIDCode(String idCode) {
        if (idCode == null || "".equals(idCode)) return false;
        Pattern pattern = Pattern.compile(UserBizConstant.VALIDATION_ID_CODE);
        return pattern.matcher(idCode).matches();
    }

    public static boolean validateAddress(String address) {
        if (address == null || "".equals(address) || address.length() > 300) return false;
        return true;
    }
}
