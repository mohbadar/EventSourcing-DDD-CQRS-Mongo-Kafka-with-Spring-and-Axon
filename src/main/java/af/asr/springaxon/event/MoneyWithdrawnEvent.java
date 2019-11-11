package af.asr.springaxon.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MoneyWithdrawnEvent extends BaseEvent<String> {
    private  BigDecimal amount;

    public MoneyWithdrawnEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}