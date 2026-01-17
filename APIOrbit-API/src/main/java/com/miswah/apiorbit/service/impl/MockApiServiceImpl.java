package com.miswah.apiorbit.service.impl;

import com.miswah.apiorbit.dto.response.MockApiResponseDTO;
import com.miswah.apiorbit.model.ApiModel;
import com.miswah.apiorbit.model.MockApiModel;
import com.miswah.apiorbit.repository.ApiRepository;
import com.miswah.apiorbit.repository.MockApiRepository;
import com.miswah.apiorbit.service.MockApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MockApiServiceImpl implements MockApiService {
    private final MockApiRepository mockApiRepository;
    private final ApiRepository apiRepository;

    @Autowired
    public MockApiServiceImpl(MockApiRepository mockApiRepository, ApiRepository apiRepository){
        this.mockApiRepository = mockApiRepository;
        this.apiRepository = apiRepository;
    }

    @Override
    public MockApiResponseDTO getMockByApiId(UUID id) {
        ApiModel apiModel = this.apiRepository.findById(id).orElseThrow();
        MockApiModel mockApiModel = this.mockApiRepository.findByApiModel(apiModel).orElseThrow();

        return this.convertToDto(mockApiModel);
    }

    @Override
    public MockApiResponseDTO updateMockApiById(UUID id, MockApiResponseDTO dto) {
        MockApiModel model = this.mockApiRepository.findById(id).orElseThrow();

        model.setDelay((int) dto.delay());
        model.setRequestBody(dto.schemaRequest());
        model.setRequestBody(dto.schemaResponse());

        this.mockApiRepository.save(model);

        return this.convertToDto(model);
    }

    private MockApiResponseDTO convertToDto(MockApiModel model){
        return new MockApiResponseDTO(model.getId(), model.getDelay(), model.getRequestBody(), model.getResponseBody());
    }
}
