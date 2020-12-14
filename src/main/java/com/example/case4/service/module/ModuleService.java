package com.example.case4.service.module;

import com.example.case4.model.Module;
import com.example.case4.repo.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ModuleService implements IModuleService{

    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public Iterable<Module> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Optional<Module> findById(Long id) {
        return moduleRepository.findById(id);
    }

    @Override
    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public void remove(Long id) {
        moduleRepository.deleteById(id);
    }
}
