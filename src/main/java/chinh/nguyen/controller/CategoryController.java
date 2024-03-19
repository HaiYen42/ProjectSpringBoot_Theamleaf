package chinh.nguyen.controller;

import chinh.nguyen.model.Category;
import chinh.nguyen.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;


    // Phân trang
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 3;
        Page<Category> page = categoryService.findPaginated(pageNo, pageSize);
        List<Category> categoryList = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("categories", categoryList);
        return "admin/category/index";
    }

    @GetMapping("/categories")
    public String listCategory(Model model) {
       return findPaginated(1, model);
    }

    //Cách 2--> Làm thiếu
    public ModelAndView showListCategory(){
        ModelAndView modelAndView = new ModelAndView("/admin/category/index");
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("/categories-add")
    public ModelAndView showFormAdd() {
        ModelAndView modelAndView = new ModelAndView("admin/category/add");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/categories-add")
    public ModelAndView saveCustomer(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("admin/category/add");
        if (result.hasErrors()) {
            return modelAndView;
        } else {
            if (categoryService.existsByName(category.getName())) {
                modelAndView.addObject("message", "Tên category bị trùng");
                return modelAndView;
            }
            categoryService.save(category);
            modelAndView.addObject("category", new Category());
            modelAndView.addObject("message", "New category created successfully !!!");
            return modelAndView;
        }
    }

    @GetMapping("/categories-edit/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("admin/category/edit");
            modelAndView.addObject("category", category.get());
            return modelAndView;

        } else {
            return new ModelAndView("/error.404");
        }

    }

    @PostMapping("/categories-edit")
    public ModelAndView editCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("admin/category/edit");
        modelAndView.addObject("message", "Edit successful !");
        return modelAndView;
    }

    @GetMapping("/category-delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        System.out.println("id" + id);
        categoryService.remove(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Delete successful !");
        showListCategory();
        return "admin/category/index";
    }

    @GetMapping("/change-status/{id}")
    public ModelAndView changeStatus(@PathVariable("id") Long id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            category.get().setStatus(!category.get().isStatus());
            categoryService.save(category.get());
            return showListCategory();
        }
        return new ModelAndView("error.404");
    }

    @GetMapping("/change-img/{id}")
    public ModelAndView showFormChangeImg(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("admin/upload-image");
        return modelAndView;
    }
//    @PostMapping("/change-img")
//    public ModelAndView updateImg(HttpServletRequest request, HttpServletResponse response,
//                                  @ModelAttribute("category") Category category){
//        String image = request.getParameter("avatar");
//        category.setImage(image);
//        categoryService.save(category);
//        return new ModelAndView("admin/category/index");
//    }

}
