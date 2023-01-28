#include <stdio.h>

int printValue(float a) {
  printf("%f\n", a);

  return 0;
}

int main() {
  float x, r;

  while (x != 5) {
    scanf("%f", &x);

    if (x == 1) {
      r = x * 2 + 200;
      printValue(r);
    }
  }

  return 0;
}
