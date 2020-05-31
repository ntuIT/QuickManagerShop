package com.qman.web.module.user.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qman.web.ApplicationContextHolder;
import com.qman.web.orm.entity.UserPo;
import com.qman.web.orm.glossary.UserStatus;
import com.qman.web.orm.service.UserService;
import com.qman.web.utility.PasswordUtils;

@Component
public class UserInfoBiz {

    public static UserInfoBiz bean() {
        return ApplicationContextHolder.getContext().getBean(UserInfoBiz.class);
    }

    @Autowired
    private UserService service;

    /**
     * This is used for @LoginInterceptor for make new user @SessionInfo with latest data
     */
    public UserPo getById(Long id) {
        return service.getById(id);
    }

    public List<UserPo> searchByFullName(String lastName, String middleName, String firstName) {
        return this.service.searchByFullName(lastName, middleName, firstName);
    }

    public List<UserPo> getAll() { return this.service.getAll(); }

    public boolean doChangePassword(UserPo po) {
        // Hash password for more security
        po.setPassword(PasswordUtils.makePassword(po.newPassword));
        try {
            this.service.save(po);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean doUpdateInfo(UserPo po) {
        try {
            this.service.save(po);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean doResetPassword(UserPo po) {
        // Hash password for more security
        po.setPassword(PasswordUtils.makePassword(po.newPassword));
        po.setStatus(UserStatus.CHANGE_PASS);
        try {
            this.service.save(po);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean doDisable(UserPo po) {
        po.setStatus(UserStatus.DISABLED);
        try {
            this.service.save(po);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
