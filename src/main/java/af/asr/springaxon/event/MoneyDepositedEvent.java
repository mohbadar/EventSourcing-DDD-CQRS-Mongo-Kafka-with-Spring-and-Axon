package af.asr.springaxon.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MoneyDepositedEvent extends BaseEvent<String> {
    private  BigDecimal amount;

    public MoneyDepositedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }

}