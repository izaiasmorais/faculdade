#include <stdio.h>

int notNegative(int x) {

  while (x < 0) {
    printf("Digite valores positivos: \n");

    scanf("%d", &x);
  }

  return x;
}

int divisorsSum(int x) {
  int sum = 0;

  for (int i = 1; i < x; i++) {
    if (x % i == 0) sum = sum + i;
  }

  return sum;
}

int areFriends(int x, int y) {
  int dx, dy;

  dx = divisorsSum(x);
  dy = divisorsSum(y);

  if (x == dy && y == dx) {
    return 1;
  } else {
    return 0;
  }
}

int main() {
  int x, y, value;

  scanf("%d %d", &x, &y);

  if(x == 0 || y == 0) {
    return 0;
  }

  x = notNegative(x);
  y = notNegative(y);

  value = areFriends(x, y);

  if (value == 1) {
    printf("%d e %d são amigos\n", x, y);
  } else {
    printf("%d e %d não são amigos\n", x, y);
  }

  return 0;
}
