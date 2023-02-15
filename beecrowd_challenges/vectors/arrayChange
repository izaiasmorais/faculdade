#include <stdio.h>
#define tam 20

int main() {
  int N[tam], T[tam], i, Y;

  for (i = 0; i < tam; i++)
    scanf("%d", &N[i]);

  for (i = 0; i < tam; i++)
    T[i] = N[i];

  for (i = 0; i < tam; i++) {
    N[i] = T[(tam - 1) - i];
  }

  for (i = 0; i < tam; i++)
    printf("N[%d] = %d\n", i, N[i]);

  return 0;
}
