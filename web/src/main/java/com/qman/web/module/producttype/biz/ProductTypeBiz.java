package com.qman.web.module.producttype.biz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qman.web.masterdata.entity.ProductTypePo;
import com.qman.web.masterdata.service.ProductTypeService;

@Component
@Transactional(rollbackFor = Exception.class)
public class ProductTypeBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeBiz.class);

    @Autowired
    ProductTypeService productTypeService;

    public List<ProductTypePo> doGetAllTypeList() {
        try {
            return productTypeService.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public ProductTypePo getById(Long id) {
        try {
            return productTypeService.getById(id);
        } catch (Exception e) {
            return new ProductTypePo();
        }
    }

    public boolean doAddProductType(ProductTypePo productType) {
        try {
            productTypeService.save(productType);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean doUpdateProductType(ProductTypePo productType) {
        try {
            productTypeService.save(productType);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean doDelete(String code) {
        ProductTypePo po = this.productTypeService.getByCode(code);
        if (po == null) return false;
        try {
            productTypeService.delete(po);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
