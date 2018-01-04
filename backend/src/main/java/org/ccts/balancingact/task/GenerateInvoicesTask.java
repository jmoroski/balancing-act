package org.ccts.balancingact.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GenerateInvoicesTask {
    @Scheduled(cron = "0 0 5 * * *")
    public void generate() {
    }
}
