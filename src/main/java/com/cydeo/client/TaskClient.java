package com.cydeo.client;

import com.cydeo.dto.TaskResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "task-service")
public interface TaskClient {

    @PutMapping("/api/v1/task/complete/project/{projectCode}")
    ResponseEntity<TaskResponseDTO> completeByProject(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable("projectCode") String projectCode);

    @DeleteMapping("/api/v1/task/delete/project/{projectCode}")
    ResponseEntity<TaskResponseDTO> deleteByProject(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable("projectCode") String projectCode);

    @GetMapping("/api/v1/task/count/project/{projectCode}")
    ResponseEntity<TaskResponseDTO> getCountsByProject(@RequestHeader(value = "Authorization") String authorizationHeader, @PathVariable("projectCode") String projectCode);

}
