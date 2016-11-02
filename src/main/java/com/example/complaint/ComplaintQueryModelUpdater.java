package com.example.complaint;

import com.example.coreapi.ComplaintFiledEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ComplaintQueryModelUpdater {

    private final ComplaintQueryObjectRepository repository;

    public ComplaintQueryModelUpdater(ComplaintQueryObjectRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ComplaintFiledEvent event) {
        repository.save(new ComplaintQueryObject(event.getComplaintId(), event.getDescription(), event.getCompany()));
    }
}
