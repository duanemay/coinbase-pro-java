package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class ReportRequest {
    String type;
    @JsonProperty("start_date")
    String startDate;
    @JsonProperty("end_date")
    String endDate;
}