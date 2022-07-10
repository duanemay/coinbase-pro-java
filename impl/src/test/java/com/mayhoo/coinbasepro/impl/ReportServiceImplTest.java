package com.mayhoo.coinbasepro.impl;

import com.mayhoo.coinbasepro.api.exchange.CoinbaseProExchange;
import com.mayhoo.coinbasepro.api.model.ReportRequest;
import com.mayhoo.coinbasepro.api.model.ReportResponse;
import com.mayhoo.coinbasepro.api.service.ReportService;
import com.mayhoo.coinbasepro.assertj.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mayhoo.coinbasepro.assertj.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
    @Mock
    CoinbaseProExchange mockExchange;

    ReportService service;

    @BeforeEach
    public void setup() {
        service = new ReportServiceImpl(mockExchange);
    }

    @Test
    void getters() {
        Assertions.assertThat(service).hasFieldOrPropertyWithValue("exchange", mockExchange);
    }

    @Test
    void getReportStatus() {
        ReportResponse mockResponse = ReportResponse.builder().build();
        when(mockExchange.get("/reports/123", ReportResponse.class)).thenReturn(mockResponse);
        ReportResponse actualResponse = service.getReportStatus("123");
        assertThat(actualResponse).isSameAs(mockResponse);
    }

    @Test
    void createReport() {
        ArgumentCaptor<ReportRequest> captor = ArgumentCaptor.forClass(ReportRequest.class);
        ReportResponse mockResponse = ReportResponse.builder().build();
        when(mockExchange.post(eq("/reports"), eq(ReportResponse.class), captor.capture())).thenReturn(mockResponse);
        ReportResponse response = service.createReport("type", "startDate", "endDate");
        Assertions.assertThat(captor.getValue())
                .hasType("type")
                .hasStartDate("startDate")
                .hasEndDate("endDate");
        Assertions.assertThat(response).isSameAs(mockResponse);
    }

}