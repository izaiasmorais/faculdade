#include <stdio.h>

int main() {

  int h, z, l;

  scanf("%d %d %d", &h, &z, &l);

  if ((z > h && h > l) || (z < h && h < l))
    printf("huguinho");
  else if ((h > z && z > l) || (h < z && z < l))
    printf("zezinho");
  else
    printf("luisinho");

  printf("\n");

  return 0;
}
