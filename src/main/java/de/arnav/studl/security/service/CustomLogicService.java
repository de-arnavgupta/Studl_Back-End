package de.arnav.studl.security.service;

import de.arnav.studl.exception.RoleAssignmentException;
import de.arnav.studl.model.RoleType;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomLogicService {

    private final ExcelEmailChecker excelEmailChecker;

    private static final String EXCEL_FILE_PATH = "src/main/resources/admins.xlsx";

    public CustomLogicService(ExcelEmailChecker excelEmailChecker) {
        this.excelEmailChecker = excelEmailChecker;
    }

    public Set<RoleType> assignRoles(String email) {
        Set<RoleType> roles = new HashSet<>();

        try {
            String domain = email.split("@")[1];

            if (domain.equals("scaler.com")) {
                if (excelEmailChecker.isEmailInExcel(email, EXCEL_FILE_PATH)) {
                    roles.add(RoleType.ROLE_ADMIN); // Assign admin role if found in the Excel sheet
                }
            }
            else if (domain.equals("sst.scaler.com")) {
                roles.add(RoleType.ROLE_STUDENT); // Assign student role
            }

        } catch (Exception e) {
            throw new RoleAssignmentException();
        }

        return roles;
    }
}
