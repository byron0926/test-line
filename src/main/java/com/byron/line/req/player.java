package com.byron.line.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class player {

    private Long id;
    private Long CompanyId;
    private String playerName;
    private String depositMethod;
    private String depositMarks;
    private String playerAccount;

}
