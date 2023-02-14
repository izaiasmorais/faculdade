#include <stdio.h>

int main() {
  int greater;
  int x;

  greater = 0;

  do {
    scanf("%d", &x);

    if (x > greater) {
      greater = x;

    }
  } while (x != 0);

  printf("%d\n", greater);

  return 0;
}
