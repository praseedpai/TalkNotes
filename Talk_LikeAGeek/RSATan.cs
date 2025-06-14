////////////////////////////// 
// 
// A Very Naive RSA implementation 
// 
// 
// 
// 
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
// 
// 
// 
// Encryption 
// ---------- 
// C = P^E (mod N) => Modular Exponentiation 
// P = C^D (mod N) => Modular Exponentiation 
// 


using System; 

class PublicKey 
{ 
  public ulong E; 
  public ulong N; 
} 

class PrivateKey 
{ 
  public ulong D; 
  public ulong N; 
} 


class RSANaive 
{ 
  public static byte [] StringToBytes( string ast ) 
  { 
    int length = ast.Length; 
    byte [] ret = new byte[length]; 
    int i=0; 
    foreach(char s in ast ) 
      ret[i++] = Convert.ToByte(s); 

    return ret; 
  } 

  public static ulong ModExp( ulong a , ulong b , ulong n ) 
  { 
    ulong Y=1; 

    while ( b != 0) 
    { 
      if ( (b & (ulong)1) == 1) 
        Y = (Y*a)%n; 

      a= (a*a)%n; 

      b = b >> 1; 
    } 

    return Y; 

  } 

  public static ulong Encrypt( ulong msg , PublicKey key ) 
  { 

    return ModExp( msg , key.E , key.N ); 
  
  } 

  public static ulong DeCrypt( ulong msg , PrivateKey key ) 
  { 
    return ModExp( msg , key.D , key.N ); 

  } 

  public static void Main(String [] args ) 
  { 

    Byte [] msg = StringToBytes("SUZANNE"); 

    PublicKey pub = new PublicKey(); 
    pub.E = 3; 
    pub.N = 33; 

    PrivateKey pri = new PrivateKey(); 
    pri.D = 7; 
    pri.N = 33; 

    ulong [] outp = new ulong[7]; 
    int i=0; 
    foreach( Byte b in msg ) { 

      outp[i++] = Encrypt((ulong)((long)(b - 'A')+1) , pub ); 
      Console.WriteLine(outp[i-1]); 
    } 

    Byte [] rsn = new Byte[outp.Length]; 
    i=0; 
    foreach( ulong rs in outp ) 
    { 
      rsn[i++] = (byte)(DeCrypt( rs , pri ) + 64 ); 
      Console.WriteLine((Char)rsn[i-1]); 

    } 

  } 


}