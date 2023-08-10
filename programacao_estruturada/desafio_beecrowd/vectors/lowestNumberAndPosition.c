#include <stdio.h>

int main() {
  int N, i;
  int l;
  int index;

  scanf("%d", &N);

  int X[N];

  for (i = 0; i < N; i++) {
    scanf("%d", &X[i]);
  }

  l = X[0];

  for (i = 1; i < N; i++) {
    if (X[i] < l) {
      l = X[i];
      index = i;
    }
  }

  printf("Menor valor: %d\n", l);

  printf("Posicao: %d\n", index);

  return 0;
}
