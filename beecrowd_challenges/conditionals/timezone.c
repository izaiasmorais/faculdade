#include <stdio.h>

int main(void) {
  int s, t, f;

  scanf("%d %d %d", &s, &t, &f);

  int res = (24 + s + t + f) % 24;

  printf("%d\n", res);

  return 0;
}
