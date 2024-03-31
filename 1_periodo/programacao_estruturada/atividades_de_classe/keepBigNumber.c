#include <stdio.h>

int main() {
  int x, n = 0;

  while (x != 0) {

    scanf("%d", &x);

    if (x == 0) {
      return 0;
    }

    if (n == 0) {
      n = x;
      printf("%d \n", n);
    } else {
      if (x <= n) {
        printf("rejeitado: %d\n", x);
      } else if (x > n) {
        n = x;
        printf("%d\n", n);
      }
    }
  }

  return 0;
}
