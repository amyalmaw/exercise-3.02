package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    DoRepository doRepository;

    @RequestMapping("/")
    public String listDos(Model model) {
        model.addAttribute("dos", doRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String DoForm(Model model) {
        model.addAttribute("do", new Do());
        return "doform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Do doObject, BindingResult result ) {
        if (result.hasErrors()) {
            return "doform";
        }
        doRepository.save(doObject);
        return "redirect:/";
    }

}
