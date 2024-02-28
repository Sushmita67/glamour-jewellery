package com.glamourjewellery.glamour_jewellery.service;

import com.glamourjewellery.glamour_jewellery.pojo.AuthenticateRequest;
import com.glamourjewellery.glamour_jewellery.pojo.AuthenticateResponse;

public interface AuthenticateService {

    AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest);
}
