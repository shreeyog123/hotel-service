package com.myhotel.hotelservice.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericErrorResponse {

    public final Integer errorCode;

    public final String errorMessage;


}
