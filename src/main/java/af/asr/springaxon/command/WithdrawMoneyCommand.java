package af.asr.springaxon.command;

import af.asr.springaxon.command.BaseCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class WithdrawMoneyCommand extends BaseCommand<String> {
    private  BigDecimal amount;

    public WithdrawMoneyCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}