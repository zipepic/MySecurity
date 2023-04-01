package zipepic.project.MySecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zipepic.project.MySecurity.services.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }
    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String all(){
        return "all";
    }
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String all(@PathVariable int id){
        System.out.println(((UserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername());
        System.out.println(((UserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getAuthorities());
        return "id is "+id;
    }
}
