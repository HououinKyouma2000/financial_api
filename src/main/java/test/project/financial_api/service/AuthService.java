package test.project.financial_api.service;

import test.project.financial_api.api.dto.auth.AuthRequest;
import test.project.financial_api.api.dto.auth.AuthResponse;

public interface AuthService {
  AuthResponse login(AuthRequest request);
}
