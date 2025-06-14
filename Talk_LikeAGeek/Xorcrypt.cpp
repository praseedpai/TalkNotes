////////////////////////////////
//
// A (Practically useless ) Naive implementation
// of Xor Encryption.. Written for Demonstration
//
// Praseed Pai K.T.
// http://praseedp.blogspot.com
//
// Compile using Visual C++ command line compiler
//                 cl XorCrypt.cpp
//
// Usage :-
//         XorCrypt  "Hello World" A
//         XorCrypt  "Quick Brown Lazy Fox" z
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main( int argc , char **argv )
{

  if ( argc != 3 ) {
    fprintf(stdout,"Usage: XorCrypt plaintext key ");
    return 1;
  }

  int plain_len = strlen(argv[1])+1;

  char *p =(char *)malloc(plain_len);

  strcpy(p,argv[1]);

  char key = argv[2][0];

  char *temp = p;

  while (*temp != 0 ) {
    *temp = *temp ^ key;
    temp++;
  }

  fprintf(stdout,"Encrypted string is now ... %s\n",p);

  temp = p;

  while (*temp != 0 ) {
    *temp = *temp ^ key;
    temp++;
  }

  fprintf(stdout,"Encrypted string is now ... %s\n",p);

  free(p);
  return 0; 

}
