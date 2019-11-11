package af.asr.springaxon.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AccountOwner implements Serializable{

    private static final long serialVersionUID = -5357787298063565273L;
    private String name;
}