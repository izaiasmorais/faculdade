#include <stdio.h>
#define tam 100

int main() {
  int N[tam], n, i;

  scanf("%d", &n);

  N[0] = 0;

  for (i = 1; i < tam; i++) {
    N[i] = N[i - 1] * 2;
  }

  for (i = 0; i < tam; i++) {
    printf("N[%d] = %d\n", i, N[i]);
  }

  return 0;
}
