package chinh.nguyen.controller;

import chinh.nguyen.model.Product;
import chinh.nguyen.service.category.ICategoryService;
import chinh.nguyen.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/product")
    public ModelAndView listProduct(){
        ModelAndView modelAndView = new ModelAndView("admin/product/index");
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }
    @GetMapping("/product-add")
    public ModelAndView formAdd(){
        ModelAndView modelAndView = new ModelAndView("admin/product/add");
        Product product = new Product();
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("product", product);
        return modelAndView;
    }
    @PostMapping("/product-add")
    public ModelAndView addProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("admin/product/add");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "Create success !");
        return modelAndView;
    }
}
