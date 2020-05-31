package com.qman.web.module.user.route;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qman.web.constant.AuthRoute;
import com.qman.web.constant.biz.SessionConstant;
import com.qman.web.constant.error.code.ErrorCode;
import com.qman.web.module.login.model.SessionData;
import com.qman.web.module.user.biz.UserInfoBiz;
import com.qman.web.module.user.model.ChangePassRequest;
import com.qman.web.module.user.validator.UserInfoValidator;
import com.qman.web.orm.entity.UserPo;

@RestController(value = "/")
public class UserSubmitRESTRoute {

    @Autowired
    private UserInfoValidator validator;

    @Autowired
    private UserInfoBiz biz;

    @PostMapping(value = AuthRoute.User.CHANGE_PASS, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteObj(@RequestBody ChangePassRequest requestModel, HttpSession session) {
        Map<String, String> response = new HashMap<String, String>();
        Long userId = ((SessionData) session.getAttribute(SessionConstant.SESSION_INFO)).getUserId();
        Triplet<Boolean, String, UserPo> validationResult = validator.validatePassChanging(requestModel, userId);
        if (validationResult.getValue0()) {
            if (biz.doChangePassword(validationResult.getValue2())) {
                response.put("message", validationResult.getValue1());
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
            response.put("message", ErrorCode.INTERNAL_ERROR);
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }
        response.put("message", validationResult.getValue1());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
}
