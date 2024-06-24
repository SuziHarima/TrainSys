package com.trainsys.TrainSys.controller;

import com.trainsys.TrainSys.controller.response.DashboardResponse;
import com.trainsys.TrainSys.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> listAllByUser(@RequestHeader(name = "Authorization") String token){
        log.info("GET /dashboard -> OK");
        return ResponseEntity.ok(dashboardService.searchByUser(token.substring(7)));
    }
}
