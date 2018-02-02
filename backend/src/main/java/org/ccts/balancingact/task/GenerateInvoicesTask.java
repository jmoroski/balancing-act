package org.ccts.balancingact.task;

import java.time.LocalDate;

import org.ccts.balancingact.dao.InvoiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GenerateInvoicesTask {
    @Autowired
    private InvoiceDao invoiceDao;

    @Scheduled(cron = "0 0 5 * * *")
    public void generate() {
        this.invoiceDao.createScheduledInvoices(LocalDate.now(), LocalDate.now());
    }
}
