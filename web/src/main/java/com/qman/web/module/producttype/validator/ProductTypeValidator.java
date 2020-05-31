package com.qman.web.module.producttype.validator;

import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qman.web.constant.biz.ProfileBizConstant;
import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.masterdata.entity.ProductTypePo;
import com.qman.web.masterdata.service.ProductTypeService;
import com.qman.web.module.producttype.model.ProductTypeModel;

@Component
public class ProductTypeValidator {

    @Autowired
    private MasterDataStorage mdStorage;

    @Autowired
    private ProductTypeService productTypeService;

    public Triplet<Boolean, String, ProductTypePo> validate(ProductTypeModel requestModel) {

        ProductTypePo productType = new ProductTypePo();

        if (checkStringValid(requestModel.getName()) ==true)
        {
            productType.setName(requestModel.getName());
        } else return Triplet.with(false, "tên chứa kí tự không hợp lệ", productType);

        if (checkStringValid(requestModel.getDesc()) ==true)
        {
            productType.setDescription(requestModel.getDesc());
        } else return Triplet.with(false, "mô tả chứa kí tự không hợp lệ", productType);

        String typeCode = requestModel.getCode();
        if (checkStringValid(typeCode) ==true)
        {
            boolean flag = checkTypeCodeExist(typeCode);
            if (flag == false)  productType.setCode(typeCode);
            else return Triplet.with(false, "mã bị trùng", productType);
        } else return Triplet.with(false, "mã chứa kí tự không hợp lệ", productType);

        return Triplet.with(true, "success", productType);
    }
    public boolean checkStringValid(String string)
    {
        char[] chars = ProfileBizConstant.INVALID_CHAR_LIST.toCharArray();
        for (char i : chars)
        {
            if(string.contains(Character.toString(i)) == true)
                return false;
        }
        return true;
    }
    public boolean checkTypeCodeExist(String code) //code bị trùng thì TRUE, không trùng thì FALSE
    {
        ProductTypePo matched = productTypeService.getByCode(code);
        if (matched == null) //trường hợp không get được type nào có cùng TypeCode
        {
            return false;
        }
        return true; //trường hợp TypeCode bị trùng
    }
}
