package com.example.complaint;

import com.example.coreapi.ComplaintFiledEvent;
import com.example.coreapi.FileComplaintCommand;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.GivenWhenThenTestFixture;
import org.junit.Before;
import org.junit.Test;

public class ComplaintTest {
    private FixtureConfiguration<Complaint> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new GivenWhenThenTestFixture<>(Complaint.class);
    }

    @Test
    public void testCreateComplaint() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new FileComplaintCommand("complaint1", "Not happy!", "Sears"))
                .expectEvents(new ComplaintFiledEvent("complaint1", "Not happy!", "Sears"));
    }
}
