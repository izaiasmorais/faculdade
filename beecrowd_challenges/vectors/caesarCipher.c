#include <stdio.h>
#include <string.h>

int main() {
  int i, j, n, t, l;

  char str[52], a;

  scanf("%d", &t);

  for (i = 0; i < t; i++) {
    scanf("%s %d", str, &n);

    l = strlen(str);

    for (j = 0; j < l; j++) {

      a = str[j] - n;

      if (a < 65)
        a += 26;

      printf("%c", a);
    }

    printf("\n");
  }

  return 0;
}
