#include <stdio.h>

int change(int *x, int *y) {
  int z;

  z = *y;

  *y = *x;

  *x = z;

  return 0;
}

int main() {
  int x, y;

  printf("Digite dois números: \n");

  scanf("%d %d", &x, &y);

  change(&x, &y);

  printf("%d %d \n", x, y);

  return 0;
}
