package af.asr.springaxon.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountCreatedEvent extends BaseEvent<String> {

    private String accHolder;
    private String accHolderName;
    private BigDecimal balance;

    public AccountCreatedEvent(String id, String accHolder, String accHolderName, BigDecimal balance) {
        super(id);
        this.setAccHolder(accHolder);
        this.setBalance(balance);
        this.setAccHolderName(accHolderName);

    }
}