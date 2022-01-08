package com.hugolo.demo.services;

import com.hugolo.demo.entities.Section;
import com.hugolo.demo.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    SectionRepository sectionRepository;

    public List<Section> getAllSections() { return sectionRepository.findAll();}

}
