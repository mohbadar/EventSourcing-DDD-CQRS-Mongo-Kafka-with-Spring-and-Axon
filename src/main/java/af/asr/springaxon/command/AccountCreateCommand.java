package af.asr.springaxon.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountCreateCommand extends BaseCommand<String> {

    private String accountHolder;
    private String accountHolderName;

    public AccountCreateCommand(String id, String holder, String accountHolderName) {
        super(id);
        this.accountHolder = holder;
        this.accountHolderName = accountHolderName;
    }

}



