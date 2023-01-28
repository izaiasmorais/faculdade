#include <stdio.h>

int main() {
  int x, y, z;

  scanf("%d", &x);

  z = x / 100;
  printf("%d notas de R$ 100,00\n", z);

  y = x % 100;
  z = y / 50;
  printf("%d notas de R$ 50,00\n", z);

  y = x % 50;
  z = y / 20;
  printf("%d notas de R$ 20,00\n", z);

  y = x % 20;
  z = y / 10;
  printf("%d notas de R$ 10,00\n", z);

  return 0;
}
