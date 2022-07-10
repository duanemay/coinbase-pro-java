package com.mayhoo.coinbasepro.api.service;

import com.mayhoo.coinbasepro.api.model.ReportResponse;

public interface ReportService {
    ReportResponse createReport(String type, String startDate, String endDate);

    ReportResponse getReportStatus(String id);
}