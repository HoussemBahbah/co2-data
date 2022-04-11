package com.project.emissionsapi.controller;

import com.project.emissionsapi.entity.UserDetail;
import com.project.emissionsapi.model.MessageResponse;
import com.project.emissionsapi.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("api/userDetail")
    @CrossOrigin("*")
    public class UserDetailController {

//        @Autowired
//        private UserDetailService userDetailService;
//
//        @PostMapping
//        public MessageResponse save(@RequestBody UserDetail userDetail) {
//            return userDetailService.save(userDetail);
//        }
//
//        @PutMapping
//        public MessageResponse update(@RequestBody UserDetail userDetail) {
//            return userDetailService.update(userDetail);
//        }
//
//        @DeleteMapping("/{id}")
//        public MessageResponse delete(@PathVariable Long id) {
//            return userDetailService.delete(id);
//        }
//
//        @GetMapping
//        public List<UserDetail> findAll() {
//            return userDetailService.findAll();
//        }
//
//        @GetMapping("/{id}")
//        public UserDetail findById(@PathVariable Long id) {
//            return userDetailService.findById(id);
//        }

}
