package de.arnav.studl.dto;

import java.util.Arrays;

public class UserEmailDto {
    private String userEmail;
    private String identifier;
    private String domain;
    private String subdomain;
    private String tld;

    private void emailPartExtractor(String userEmail) {
        String[] parts = userEmail.split("@");
        this.identifier = parts[0];

        String[] partsInDomain = parts[1].split("\\.");
        int l = partsInDomain.length;

        if (l >= 2) {
            if (l >= 3) {
                this.subdomain = String.join(".", Arrays.copyOfRange(partsInDomain, 0, l - 2));
            }
            this.domain = partsInDomain[l - 2];
            this.tld = partsInDomain[l - 1];

        }
    }

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDomain() {
        return domain;
    }

    public String getSubdomain() {
        return subdomain;
    }

    public String getTld() {
        return tld;
    }
}
