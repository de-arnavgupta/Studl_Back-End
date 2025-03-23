package de.arnav.studl.security.service;

import org.springframework.context.annotation.Bean;

public class CustomLogicService {

    User user;

    public CustomLogicService(User user){
        this.user = user;
    }

    @Bean
    public Role assignRole(String email){

        String domain = email.split("@")[0];


        if(domain.equals("scaler.com")){

        }

        else if(domain.equals("sst.scaler.com")){
            user.setRole("ROLE_STUDENT");
        }



    }
}
