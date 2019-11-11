package af.asr.springaxon.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Data
@NoArgsConstructor
public class BaseEvent<T> {
    private  T id;
    public BaseEvent(T id) {
        Assert.notNull(id, "Id must be not null");
        this.id = id;
    }

}