package com.mayhoo.coinbasepro.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportResponse {
    String id;
    String type;
    String status;
    @JsonProperty("created_at")
    String createdAt;
    @JsonProperty("completed_at")
    String completedAt;
    @JsonProperty("expires_at")
    String expiresAt;
    @JsonProperty("file_url")
    String fileUrl;
    TimePeriod params;
}