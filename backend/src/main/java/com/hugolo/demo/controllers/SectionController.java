package com.hugolo.demo.controllers;

import com.hugolo.demo.entities.Section;
import com.hugolo.demo.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/section")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @GetMapping
    public List<Section> getAllSections() {return sectionService.getAllSections();}
}
