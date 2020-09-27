package by.it.academy.pojo;

import by.it.academy.utils.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String walletId;

    @Column(columnDefinition="MEDIUMBLOB")
    private PrivateKey privateKey;

    @Column(columnDefinition="MEDIUMBLOB")
    private PublicKey publicKey;

    private String privateKeyString;

    private String publicKeyString;

    @OneToOne
    private User walletOwner;

    private double balance;

    public Wallet(User user){
        generateKeyPair();
        this.walletOwner = user;
        this.balance = 100;
        this.privateKeyString = StringUtil.getStringFromKey(privateKey);
        this.publicKeyString = StringUtil.getStringFromKey(publicKey);
    }

    private void generateKeyPair() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            // Initialize the key generator and generate a KeyPair
            keyGen.initialize(ecSpec, random);   //256 bytes provides an acceptable security level
            KeyPair keyPair = keyGen.generateKeyPair();
            // Set the public and private keys from the keyPair
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletId='" + walletId + '\'' +
                ", privateKey=" + privateKeyString +
                ", publicKey=" + publicKeyString +
                ", balance=" + balance +
                '}';
    }
}
