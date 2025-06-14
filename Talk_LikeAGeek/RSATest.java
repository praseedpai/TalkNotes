import java.math.BigInteger;
import java.security.SecureRandom;

// Key Generation Algorithm 
// ------------------------- 
// 
// a) Choose two Large Primes , P and Q 
// b) Compute N = P*Q 
// c) Compute Z = ( P - 1 ) * (Q - 1 ) 
// d) Choose a number which is relatively prime 
// to Z and call it D 
// e) Find E such that ExD = 1 (mod Z ) 
// Public Key => { E , N } 
// Private Key => { D , N } 

public class RSATest {
    public static void main(String[] args) {
        // Key size in bits for each prime. 
        // For real applications, you should use at least 1024 or 2048 bits.
        int bitLength = 512;
        SecureRandom random = new SecureRandom();
        
        // 1. Generate two random primes, p and q.
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength, random);
        
        // 2. Compute the modulus n = p * q.
        BigInteger n = p.multiply(q);
        
        // 3. Compute Euler's Totient, φ(n) = (p - 1) * (q - 1).
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        
        // 4. Choose the public exponent, e.
        // A common choice for e is 65537.
        BigInteger e = BigInteger.valueOf(65537);
        // Ensure that e and phi(n) are relatively prime. If not, choose another e.
        while (!phi.gcd(e).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.TWO); // try next odd number
        }
        
        // 5. Compute the private exponent d such that e*d ≡ 1 mod phi(n).
        BigInteger d = e.modInverse(phi);
        
        // Display the public and private keys.
        System.out.println("Public Key (e, n):");
        System.out.println("e = " + e);
        System.out.println("n = " + n);
        System.out.println();
        
        System.out.println("Private Key (d, n):");
        System.out.println("d = " + d);
        System.out.println("n = " + n);
        System.out.println();
        
        // 6. Encrypt a message.
        String message = "Hello RSA!";
        System.out.println("Original Message: " + message);
        
        // Convert message into a BigInteger using its byte representation.
        BigInteger m = new BigInteger(message.getBytes());
        // RSA requires m < n.
        if (m.compareTo(n) >= 0) {
            System.err.println("Message too long! Choose larger prime values or a shorter message.");
            return;
        }
        
        // Encryption: c = m^e mod n.
        BigInteger ciphertext = m.modPow(e, n);
        System.out.println("Encrypted Message: " + ciphertext);
        
        // 7. Decrypt the message.
        // Decryption: m = c^d mod n.
        BigInteger decrypted = ciphertext.modPow(d, n);
        String decryptedMessage = new String(decrypted.toByteArray());
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}
