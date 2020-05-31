package com.qman.web.module.dashboard.route;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qman.web.constant.AuthRoute;
import com.qman.web.masterdata.MasterDataStorage;
import com.qman.web.masterdata.glossary.SideBarRouteEnum;

@Controller
@RequestMapping(value = "/")
public class DashBoardRoute {

    @Autowired
    private MasterDataStorage mdStorage;

    @GetMapping(value = "/")
    public String root(Model model) {
        return "redirect:" + AuthRoute.DashBoard.ROOT;
    }

    @GetMapping(value = AuthRoute.DashBoard.ROOT)
    public String index(Model model, HttpSession session) {
        model.addAttribute("title", SideBarRouteEnum.DASHBOARD.getDisplayName());
        return "dashboard";
    }
}
