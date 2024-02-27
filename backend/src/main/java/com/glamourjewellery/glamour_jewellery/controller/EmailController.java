package com.glamourjewellery.glamour_jewellery.controller;


import com.glamourjewellery.glamour_jewellery.pojo.EmailRequest;
import com.glamourjewellery.glamour_jewellery.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recover")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;


    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody EmailRequest emailRequest){
        this.emailService.resetPassword(emailRequest);
    }

}
