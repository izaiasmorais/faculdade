#include <stdio.h>

int main(void) {
  int x = 0, y = 1, n;

  for (int i = 1; i <= 100; i++) {
    printf("Digite um número \n");
    scanf("%d", &n);

    if (i % 2 == 2) {
      x = x + n;
    } else {
      y = y * n;
    }
  }

  printf("%d %d\n", x, y);

  return 0;
}
