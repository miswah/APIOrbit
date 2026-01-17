package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.response.ApiByMethodCountResponse;
import com.miswah.apiorbit.dto.response.SystemKPIResponseDTO;
import com.miswah.apiorbit.enums.ResourceStatus;
import com.miswah.apiorbit.repository.ApiRepository;
import com.miswah.apiorbit.repository.MockApiRepository;
import com.miswah.apiorbit.repository.UserRepository;
import com.miswah.apiorbit.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DashboardServiceImpl  implements DashboardService {

    private final ApiRepository apiRepository;
    private final MockApiRepository mockApiRepository;
    private final UserRepository userRepository;

    @Autowired
    public DashboardServiceImpl(ApiRepository apiRepository, MockApiRepository mockApiRepository, UserRepository userRepository){
        this.apiRepository = apiRepository;
        this.mockApiRepository = mockApiRepository;
        this.userRepository = userRepository;
    }


    @Override
    public SystemKPIResponseDTO getSystemKPI() {
        long totalApis = this.apiRepository.count();
        long activeApis = this.apiRepository.countByStatus(ResourceStatus.ACTIVE);
        long pendingApis = this.apiRepository.countByStatus(ResourceStatus.PENDING);
        long deprecatedApis = this.apiRepository.countByStatus(ResourceStatus.DEPRECATED);
        long activeMocks = this.mockApiRepository.count();
        long totalUsers = this.userRepository.count();

        return new SystemKPIResponseDTO(totalApis,activeApis,pendingApis, deprecatedApis,activeMocks,totalUsers);
    }

    @Override
    public List<ApiByMethodCountResponse> getApiCountByMethod() {
//        ApiByMethodCountResponse get = new ApiByMethodCountResponse();
        return List.of();
    }
}
