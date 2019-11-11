package af.asr.springaxon.config;

import com.mongodb.MongoClient;
import org.axonframework.boot.autoconfig.AxonAutoConfiguration;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(AxonAutoConfiguration.class)
public class AxonConfiguration {


    @Autowired
    private EventStore myEventStore;

    @Bean
    public EventStorageEngine eventStore(MongoClient client) {
        return new MongoEventStorageEngine(new DefaultMongoTemplate(client));
    }
//    @Bean
//    public AggregateFactory<AccountAggregate> aggregateFactory(){
//        return new GenericAggregateFactory<AccountAggregate>(AccountAggregate.class);
//    }

//    @Bean
//    public Snapshotter snapShotter(AggregateFactory<AccountAggregate> aggregateFactory){
//        return new AggregateSnapshotter(myEventStore, aggregateFactory);
//    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
    }
//    @Bean
//    public Repository<AccountAggregate> accountAggregateRepository(SnapshotTriggerDefinition snapshotTriggerDefinition,AggregateFactory<AccountAggregate> aggregateFactory){
//        return new EventSourcingRepository<AccountAggregate>(aggregateFactory, myEventStore,snapshotTriggerDefinition);
//    }
//	@ConditionalOnMissingBean
//	@Bean
//	public KafkaMessageConverter<String, byte[]> kafkaMessageConverter(
//			@Qualifier("eventSerializer") Serializer eventSerializer) {
//		return new DefaultKafkaMessageConverter(eventSerializer);
//	}
//	@Bean
//    public Exchange exchange() {
//        return ExchangeBuilder.fanoutExchange("Accounts").build();
//    }
//
//    @Bean
//    public Queue queue() {
//        return QueueBuilder.durable("Accounts").build();
//    }
//
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
//    }


}
