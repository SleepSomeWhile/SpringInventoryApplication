package com.pwc.controller;

import com.pwc.Constants;
import com.pwc.model.Person;
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
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SystRepository systRepository;

    @Autowired
    private ProcRepository procRepository;

    @GetMapping("/persons")
    public String showPersonList(Model model) {
        return listByPage(model, 1, "id", "asc", "");
    }

    @GetMapping("/persons/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable int pageNumber, @RequestParam String sortField, @RequestParam String sortDir, @RequestParam String keyword){
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Page<Person> page = personRepository.findAll(keyword, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sort));

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listPersons", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        return "persons";
    }

    @GetMapping("/persons/new")
    public String showCreateNewPersonForm(Model model) {
        model.addAttribute("listSystems", systRepository.findAll());
        model.addAttribute("listProcesses", procRepository.findAll());
        model.addAttribute("person", new Person());
        return "person_form";
    }

    @PostMapping("/persons/save")
    public String savePerson(Person person) {
        personRepository.save(person);
        return "redirect:/persons";
    }

    @GetMapping("/persons/edit/{id}")
    public String showCreateNewPersonForm(@PathVariable Integer id, Model model) {
        model.addAttribute("listSystems", systRepository.findAll());
        model.addAttribute("listProcesses", procRepository.findAll());
        model.addAttribute("person", personRepository.findById(id).get());
        return "person_form";
    }

    @GetMapping("/persons/delete/{id}")
    public String deletePerson(@PathVariable Integer id) {
        personRepository.deleteById(id);
        return "redirect:/persons";
    }
}
