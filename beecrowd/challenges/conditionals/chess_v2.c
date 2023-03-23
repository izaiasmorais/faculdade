#include <stdio.h>

int main() {
  int x, y, c;

  scanf("%d %d", &x, &y);

  c = (x + y) % 2;

  if (c == 0) {
    printf("1 \n");
  } else {
    printf("0 \n");
  }

  return 0;
}
