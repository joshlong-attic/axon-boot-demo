package com.example.complaint;

import com.example.coreapi.FileComplaintCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class ComplaintAPI {

    private final CommandGateway commandGateway;
    private final ComplaintQueryObjectRepository repository;

    public ComplaintAPI(CommandGateway commandGateway, ComplaintQueryObjectRepository repository) {
        this.commandGateway = commandGateway;
        this.repository = repository;
    }

    @PostMapping()
    public CompletableFuture<String> createNewComplaint(@RequestBody Map<String, String> request) {
        String complaintId = UUID.randomUUID().toString();
        System.out.println(complaintId);
        return commandGateway.send(new FileComplaintCommand(complaintId, request.get("description"), request.get("company")));
    }

    @GetMapping("/{complaintId}")
    public ComplaintQueryObject loadComplaint(@PathVariable String complaintId) {
        ComplaintQueryObject one = repository.findOne(complaintId);
        return one;
    }

    @GetMapping
    public List<ComplaintQueryObject> loadAll() {
        return repository.findAll();
    }
}
