package com.qman.web.module.producttype.route;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qman.web.constant.AuthRoute;
import com.qman.web.module.producttype.biz.ProductTypeBiz;

@RestController
@RequestMapping(value = "/")
public class ProductTypeRESTRoute {

    @Autowired
    private ProductTypeBiz ptBiz;

    @PostMapping(value = AuthRoute.ProductType.DELETE
        , consumes = MediaType.APPLICATION_JSON_VALUE
        , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteObj(@RequestBody Map<String, String> body) {
        String code = body.get("code");
        Map<String, String> response = new HashMap<String, String>();
        if (code != null && ptBiz.doDelete(code)) {
            response.put("message", "delete successfully");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        response.put("message", "delete unsuccessfully");
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
}
