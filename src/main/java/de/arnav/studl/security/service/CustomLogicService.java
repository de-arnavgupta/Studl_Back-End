package de.arnav.studl.security.service;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomLogicService {

    private final ExcelEmailChecker excelEmailChecker;

    public CustomLogicService(ExcelEmailChecker excelEmailChecker) {
        this.excelEmailChecker = excelEmailChecker;
    }

    public Set<String> assignRoles(String email) {
        Set<String> roles = new HashSet<>();

        try {
            String domain = email.split("@")[1];

            if (domain.equals("scaler.com")) {
                if (excelEmailChecker.isEmailInExcel(email)) {
                    roles.add("ROLE_ADMIN"); // Assign admin role if found in the Excel sheet
                }
            }
            else if (domain.equals("sst.scaler.com")) {
                roles.add("ROLE_STUDENT"); // Assign student role
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while assigning roles", e);
        }

        return roles;
    }
}
