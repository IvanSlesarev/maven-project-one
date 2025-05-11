package com.vanya.mapper;

import com.vanya.dto.driverRequest.DriverRequestCreateEditDto;
import com.vanya.dto.driverRequest.DriverRequestReadDto;
import com.vanya.dto.user.UserCrateEditDto;
import com.vanya.entity.DriverRequest;
import com.vanya.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DriverRequestCreateEditMapper implements Mapper<DriverRequestCreateEditDto, DriverRequest> {

    @Override
    public DriverRequest map(DriverRequestCreateEditDto object) {
        DriverRequest driverRequest = new DriverRequest();
        copy(object, driverRequest);
        return driverRequest;
    }

    @Override
    public DriverRequest map(DriverRequestCreateEditDto fromObject, DriverRequest toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(DriverRequestCreateEditDto object, DriverRequest driverRequest) {
        driverRequest.setCargo(object.getCargo());
        driverRequest.setUser(object.getUser());
        driverRequest.setPrice(object.getPrice());
        driverRequest.setStatus(object.getStatus());
    }
}
