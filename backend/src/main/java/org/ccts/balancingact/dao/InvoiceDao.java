package org.ccts.balancingact.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.ccts.balancingact.model.api.InvoiceReport;

public interface InvoiceDao {
    void createScheduledInvoices(LocalDate rangeStart, LocalDate rangeEnd);
    List<InvoiceReport> generateProgramGroupInvoiceData(UUID programGroupId, LocalDate rangeStart, LocalDate rangeEnd);
}
