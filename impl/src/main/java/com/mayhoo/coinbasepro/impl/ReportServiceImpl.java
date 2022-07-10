package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.ReportRequest;
import com.mayhoo.coinbasepro.api.model.ReportResponse;
import com.mayhoo.coinbasepro.api.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private static final String REPORTS_ENDPOINT = "/reports";

    CoinbaseProExchange exchange;

    @Override
    public ReportResponse createReport(String type, String startDate, String endDate) {
        ReportRequest reportRequest = new ReportRequest(type, startDate, endDate);
        return exchange.post(REPORTS_ENDPOINT, ReportResponse.class, reportRequest);
    }

    @Override
    public ReportResponse getReportStatus(String id) {
        return exchange.get(REPORTS_ENDPOINT + "/" + id, ReportResponse.class);
    }
}