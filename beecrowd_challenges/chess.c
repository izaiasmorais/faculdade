#include <stdio.h>

int main() {
  int x, y, c;
  scanf("%d %d", &x , &y);

  if(x == y) {
    c = 1;
  } else if (x % 2 == 0 && y % 2 != 0) {
    c = 0;
  } else if (y % 2 == 0 && x % 2 != 0) {
    c = 0;
  } else if (x % 2 == 0 && y % 2 == 0) {
    c = 1;
  } else if (x % 2 != 0 && y % 2 != 0) {
    c = 1;
   }

  printf("%d\n", c);

  return 0;
}
