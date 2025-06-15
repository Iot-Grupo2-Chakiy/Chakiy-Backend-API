package com.iot.error404.chakiy.profiles.application.internal.outboundservices.acl;

import com.iot.error404.chakiy.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalUserService {
    private final IamContextFacade iamContextFacade;

    public ExternalUserService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }
    public boolean checkUserExistsByUserId(Long UserId){return iamContextFacade.checkProfileExistsByUserId(UserId);}
}
