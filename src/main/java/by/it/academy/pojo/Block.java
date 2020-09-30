package by.it.academy.pojo;

import by.it.academy.utils.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Block {

    @Id
    private String hash;

    private String previousHash;

    @OneToMany(mappedBy = "block")
    private List<Transaction> transactions;

    private long timeStamp; //as number of milliseconds since 1/1/1970

    private int nonce;


    public Block(List<Transaction> transactions, String previousHash) {
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }

    public String calculateHash() {
        return StringUtil
                .applySha256(previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        transactions
                );
    }

}
