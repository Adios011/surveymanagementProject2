package com.gmail.muhsener98.surveymanagementproject2.ui.controller;

import com.gmail.muhsener98.surveymanagementproject2.ui.model.request.user.LoginForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {

    @Operation(summary = "User Login")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Response Headers",
                    headers = {
                            @Header(name = "Authorization", description = "Bearer 'JWT value here'", schema = @Schema(type = "string")),
                            @Header(name = "userId", description = "'Public User Id value here'", schema = @Schema(type = "string"))
                    })
    })
    @PostMapping("/login")
    public void theFakeLogin(@RequestBody LoginForm loginForm) {
        throw new IllegalStateException("This method should not be called. This method is implemented by Spring Security.");
    }
}
