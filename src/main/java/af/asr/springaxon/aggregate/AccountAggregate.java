package af.asr.springaxon.aggregate;

import lombok.Data;
import lombok.ToString;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

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


}
