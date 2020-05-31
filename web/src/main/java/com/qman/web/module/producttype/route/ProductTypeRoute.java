package com.qman.web.module.producttype.route;

import javax.servlet.http.HttpSession;

import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qman.web.constant.AuthRoute;
import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.masterdata.entity.ProductTypePo;
import com.qman.web.masterdata.glossary.SideBarRouteEnum;
import com.qman.web.module.producttype.biz.ProductTypeBiz;
import com.qman.web.module.producttype.model.ProductTypeModel;
import com.qman.web.module.producttype.validator.ProductTypeValidator;

@Controller
@RequestMapping(value = "/")
public class ProductTypeRoute {

    @Autowired
    private MasterDataStorage mdStorage;

    @Autowired
    private ProductTypeBiz ptBiz;

    @Autowired
    private ProductTypeValidator ptValidator;

    @GetMapping(value = AuthRoute.ProductType.LIST)
    public String productType(Model model, HttpSession session) {
        model.addAttribute("title", SideBarRouteEnum.PRODUCT.getDisplayName());
        model.addAttribute("type_list", ptBiz.doGetAllTypeList());
        return "type-product/product-types";
    }

    @GetMapping(value = AuthRoute.ProductType.ADD)
    public String formNewProductType(Model model, HttpSession session) {
        model.addAttribute("title", SideBarRouteEnum.PRODUCT.getDisplayName());
        model.addAttribute("request", new ProductTypeModel());
        return "type-product/product-type-form";
    }

    @PostMapping(value = AuthRoute.ProductType.ADD)
    public String addProductType(@ModelAttribute("request") ProductTypeModel ptReqBody, Model model) {
        Triplet<Boolean, String, ProductTypePo> validationResult = ptValidator.validate(ptReqBody);
        model.addAttribute("header_title", validationResult.getValue1());
        if (validationResult.getValue0() == false) {
            return "redirect:" + AuthRoute.ProductType.ADD;
        } else {
            ProductTypePo po = validationResult.getValue2();
            ptBiz.doAddProductType(po);
        }
        return "redirect:" + AuthRoute.ProductType.LIST;
    }

    @PostMapping(value = AuthRoute.ProductType.DETAIL)
    public String formEditProductType(@RequestParam("id") Long id, Model model) {
        ProductTypePo pt = ptBiz.getById(id);
        model.addAttribute("pt_code", pt.getCode());
        ProductTypeModel ptModel = new ProductTypeModel(pt.getName(), pt.getDescription(), pt.getCode());
        ptModel.setId(pt.getId());
        model.addAttribute("title", SideBarRouteEnum.PRODUCT.getDisplayName());
        model.addAttribute("request", ptModel);
        return "type-product/product-type-form";
    }

    @PostMapping(value = AuthRoute.ProductType.EDIT)
    public String editProductType(@ModelAttribute("request") ProductTypeModel ptReqBody, Model model) {
        Triplet<Boolean, String, ProductTypePo> validationResult = ptValidator.validate(ptReqBody);
        model.addAttribute("header_title", validationResult.getValue1());
        if (validationResult.getValue0() == false) {
            return "redirect:" + AuthRoute.ProductType.EDIT;
        } else {
            ProductTypePo po = validationResult.getValue2();
            ptBiz.doUpdateProductType(po);
        }
        return "redirect:" + AuthRoute.ProductType.LIST;
    }
}
