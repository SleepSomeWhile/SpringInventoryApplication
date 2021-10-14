package com.pwc.controller;

import com.pwc.Constants;
import com.pwc.model.Syst;
import com.pwc.repository.PersonRepository;
import com.pwc.repository.ProcRepository;
import com.pwc.repository.SystRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SystController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SystRepository systRepository;

    @Autowired
    private ProcRepository procRepository;


    @GetMapping("/systems")
    public String showSystemList(Model model) {
        return listByPage(model, 1, "id", "asc", "");
    }

    @GetMapping("/systems/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Page<Syst> page = systRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listSystems", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        return "systems";
    }

    @GetMapping("/systems/new")
    public String showCreateNewSystemForm(Model model) {
        model.addAttribute("listPersons", personRepository.findAll());
        model.addAttribute("listProcesses", procRepository.findAll());
        model.addAttribute("system", new Syst());
        return "system_form";
    }

    @PostMapping("/systems/save")
    public String saveSystem(Syst system) {
        systRepository.save(system);
        return "redirect:/systems";
    }

    @GetMapping("/systems/edit/{id}")
    public String showCreateNewSystemForm(@PathVariable Integer id, Model model) {
        model.addAttribute("listPersons", personRepository.findAll());
        model.addAttribute("listProcesses", procRepository.findAll());
        model.addAttribute("system", systRepository.findById(id).get());
        return "system_form";
    }

    @GetMapping("/systems/delete/{id}")
    public String deleteSystem(@PathVariable Integer id) {
        systRepository.deleteById(id);
        return "redirect:/systems";
    }
}
