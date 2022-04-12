package com.project.emissionsapi.controller;

import com.project.emissionsapi.entity.District;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.service.DistrictService;
import com.project.emissionsapi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/district")
@CrossOrigin("*")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping
    public MessageResponse save(@RequestBody District district) {
        return districtService.save(district);
    }

    @PutMapping
    public MessageResponse update(@RequestBody District district) {
        return districtService.update(district);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return districtService.delete(id);
    }

    @GetMapping
    public List<District> findAll() {
        return districtService.findAll();
    }

    @GetMapping("/{id}")
    public District findById(@PathVariable Long id) {
        return districtService.findById(id);
    }

}
