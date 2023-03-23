#include <stdio.h>

int main() {
  int X, Y;
  float snack;

  scanf("%d %d", &X, &Y);

  float ar[5] = {4.0,4.5,5.0,2.0,1.5};

  snack = ar[X - 1] * Y;

  printf("TOTAL: R$ %.2lf\n", snack);

  return 0;
}

