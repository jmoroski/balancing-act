package org.ccts.balancingact.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ccts.balancingact.dao.InvoiceDao;
import org.ccts.balancingact.model.api.InvoiceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@RestController
@RequestMapping(path = "/api/reports")
public class ReportController {
    @Autowired
    private Configuration configuration;

    @Autowired
    private InvoiceDao invoiceDao;

    @GetMapping(path = "/invoices", params = {"startDate", "endDate", "programGroup"})
    public ResponseEntity<?> getInvoices(
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
            @RequestParam UUID programGroup) {


        System.out.println("generating invoices for " + programGroup + " from " + startDate + " to " + endDate);
        Map<String, List<InvoiceReport>> reports = Collections.singletonMap("reports",
                this.invoiceDao.generateProgramGroupInvoiceData(programGroup, startDate, endDate));

        try {
            String value = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("invoice.template"), reports);
            return new ResponseEntity<>(value, HttpStatus.OK);
        } catch (IOException | TemplateException e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
