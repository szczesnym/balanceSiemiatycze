package com.oerlemans.siemiatycze.balance.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Setter

@Component
public class Weight {
    double balanceResult;
    String timeStamp;
}
