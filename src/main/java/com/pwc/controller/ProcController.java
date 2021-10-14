package com.pwc.controller;

import com.pwc.Constants;
import com.pwc.model.Proc;
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
public class ProcController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SystRepository systRepository;

    @Autowired
    private ProcRepository procRepository;


    @GetMapping("/processes")
    public String showProcessList(Model model) {
        return listByPage(model, 1, "id", "asc", "");
    }

    @GetMapping("/processes/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Page<Proc> page = procRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listProcesses", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        return "processes";
    }

    @GetMapping("/processes/new")
    public String showCreateNewProcessForm(Model model) {
        model.addAttribute("listPersons", personRepository.findAll());
        model.addAttribute("listSystems", systRepository.findAll());
        model.addAttribute("process", new Proc());
        return "process_form";
    }

    @PostMapping("/processes/save")
    public String saveProcess(Proc process) {
        procRepository.save(process);
        return "redirect:/processes";
    }

    @GetMapping("/processes/edit/{id}")
    public String showCreateNewProcessForm(@PathVariable Integer id, Model model) {
        model.addAttribute("listPersons", personRepository.findAll());
        model.addAttribute("listSystems", systRepository.findAll());
        model.addAttribute("process", procRepository.findById(id).get());
        return "process_form";
    }

    @GetMapping("/processes/delete/{id}")
    public String deletePerson(@PathVariable Integer id) {
        procRepository.deleteById(id);
        return "redirect:/processes";
    }
}
