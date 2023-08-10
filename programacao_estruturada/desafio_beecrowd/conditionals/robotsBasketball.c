#include <stdio.h>

int main(void) {

  int D, P;

  scanf("%d", &D);

  if(D <= 800) {
    P = 1;
  } else if (D > 800 && D <= 1400) {
    P = 2;
  } else if (D > 1400 && D <= 2000) {
    P = 3;
  }

  printf("%d\n", P);
  return 0;
}
