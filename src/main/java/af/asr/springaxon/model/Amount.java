package af.asr.springaxon.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Amount implements Serializable{

    private static final long serialVersionUID = -5684258230592753293L;
    private BigDecimal amount;



}
