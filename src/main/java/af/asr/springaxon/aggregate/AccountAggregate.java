package af.asr.springaxon.aggregate;

import af.asr.springaxon.command.AccountCreateCommand;
import af.asr.springaxon.command.WithdrawMoneyCommand;
import af.asr.springaxon.event.AccountCreatedEvent;
import lombok.Data;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.math.BigDecimal;

@Aggregate
@Data
@ToString
public class AccountAggregate {

    @Autowired
    private EventSourcingRepository<AccountAggregate> repo;


    @AggregateIdentifier
    private String id;

    private BigDecimal balance;

    private String accountHolder;

    private String accHolderName;


    @CommandHandler
    public AccountAggregate(AccountCreateCommand command)
    {
        Assert.hasLength(command.getAccountHolder(), "Account holder must have a name");
        Assert.hasLength(command.getId(), "Account id must have length greater than Zero");
        apply(new AccountCreatedEvent(command.getId(), command.getAccountHolder(), command.getAccountHolderName(), new BigDecimal(0)));
    }

    @EventSourcingHandler
    public void handle(AccountCreatedEvent event)
    {
        this.id = event.getId();
        this.accountHolder = event.getAccHolder();
        this.accHolderName = event.getAccHolderName();
        this.balance = event.getBalance();
    }

    @CommandHandler
    public void on(DepositMoneyCommand command) {
        System.out.println("deposit  received "+command.getId());
        Assert.isTrue(command.getAmount().compareTo(BigDecimal.ZERO) > 0 , "Amount should be a positive number");
        apply(new MoneyDepositedEvent(command.getId(), command.getAmount()));
    }

    @EventSourcingHandler
    public void handle(MoneyDepositedEvent event) {
        //AccountAggregate aggregate = repository.load(event.getId()).getWrappedAggregate().getAggregateRoot();
        System.out.println("balance: "+this.balance.toString());
        this.balance = this.balance.add(event.getAmount());

    }

    @CommandHandler
    public void on(WithdrawMoneyCommand command) {
        System.out.println("withdraw  received "+command.getId());
        Assert.isTrue(command.getAmount().compareTo(BigDecimal.ZERO) > 0 , "Amount should be a positive number");
        if(command.getAmount().compareTo(this.balance) > 0 ) {
            throw new InsufficientBalanceException("Insufficient balance. Trying to withdraw:" + command.getAmount() + ", but current balance is: " + this.balance);
        }
        apply(new MoneyWithdrawnEvent(command.getId(), command.getAmount()));
    }

    @EventSourcingHandler
    public void handle(MoneyWithdrawnEvent event) {
        this.balance = this.balance.subtract((event.getAmount()));
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }


}
