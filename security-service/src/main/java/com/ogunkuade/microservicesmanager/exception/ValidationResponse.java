package com.ogunkuade.microservicesmanager.exception;

import java.util.Date;
import java.util.List;

public record ValidationResponse(Date timestamp, String subject, List<String> errorList) {
}
