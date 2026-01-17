package com.miswah.apiorbit.handler;

import com.miswah.apiorbit.enums.HttpMethods;
import com.miswah.apiorbit.model.ApiDefinitionModel;
import com.miswah.apiorbit.model.ApiModel;
import com.miswah.apiorbit.model.MockApiModel;
import com.miswah.apiorbit.repository.ApiDefinitionRepository;
import com.miswah.apiorbit.repository.ApiRepository;
import com.miswah.apiorbit.repository.MockApiRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockRequestHandler {

   private final MockApiRepository mockApiRepository;
   private final ApiRepository apiRepository;

   @Autowired
   public MockRequestHandler(MockApiRepository mockApiRepository, ApiRepository apiRepository){
       this.mockApiRepository = mockApiRepository;
       this.apiRepository = apiRepository;
   }

    @RequestMapping("/**")
    public ResponseEntity<String> handleRequest(
            HttpServletRequest request,
            @RequestBody(required = false) String requestBody) {

        String path = extractPath(request);
        String method = String.valueOf(request.getMethod());

        ApiModel apiModel = this.apiRepository.findByMockUrlAndHttpMethod(path, HttpMethods.valueOf(method)).orElseThrow();

        MockApiModel endpoint = this.mockApiRepository.findByApiModel(apiModel).orElseThrow();

        // Simulate delay if configured
        if (endpoint.getDelay() > 0) {
            try { Thread.sleep(endpoint.getDelay()); }
            catch (InterruptedException e) { /* handle */ }
        }

        // Build response with configured headers and status
        return ResponseEntity.status(HttpStatus.OK)
                .body(endpoint.getResponseBody());
    }

    private String extractPath(HttpServletRequest request) {
        // Extract path after context path
        return request.getRequestURI().substring(request.getContextPath().length()).replaceFirst("/mock", "");
    }
}
