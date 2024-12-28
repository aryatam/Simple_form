
package com.example.formmanager.controller;


import com.example.formmanager.model.Field;
import com.example.formmanager.model.Form;
import com.example.formmanager.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    @Autowired
    private FormRepository formRepository;

    @GetMapping("/")
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    @PostMapping("/")
    public Form createForm(@RequestBody Form form) {
        form.getFields().forEach(field -> field.setForm(form));
        return formRepository.save(form);
    }


    @GetMapping("/{id}")
    public Form getFormById(@PathVariable Long id) {
        return formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));
    }


    @PutMapping("/{id}")
    public Form updateForm(@PathVariable Long id, @RequestBody Form updatedForm) {
        return formRepository.findById(id).map(existingForm -> {
            existingForm.setName(updatedForm.getName());
            existingForm.setPublished(updatedForm.isPublished());
            existingForm.getFields().clear();
            updatedForm.getFields().forEach(field -> {
                field.setForm(existingForm);
                existingForm.getFields().add(field);
            });
            return formRepository.save(existingForm);
        }).orElseThrow(() -> new RuntimeException("Form not found"));
    }


    @DeleteMapping("/{id}")
    public void deleteForm(@PathVariable Long id) {
        formRepository.deleteById(id);
    }

    @GetMapping("/{id}/fields")
    public List<Field> getFieldsOfForm(@PathVariable Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));
        return form.getFields();
    }

    @PutMapping("/{id}/fields")
    public Form updateFieldsOfForm(@PathVariable Long id, @RequestBody List<Field> updatedFields) {
        return formRepository.findById(id).map(existingForm -> {
            existingForm.getFields().clear();
            updatedFields.forEach(field -> {
                field.setForm(existingForm);
                existingForm.getFields().add(field);
            });
            return formRepository.save(existingForm);
        }).orElseThrow(() -> new RuntimeException("Form not found"));
    }

    @PostMapping("/{id}/publish")
    public Form togglePublishForm(@PathVariable Long id) {
        return formRepository.findById(id).map(form -> {
            form.setPublished(!form.isPublished());
            return formRepository.save(form);
        }).orElseThrow(() -> new RuntimeException("Form not found"));
    }


    @GetMapping("/published")
    public List<Form> getPublishedForms() {
        return formRepository.findAll().stream().filter(Form::isPublished).toList();
    }
}
