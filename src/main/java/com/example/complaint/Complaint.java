package com.example.complaint;

import com.example.coreapi.ComplaintFiledEvent;
import com.example.coreapi.FileComplaintCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Complaint {

    @AggregateIdentifier
    private String complaintId;

    public Complaint() {
    }

    @CommandHandler
    public Complaint(FileComplaintCommand cmd) {
        apply(new ComplaintFiledEvent(cmd.getComplaintId(), cmd.getDescription(), cmd.getCompany()));
    }

    @EventSourcingHandler
    protected void on(ComplaintFiledEvent event) {
        this.complaintId = event.getComplaintId();
    }

}
