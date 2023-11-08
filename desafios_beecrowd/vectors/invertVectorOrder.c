#include <stdio.h>
#define tam 50

int main() {
  int V[tam], T, i;

  for (i = 0; i < tam; i++) {
    scanf("%d", &V[i]);
  }

  for (i = 0; i < (tam / 2); i++) {
    T = V[i];
    V[i] = V[(tam - 1) - i];
    V[(tam - 1) - i] = T;
  }

  for (i = 0; i < tam; i++) {
    printf("V[%d] = %d\n", i, V[i]);
  }

  return 0;
}
