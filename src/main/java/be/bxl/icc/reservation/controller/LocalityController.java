package be.bxl.icc.reservation.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import be.bxl.icc.reservation.model.Locality;
import be.bxl.icc.reservation.model.LocalityService;


@Controller
public class LocalityController {
	@Autowired
	LocalityService service;

	@GetMapping("/localities")
    public String index2(Model model) {
		List<Locality> localities = service.getAll();

		model.addAttribute("localities", localities);
		model.addAttribute("title", "Liste des localités");
		
        return "locality/index";
    }
	
	@GetMapping("/localities/{id}")
    public String show(Model model, @PathVariable("id") String id) {
		Locality locality = service.get(id);

		model.addAttribute("locality", locality);
		model.addAttribute("title", "Fiche d'une localité");
		
        return "locality/show";
    }
	@GetMapping("/localities/create")
	public String create(Model model) {
	   Locality locality = new Locality(null, null);

	    model.addAttribute("locality", locality);
		
	    return "locality/create";
	}
	
	@PostMapping("/localities/create")
	public String store(@Valid @ModelAttribute("locality") Locality locality, BindingResult bindingResult, Model model) {
	    
	    if (bindingResult.hasErrors()) {
		return "locality/create";
	    }
		    
	    service.add(locality);
	    
	    return "redirect:/localities/"+locality.getId();
	}

}

