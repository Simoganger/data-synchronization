package com.sprintgether.datasynchro.model;

import lombok.Builder;

@Builder
public record ResponseMessage(String message, Integer code, Object data) {}
