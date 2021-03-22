package com.sainsburys.psr.spike.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndexResponse implements Serializable {
    private boolean status;
}
