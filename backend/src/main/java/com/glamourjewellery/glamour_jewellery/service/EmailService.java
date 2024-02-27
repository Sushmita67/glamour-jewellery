package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.pojo.EmailRequest;

public interface EmailService {

    void resetPassword(EmailRequest emailRequest);
}
